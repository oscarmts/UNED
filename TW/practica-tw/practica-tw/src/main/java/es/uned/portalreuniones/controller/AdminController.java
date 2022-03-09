package es.uned.portalreuniones.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uned.portalreuniones.constants.Session;
import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.NicknameFoundException;
import es.uned.portalreuniones.exception.RoomNameFoundException;
import es.uned.portalreuniones.exception.SessionNotFoundException;
import es.uned.portalreuniones.model.dto.EventDTO;
import es.uned.portalreuniones.model.dto.EventDateDTO;
import es.uned.portalreuniones.model.dto.RoomDTO;
import es.uned.portalreuniones.model.dto.UserDTO;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventInvited;
import es.uned.portalreuniones.model.room.Room;
import es.uned.portalreuniones.model.user.UserNotRegistered;
import es.uned.portalreuniones.service.event.EventFileService;
import es.uned.portalreuniones.service.event.EventInvitedService;
import es.uned.portalreuniones.service.event.EventMessageChatService;
import es.uned.portalreuniones.service.event.EventPreferenceScheduleService;
import es.uned.portalreuniones.service.event.EventService;
import es.uned.portalreuniones.service.event.EventWaitingListService;
import es.uned.portalreuniones.service.room.RoomService;
import es.uned.portalreuniones.service.room.TechResourceService;
import es.uned.portalreuniones.service.user.RolService;
import es.uned.portalreuniones.service.user.UserNotRegisteredService;
import es.uned.portalreuniones.service.user.UserService;
import es.uned.portalreuniones.util.Dates;
import es.uned.portalreuniones.util.Sessions;
import es.uned.portalreuniones.util.Users;

