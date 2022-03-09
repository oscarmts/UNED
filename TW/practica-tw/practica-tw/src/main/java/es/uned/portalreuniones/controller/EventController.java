package es.uned.portalreuniones.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uned.portalreuniones.constants.Session;
import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.EventFinishedException;
import es.uned.portalreuniones.exception.ForbiddenException;
import es.uned.portalreuniones.exception.SessionNotFoundException;
import es.uned.portalreuniones.model.dto.EventFileDTO;
import es.uned.portalreuniones.model.dto.EventPreferenceScheduleDTO;
import es.uned.portalreuniones.model.dto.EventTokenDTO;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventFile;
import es.uned.portalreuniones.model.event.EventPreferenceDateSchedule;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.model.user.UserNotRegistered;
import es.uned.portalreuniones.service.event.EventEnrollService;
import es.uned.portalreuniones.service.event.EventFileService;
import es.uned.portalreuniones.service.event.EventMessageChatService;
import es.uned.portalreuniones.service.event.EventPreferenceDateScheduleService;
import es.uned.portalreuniones.service.event.EventPreferenceScheduleService;
import es.uned.portalreuniones.service.event.EventService;
import es.uned.portalreuniones.service.room.RoomTechResourceService;
import es.uned.portalreuniones.service.user.UserNotRegisteredService;
import es.uned.portalreuniones.service.user.UserService;
import es.uned.portalreuniones.util.Sessions;

/**
 * Controlador para los eventos a nivel de todos los usuarios
 * 
 * @author omontes
 *
 */
@Controller
@RequestMapping("/event")
public class EventController extends AbstractController {

	private static final String EVENT_TOKEN = "eventToken";

	private static final Logger logger = LoggerFactory.getLogger(EventController.class);

	private static String EVENT_PREFERENCES_FORM = "event/eventPreferencesForm";
	private static String EVENT_FORM = "event/eventForm";
	private static String FORBIDDEN_FORM = "event/forbidden";
	private static String REDIRECT_FORBIDDEN_FORM = "redirect:/event/forbidden";
	private static String REDIRECT_TOKEN_FORBIDDEN_FORM = "redirect:/event/token/forbidden";
	private static String TOKEN_FORM = "event/tokenForm";
	private static String REDIRECT_TOKEN_FORM = "redirect:/event/invite/token/";
	private static String EVENT_DETAIL = "event/detail";
	private static final String TOKEN_FORM_OK = "event/tokenFormOk";
	private static final String EVENT_PREFERENCES_DTO = "eventPreferences";
	private static final String MSG_FORBIDDEN = "msgForbidden";
	private static final String ID_USER_TOKEN = "idUserToken";
	private static final String ID_EVENT_FINISHED = "idEventFinished";
	private static final String WITH_TOKEN = "withToken";

	@Autowired
	private EventService eventService;

	@Autowired
	private EventPreferenceScheduleService eventPreferenceScheduleService;

	@Autowired
	private EventPreferenceDateScheduleService eventPreferenceDateScheduleService;

	@Autowired
	private EventEnrollService eventEnrollService;

	@Autowired
	private EventFileService eventFileService;

	@Autowired
	private EventMessageChatService eventMessageChatService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserNotRegisteredService userNotRegisteredService;

	@Autowired
	private RoomTechResourceService roomTechResourceService;

	/**
	 * Ir a la pantalla del evento
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	@RequestMapping("/{idEvent}/{idUser}")
	public String event(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idUser) {
		try {
			checkSession(request);
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		}
		return goEvent(request, model, idEvent, idUser, false);
	}

	/**
	 * Ir a la pantalla del evento por token
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	@RequestMapping("/enter/token/{idEvent}/{idUser}")
	public String eventToken(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idUser) {
		return goEvent(request, model, idEvent, idUser, true);
	}

	private String goEvent(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idUser, boolean withToken) {
		try {
			Sessions.setValue(request, WITH_TOKEN, withToken);
			boolean isOwner = false;
			if (withToken) {
				eventService.checkPermission(idEvent, idUser, withToken);
			} else {
				eventService.checkPermission(idEvent, (Long) Sessions.getValue(request, Session.ID_USER), withToken);
			}
			User user = null;
			String urlEvent = null;
			if (withToken) {
				UserNotRegistered userNotRegistered = userNotRegisteredService.findById(idUser);
				user = userNotRegistered.getUser();
				urlEvent = "/event/enter/token/" + idEvent + "/" + idUser;
				Sessions.setValue(request, URL_EVENT, "redirect:/" + urlEvent);
				Sessions.setValue(request, ID_USER_TOKEN, idUser);
			} else {
				user = userService.findById(idUser);
				urlEvent = "/event/" + idEvent + "/" + idUser;
				Sessions.setValue(request, URL_EVENT, "redirect:/" + urlEvent);
			}
			Event event = eventService.findById(idEvent);
			Sessions.setValue(request, Session.ID_EVENT, idEvent);
			if (Sessions.contains(request, Session.ID_USER)) {
				isOwner = event.getOwner().getId().equals((Long) Sessions.getValue(request, Session.ID_USER));
			}
			model.addAttribute("urlEvent", urlEvent);
			model.addAttribute("user", user);
			model.addAttribute("username", user.getNickName());
			model.addAttribute("idEvent", idEvent);
			model.addAttribute("chat", eventMessageChatService.findByIdEvent(idEvent));
			model.addAttribute("usersEnroll", eventEnrollService.findEventEnrollByIdEvent(idEvent));
			model.addAttribute("usersNotRegistered", userNotRegisteredService.findByIdEvent(idEvent));
			model.addAttribute("files", eventFileService.findByIdEvent(idEvent));
			model.addAttribute("techResources", roomTechResourceService.findByIdRoom(event.getRoom().getId()));
			model.addAttribute("isOwner", isOwner);
			completeModelSession(request, model);
		} catch (ElementNotFoundException e) {
			// TODO Veremos como controlamos el error
		} catch (EventFinishedException e) {
			Sessions.setValue(request, MSG_FORBIDDEN, e.getMessage());
			Sessions.setValue(request, ID_EVENT_FINISHED, idEvent);
			if (withToken) {
				return REDIRECT_TOKEN_FORBIDDEN_FORM;
			} else {
				return REDIRECT_FORBIDDEN_FORM;
			}
		} catch (ForbiddenException e) {
			Sessions.setValue(request, MSG_FORBIDDEN, e.getMessage());
			return REDIRECT_FORBIDDEN_FORM;
		}

		return EVENT_FORM;
	}

	/**
	 * Subir fichero al evento
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param file
	 * @return
	 */
	@PostMapping("/uploadFile")
	public String uploadFile(HttpServletRequest request, Model model,
			@RequestParam(value = "idEvent", required = true) Long idEvent, @RequestParam("file") MultipartFile file) {
		try {
			if (file.getSize() > 0) {
				EventFileDTO eventFileDTO = new EventFileDTO();
				eventFileDTO.setIdEvent(idEvent);
				eventFileDTO.setMultipartFile(file);

				eventFileService.addFileToEvent(eventFileDTO);
			}
		} catch (ElementNotFoundException e) {
			// TODO Ya veremos como manejamos esto
		} catch (EventFinishedException e) {
			Sessions.setValue(request, MSG_FORBIDDEN, e.getMessage());
			Sessions.setValue(request, MSG_FORBIDDEN, e.getMessage());
			Sessions.setValue(request, ID_EVENT_FINISHED, idEvent);
			boolean withToken = (boolean) Sessions.getValue(request, WITH_TOKEN);
			Sessions.removeValue(request, WITH_TOKEN);
			if (withToken) {
				return REDIRECT_TOKEN_FORBIDDEN_FORM;
			} else {
				return REDIRECT_FORBIDDEN_FORM;
			}
		}
		return (String) Sessions.getValue(request, URL_EVENT);
	}

	/**
	 * Ver el contenido del fichero del evento
	 * 
	 * @param idFile
	 * @param response
	 */
	@GetMapping("/showFile/{idFile}")
	public void showFile(@PathVariable Long idFile, HttpServletResponse response) {
		try {
			EventFile eventFile = eventFileService.findById(idFile);
			response.setContentType(eventFile.getFileType());
			InputStream is = new ByteArrayInputStream(eventFile.getData());
			IOUtils.copyLarge(is, response.getOutputStream());
		} catch (IOException e) {
			// TODO Ya veremos como manejamos esto
			e.printStackTrace();
		} catch (ElementNotFoundException e1) {
			// TODO Ya veremos como manejamos esto
			e1.printStackTrace();
		}
	}