/**
 * Controlador para las funciones del administrador/jefe de proyecto
 * 
 * @author omontes
 *
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private static String ADMIN_EVENT_FORM = "admin/event/newEvent";
	private static String REDIRECT_ADMIN_EVENT_FORM = "redirect:/admin/event/newEvent";
	private static String ADMIN_EVENT_CONFIGURE = "admin/event/configure";
	private static String REDIRECT_ADMIN_EVENT_CONFIGURE = "redirect:/admin/event/configure";
	private static String USER_FORM = "admin/user/newUser";
	private static String REDIRECT_USER_FORM = "redirect:/admin/user/newUser";
	private static String ROOM_FORM = "admin/room/newRoom";
	private static String REDIRECT_ROOM_FORM = "redirect:/admin/room/newRoom";
	private static String GO_EVENT = "redirect:/event/";

	private static final String EVENT_DTO = "event";
	private static final String ROOM_DTO = "room";
	private static final String DATE_EVENT_DTO = "dateEvent";

	@Autowired
	private EventService eventService;

	@Autowired
	private EventPreferenceScheduleService eventPreferenceScheduleService;

	@Autowired
	private EventInvitedService eventInvitedService;

	@Autowired
	private EventWaitingListService eventWaitingListService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserNotRegisteredService userNotRegisteredService;

	@Autowired
	private RolService rolService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private TechResourceService techResourceService;

	@Autowired
	private EventFileService eventFileService;

	@Autowired
	private EventMessageChatService eventMessageChatService;

	/**
	 * Ver pantalla de nuevo evento
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/event/newEvent")
	public String showEventForm(HttpServletRequest request, Model model) {

		try {
			checkSession(request);
			Date firstDate = null;
			List<Date> ownerDates = new ArrayList<Date>();
			if (!model.containsAttribute(EVENT_DTO)) {
				model.addAttribute(EVENT_DTO, new EventDTO());
				firstDate = Dates.now();
			} else {
				Date[] ownerDatesSelected = (Date[]) Sessions.getValue(request, "ownerDates");
				if (ownerDatesSelected != null && ownerDatesSelected.length > 0) {
					firstDate = ownerDatesSelected[0];
					for (int i = 1; i < ownerDatesSelected.length; i++) {
						ownerDates.add(ownerDatesSelected[i]);
					}
				}
			}
			model.addAttribute("firstDate", firstDate);
			model.addAttribute("ownerDates", ownerDates);
			model.addAttribute("users", userService.findAll());
			model.addAttribute("rooms", roomService.findAll());
			model.addAttribute("hours", roomService.getHours());
			model.addAttribute("user", userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			model.addAttribute(Session.USERS_SELECTED, Sessions.getValue(request, Session.USERS_SELECTED));
			model.addAttribute(Session.WAITING_LIST_SELECTED,
					Sessions.getValue(request, Session.WAITING_LIST_SELECTED));
			Sessions.removeValue(request, Session.USERS_SELECTED);
			Sessions.removeValue(request, Session.WAITING_LIST_SELECTED);
			Sessions.removeValue(request, "ownerDates");
			completeModelSession(request, model);
		} catch (SessionNotFoundException | ElementNotFoundException e) {
			logger.error("Error al entrar al formulario de nuevo evento {}", e.getMessage());
			return GO_LOGIN;
		}
		return ADMIN_EVENT_FORM;
	}

	/**
	 * Crear un nuevo evento
	 * 
	 * @param request
	 * @param model
	 * @param eventDTO
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/event/create")
	public String createEvent(HttpServletRequest request, Model model,
			@Valid @ModelAttribute(EVENT_DTO) EventDTO eventDTO, BindingResult errors, RedirectAttributes attr) {
		logger.info("Se crea nuevo evento");
		try {
			Room room = eventDTO.getRoom();
			if (eventDTO.getHours() != null && eventDTO.getHours().length > 0) {
				checkRoomAvailable(room.getId(), eventDTO.getHours(), errors);
			}
			Integer[] usersInvited = Users.refreshUsersSelected(eventDTO);
			checkErrors(room, usersInvited, errors);
			Sessions.setValue(request, Session.USERS_SELECTED, eventDTO.getUsersSelected());
			Sessions.setValue(request, Session.WAITING_LIST_SELECTED, eventDTO.getWaitingListSelected());
			Sessions.setValue(request, "ownerDates", eventDTO.getDates());
			eventDTO.setUsers(usersInvited);
			eventDTO.setWaitingList(Users.refreshWaitingList(eventDTO));
			eventDTO.setOwner(userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			if (!isErrors(errors)) {
				eventService.createEvent(eventDTO);
			}
		} catch (ElementNotFoundException e) {
			logger.error("Error al crear un nuevo evento {}", e.getMessage());
			return REDIRECT_USER_PANEL;
		}
		saveSucessMessage("Evento creado correctamente.", request, errors);
		return redirect(errors, REDIRECT_ADMIN_EVENT_FORM, REDIRECT_USER_PANEL, EVENT_DTO, eventDTO, attr);
	}

	/**
	 * Ir a la pantalla de configuración de evento
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @return
	 */
	@RequestMapping("/event/configure/{idEvent}")
	public String configureEvent(HttpServletRequest request, Model model, @PathVariable Long idEvent) {
		try {
			checkSession(request);
			Event event = null;
			if (!model.containsAttribute("event") && !model.containsAttribute(DATE_EVENT_DTO)) {
				event = eventService.findById(idEvent);
			} else {
				if (model.containsAttribute("event")) {
					event = (Event) model.getAttribute("event");
				}
				if (model.containsAttribute(DATE_EVENT_DTO)) {
					EventDateDTO dateEventDTO = (EventDateDTO) model.getAttribute(DATE_EVENT_DTO);
					event = eventService.findById(dateEventDTO.getIdEvent());
				}
			}

			List<EventInvited> usersInvited = eventInvitedService.findEventInvitedByIdEvent(idEvent);

			List<EventInvited> usersInvitedWithoutVote = eventInvitedService
					.findEventInvitedWithoutVoteByIdEvent(idEvent);
			List<UserNotRegistered> usersNotRegisteredInvited = userNotRegisteredService.findByIdEvent(idEvent);
			// Solo podremos configurar el evento si hay lista de invitados y han votado
			// todos
			boolean canSave = false;
			if (usersInvited.size() > 0) {
				canSave = usersInvitedWithoutVote.size() == 0;
			}
			model.addAttribute("event", event);
			model.addAttribute("rooms", roomService.findAll());
			model.addAttribute("afororestante",
					event.getRoom().getCapacity() - usersInvited.size() - usersNotRegisteredInvited.size());
			model.addAttribute("usersPreferences", eventPreferenceScheduleService.findUserWithHoursByIdEvent(idEvent));
			model.addAttribute("eventInvited", usersInvitedWithoutVote);
			model.addAttribute("usersNotRegistered", usersNotRegisteredInvited);
			model.addAttribute("waitingList", eventWaitingListService.findByIdEvent(idEvent));
			model.addAttribute("idRoom", event.getRoom().getId());
			model.addAttribute("canSave", canSave);
			if (!model.containsAttribute(DATE_EVENT_DTO)) {
				EventDateDTO eventDate = eventService.configureDateEvent(idEvent);
				eventDate.setIdEvent(idEvent);
				if (eventDate.getDate() == null) {
					eventDate.setDate(Dates.now());
				}
				model.addAttribute(DATE_EVENT_DTO, eventDate);
			}
			model.addAttribute("user", userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			completeModelSession(request, model);
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		} catch (ElementNotFoundException e) {
			return GO_LOGIN;
		}

		return ADMIN_EVENT_CONFIGURE;
	}

	/**
	 * Actualización de la configuración del evento
	 * 
	 * @param request
	 * @param event
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/event/configure/update")
	public String configureEventUpdate(HttpServletRequest request, @ModelAttribute("event") Event event,
			BindingResult errors, RedirectAttributes attr) {
		try {
			checkSession(request);
			if (event.getHour() != null) {
				checkRoomAvailable(event.getRoom().getId(), new int[] { event.getHour() }, errors);
			}
			if (!isErrors(errors)) {
				eventService.update(event);
			}
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		} catch (ElementNotFoundException e) {
			return GO_LOGIN;
		}
		return REDIRECT_ADMIN_EVENT_CONFIGURE + "/" + event.getId();
	}

	/**
	 * Traspasar un usuario de la lista de invitados a la lista de espera
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	@RequestMapping("/event/configure/waitingList/transfer/{idEvent}/{idUser}")
	public String configureEventWaitingListTransfer(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idUser) {
		try {
			checkSession(request);
			eventWaitingListService.transferFromEventInvited(idEvent, idUser);
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		} catch (ElementNotFoundException e) {
			// TODO Recuerda controlar este error
		}

		return REDIRECT_ADMIN_EVENT_CONFIGURE + "/" + idEvent;
	}

	/**
	 * Traspasar un usuario como invitado desde la lista de espera
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	@RequestMapping("/event/configure/invited/transfer/{idEvent}/{idUser}")
	public String configureEventInvitedTransfer(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idUser) {
		try {
			checkSession(request);
			eventInvitedService.transferFromEventWaitingList(idEvent, idUser);
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		} catch (ElementNotFoundException e) {
			// TODO Recuerda controlar este error
		}

		return REDIRECT_ADMIN_EVENT_CONFIGURE + "/" + idEvent;
	}

	/**
	 * Configuración definitiva del evento con la fecha y hora más votadas
	 * 
	 * @param model
	 * @param eventDTO
	 * @param idRoom
	 * @param request
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/event/configure/date")
	public String configureEventHour(@RequestParam(value = "idRoom", required = true) Long idRoom,
			@Valid @ModelAttribute(DATE_EVENT_DTO) EventDateDTO eventDTO, BindingResult errors, Model model,
			HttpServletRequest request, RedirectAttributes attr) {
		Long idEvent = eventDTO.getIdEvent();

		checkRoom(idRoom, eventDTO, errors, idEvent);
		if (!isErrors(errors)) {
			eventService.configureEventWithDate(idEvent, eventDTO.getDate(), eventDTO.getHour());
		}
		saveSucessMessage("Evento configurado correctamente.Puede acceder a él en su bandeja de entrada.", request,
				errors);
		String redirect_event_configure = REDIRECT_ADMIN_EVENT_CONFIGURE + "/" + idEvent;
		return redirect(errors, redirect_event_configure, REDIRECT_USER_PANEL, DATE_EVENT_DTO, eventDTO, attr);
	}

	/**
	 * Finalizar evento
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @return
	 */
	@RequestMapping("/event/finish")
	public String finishEvent(HttpServletRequest request, Model model,
			@RequestParam(value = "idEvent", required = true) Long idEvent) {
		try {
			checkSession(request);
			model.addAttribute("user", userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			eventService.finishEvent(idEvent);
			completeModelSession(request, model);
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		} catch (ElementNotFoundException e) {
			// TODO Recuerda controlar este error
		}
		saveSucessMessage("Evento finalizado correctamente, puede consultarlo en su lista de eventos finalizados.",
				request);
		return REDIRECT_USER_PANEL;
	}

	/**
	 * Ver pantalla de nuevo jefe de proyecto
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/user/newUser")
	public String showUserForm(HttpServletRequest request, Model model) {
		try {
			checkSession(request);
			if (!model.containsAttribute(USER_DTO)) {
				model.addAttribute(USER_DTO, new UserDTO());
			}
			model.addAttribute("roles", rolService.findAll());
			model.addAttribute("user", userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			completeModelSession(request, model);
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		} catch (ElementNotFoundException e) {
			// TODO Recuerda controlar este error
		}
		return USER_FORM;
	}

	/**
	 * Crear jefe de proyecto
	 * 
	 * @param request
	 * @param user
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/user/create")
	public String createUser(HttpServletRequest request, @Valid @ModelAttribute(USER_DTO) UserDTO user,
			BindingResult errors, RedirectAttributes attr) {
		try {
			if (!isErrors(errors)) {
				user.setRol(rolService.findById(1l));
				userService.create(user);
			}
		} catch (NicknameFoundException e) {
			addError(errors, e.getMessage());
		} catch (ElementNotFoundException e) {
			// Ya veremos como controlar excepciones
		}
		saveSucessMessage("Usuario creado correctamente.", request, errors);
		return redirect(errors, REDIRECT_USER_FORM, REDIRECT_USER_PANEL, USER_DTO, user, attr);
	}

	/**
	 * Ir a la pantalla de nueva sala
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/room/newRoom")
	public String showRoomForm(HttpServletRequest request, Model model) {
		try {
			if (!model.containsAttribute(ROOM_DTO)) {
				model.addAttribute(ROOM_DTO, new RoomDTO());
			}
			model.addAttribute("techResources", techResourceService.findAll());
			model.addAttribute("hours", roomService.getHours());
			model.addAttribute("user", userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			completeModelSession(request, model);
		} catch (ElementNotFoundException e) {
			return GO_LOGIN;
		}

		return ROOM_FORM;
	}

	/**
	 * Creación de la sala
	 * 
	 * @param request
	 * @param model
	 * @param room
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/room/create")
	public String createRoom(HttpServletRequest request, @Valid @ModelAttribute(ROOM_DTO) RoomDTO room,
			BindingResult errors, Model model, RedirectAttributes attr) {
		checkCapacity(room, errors);
		if (!isErrors(errors)) {
			try {
				roomService.createRoom(room);
			} catch (RoomNameFoundException e) {
				addError(errors, e.getMessage());
			}
			saveSucessMessage("Sala creada correctamente.", request, errors);
		}
		return redirect(errors, REDIRECT_ROOM_FORM, REDIRECT_USER_PANEL, ROOM_DTO, errors, attr);
	}

	/**
	 * Borrar fichero del evento
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idFile
	 * @return
	 */
	@RequestMapping("/event/deleteFile/{idEvent}/{idFile}")
	public String deleteFile(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idFile) {
		try {
			eventFileService.delete(idFile);
		} catch (ElementNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (String) Sessions.getValue(request, URL_EVENT);
	}

	/**
	 * Borrar mensaje del evento
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idMessage
	 * @return
	 */
	@RequestMapping("/event/chat/deleteMessage/{idEvent}/{idMessage}")
	public String deleteChatMessage(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idMessage) {
		eventMessageChatService.delete(idMessage);
		return GO_EVENT + idEvent + "/" + idMessage;
	}

	private void checkCapacity(RoomDTO room, BindingResult errors) {
		try {
			Integer capacity = Integer.valueOf(room.getCapacity());
			if (capacity <= 0) {
				addError(errors, "La capacidad debe ser superior a cero");
			}
		} catch (NumberFormatException nfe) {
			addError(errors, "La capacidad de la sala debe ser un número");
		}
	}

	private void checkErrors(Room room, Integer[] usersInvited, BindingResult errors) {
		if (usersInvited.length > 0) {
			if (room.getCapacity() < usersInvited.length) {
				addError(errors, "El aforo máximo de la sala " + room.getName() + " es de " + room.getCapacity());
			}
		} else {
			addError(errors, "Debe invitar al menos a un usuario");
		}
	}

	private void checkRoom(Long idRoom, EventDateDTO eventDTO, BindingResult errors, Long idEvent) {
		if (eventDTO.getHour() != null) {
			if (checkRoomAvailable(idRoom, new int[] { eventDTO.getHour() }, errors)) {
				if (roomService.isReserved(idRoom, idEvent, eventDTO.getDate(), eventDTO.getHour())) {
					addError(errors, "La sala está ya reservada, debe escoger otra");
				}
			}
		}
	}

	private boolean checkRoomAvailable(Long idRoom, int[] hours, BindingResult errors) {
		boolean isRoomAvailable = false;
		try {
			isRoomAvailable = roomService.isAvailable(idRoom, hours);
			if (!isRoomAvailable) {
				Room room = roomService.findById(idRoom);
				addError(errors, "La sala " + room.getName() + " solo puede utilizarse las siguientes horas: "
						+ room.getHoursToString());
			}
		} catch (ElementNotFoundException e) {

		}
		return isRoomAvailable;
	}
}