	/**
	 * Ventana de votación de fecha y hora
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	@RequestMapping("/preferences/{idEvent}/{idUser}")
	public String showEventPreferenceSchedule(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idUser) {
		if (idEvent != null) {
			try {
				checkSession(request);
				List<EventPreferenceDateSchedule> ownerDates = eventPreferenceDateScheduleService
						.findByIdEvent(idEvent);
				if (!model.containsAttribute(EVENT_PREFERENCES_DTO)) {
					EventPreferenceScheduleDTO eventPrefereceDTO = new EventPreferenceScheduleDTO();
					eventPrefereceDTO.setDate(ownerDates.get(0).getDate());
					model.addAttribute(EVENT_PREFERENCES_DTO, eventPrefereceDTO);
				}
				model.addAttribute("ownerDates", ownerDates);
				model.addAttribute("ownerHours",
						eventPreferenceScheduleService.findHoursFromOwner(Long.valueOf(idEvent)));
				model.addAttribute("idEvent", idEvent);
				model.addAttribute("idUser", idUser);
				model.addAttribute("user", userService.findById(idUser));
				completeModelSession(request, model);
			} catch (SessionNotFoundException | ElementNotFoundException e) {
				return GO_LOGIN;
			}
		} else {
			return GO_LOGIN;
		}

		return EVENT_PREFERENCES_FORM;
	}

	/**
	 * Guardar la fecha y hora votada del usuario
	 * 
	 * @param request
	 * @param model
	 * @param eventPreferenceScheduleDTO
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/preferences/create")
	public String createPreferenceScheduleUser(HttpServletRequest request, Model model,
			@Valid @ModelAttribute(EVENT_PREFERENCES_DTO) EventPreferenceScheduleDTO eventPreferenceScheduleDTO,
			BindingResult errors, RedirectAttributes attr) {
		String redirectPreferences = "redirect:/event/preferences/" + eventPreferenceScheduleDTO.getIdEvent() + "/"
				+ eventPreferenceScheduleDTO.getIdUser();
		eventPreferenceScheduleService.create(eventPreferenceScheduleDTO);
		saveSucessMessage("Se han guardado correctamente sus preferencias.", request, errors);
		return redirect(errors, redirectPreferences, REDIRECT_USER_PANEL, EVENT_PREFERENCES_DTO,
				eventPreferenceScheduleDTO, attr);
	}

	/**
	 * Ir a la ventana de invitación por token
	 * 
	 * @param model
	 * @param token
	 * @return
	 */
	@RequestMapping("/invite/token/{token}")
	public String inviteWithToken(HttpServletRequest request, Model model, @PathVariable String token) {
		if (!model.containsAttribute(EVENT_TOKEN)) {
			model.addAttribute(EVENT_TOKEN, new EventTokenDTO());
		}
		model.addAttribute("token", token);
		return TOKEN_FORM;
	}

	/**
	 * Registrarse al evento por token
	 * 
	 * @param model
	 * @param token
	 * @param nickName
	 * @return
	 */
	@RequestMapping("/token")
	public String eventToken(HttpServletRequest request, Model model,
			@Valid @ModelAttribute(EVENT_TOKEN) EventTokenDTO eventToken, BindingResult errors,
			RedirectAttributes attr) {
		Long idEvent = null;
		try {
			String token = eventToken.getToken();
			String nickName = eventToken.getNickName();
			if (errors.getAllErrors().size() == 0) {
				if (nickName != null && nickName.trim().length() == 0) {
					addError(errors, "El nombre no puede estar vacío");
				} else {
					Event event = eventService.findByToken(token);
					idEvent = event.getId();
					UserNotRegistered userNotRegistered = userNotRegisteredService.create(nickName, idEvent);
					model.addAttribute("event", event);
					model.addAttribute("idUser", userNotRegistered.getId());
				}
			}
		} catch (ElementNotFoundException e) {
			return GO_LOGIN;
		}
		return redirect(errors, REDIRECT_TOKEN_FORM.concat(eventToken.getToken()), TOKEN_FORM_OK, EVENT_TOKEN,
				eventToken, attr);
	}

	/**
	 * Ir al detalle de un evento finalizado
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @return
	 */
	@RequestMapping("/detail/{idEvent}")
	public String detailEvent(HttpServletRequest request, Model model, @PathVariable Long idEvent) {
		try {
			checkSession(request);
		} catch (SessionNotFoundException e) {
			return GO_LOGIN;
		}

		return goDetail(request, model, idEvent, (Long) Sessions.getValue(request, Session.ID_USER), false);
	}

	/**
	 * Ir al detalle de un evento finalizado por token
	 * 
	 * @param request
	 * @param model
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	@RequestMapping("/detail/token/{idEvent}/{idUser}")
	public String detailTokenEvent(HttpServletRequest request, Model model, @PathVariable Long idEvent,
			@PathVariable Long idUser) {
		return goDetail(request, model, idEvent, idUser, true);
	}

	private String goDetail(HttpServletRequest request, Model model, Long idEvent, Long idUser, boolean withToken) {
		try {
			Event event = eventService.findById(idEvent);
			User user = null;
			if (withToken) {
				eventService.checkDetailPermission(idEvent, idUser, withToken);
				UserNotRegistered userNotRegistered = userNotRegisteredService.findById(idUser);
				user = userNotRegistered.getUser();
			} else {
				eventService.checkDetailPermission(idEvent, (Long) Sessions.getValue(request, Session.ID_USER),
						withToken);
				user = userService.findById(idUser);
			}
			Sessions.setValue(request, Session.ID_EVENT, idEvent);
			model.addAttribute("idEvent", idEvent);
			model.addAttribute("user", user);
			model.addAttribute("event", event);
			model.addAttribute("idEvent", idEvent);
			model.addAttribute("chat", eventMessageChatService.findByIdEvent(idEvent));
			model.addAttribute("usersEnroll", eventEnrollService.findEventEnrollByIdEvent(idEvent));
			model.addAttribute("usersNotRegistered", userNotRegisteredService.findByIdEvent(idEvent));
			model.addAttribute("files", eventFileService.findByIdEvent(idEvent));
			completeModelSession(request, model);
		} catch (ForbiddenException e) {
			Sessions.setValue(request, MSG_FORBIDDEN, e.getMessage());
			return REDIRECT_FORBIDDEN_FORM;
		} catch (ElementNotFoundException e) {
			// TODO Recuerda controlar este error
		}

		return EVENT_DETAIL;

	}

	/**
	 * Ir a ventana de prohibido
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/forbidden")
	public String forbidden(HttpServletRequest request, Model model) {
		try {
			if (Sessions.contains(request, Session.ID_USER)) {
				model.addAttribute("user", userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			}
			model.addAttribute(MSG_FORBIDDEN, Sessions.getValue(request, MSG_FORBIDDEN));
			model.addAttribute(ID_EVENT_FINISHED, Sessions.getValue(request, ID_EVENT_FINISHED));
			model.addAttribute("idUser", Sessions.getValue(request, Session.ID_USER));
			completeModelSession(request, model);
			Sessions.removeValue(request, MSG_FORBIDDEN);
			Sessions.removeValue(request, ID_EVENT_FINISHED);
		} catch (ElementNotFoundException e) {
			// TODO Recuerda controlar este error
		}
		return FORBIDDEN_FORM;
	}

	/**
	 * Ir a ventana de prohibido
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/token/forbidden")
	public String forbiddenToken(HttpServletRequest request, Model model) {
		try {
			if (Sessions.contains(request, Session.ID_USER)) {
				model.addAttribute("user", userService.findById((Long) Sessions.getValue(request, Session.ID_USER)));
			}
			model.addAttribute(MSG_FORBIDDEN, Sessions.getValue(request, MSG_FORBIDDEN));
			model.addAttribute(ID_EVENT_FINISHED, Sessions.getValue(request, ID_EVENT_FINISHED));
			model.addAttribute(ID_USER_TOKEN, Sessions.getValue(request, ID_USER_TOKEN));

			completeModelSession(request, model);
			Sessions.removeValue(request, MSG_FORBIDDEN);
			Sessions.removeValue(request, ID_EVENT_FINISHED);
			Sessions.removeValue(request, ID_USER_TOKEN);
		} catch (ElementNotFoundException e) {
			// TODO Recuerda controlar este error
		}
		return FORBIDDEN_FORM;
	}

}
