package es.uned.portalreuniones.controller;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uned.portalreuniones.constants.Session;
import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.NicknameFoundException;
import es.uned.portalreuniones.exception.SessionNotFoundException;
import es.uned.portalreuniones.model.dto.UserDTO;
import es.uned.portalreuniones.model.dto.UserUpdateDTO;
import es.uned.portalreuniones.service.event.EventEnrollService;
import es.uned.portalreuniones.service.event.EventInvitedService;
import es.uned.portalreuniones.service.event.EventService;
import es.uned.portalreuniones.service.user.RolService;
import es.uned.portalreuniones.service.user.UserService;
import es.uned.portalreuniones.util.Sessions;

/**
 * Gestión de los usuarios
 * 
 * @author omontes
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	private static String NEW_USER = "user/newUser";
	private static String REDIRECT_NEW_USER = "redirect:/user/newUser";

	@Autowired
	private UserService userService;

	@Autowired
	private RolService rolService;

	@Autowired
	private EventService eventService;

	@Autowired
	private EventInvitedService eventInvitedService;

	@Autowired
	private EventEnrollService eventEnrollService;

	private static String USER_PANEL = "user/panel";

	private static final String USER_UPDATE_DTO = "userUpdate";

	/**
	 * Ir a la pantalla de nuevo usuario
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/newUser")
	public String newUser(HttpServletRequest request, Model model) {
		if (!model.containsAttribute(USER_DTO)) {
			model.addAttribute("newUser", new UserDTO());
		}
		return NEW_USER;
	}

	/**
	 * Crear usuario
	 * 
	 * @param request
	 * @param user
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/create")
	public String createUser(HttpServletRequest request, @Valid @ModelAttribute(USER_DTO) UserDTO user,
			BindingResult errors, Model model, RedirectAttributes attr) {
		try {
			if (!isErrors(errors)) {
				// Le ponemos el rol de Usuario (ID=3)
				user.setRol(rolService.findById(3l));
				userService.create(user);
			}
		} catch (NicknameFoundException e) {
			addError(errors, e.getMessage());
		} catch (ElementNotFoundException e) {
			// TODO Gestionar excepción
		}
		return redirect(errors, REDIRECT_NEW_USER, REDIRECT_USER_PANEL, USER_DTO, user, attr);
	}

	/**
	 * Ir al panel de usuario
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/panel")
	public String showUserPanel(HttpServletRequest request, Model model) {
		try {
			checkSession(request);
			Long idUser = (Long) Sessions.getValue(request, Session.ID_USER);
			logger.info("Entramos a su panel el usuario con ID:: {}", idUser);
			model.addAttribute("eventsInvinted", eventInvitedService.findEventInvitedByIdUser(idUser));
			model.addAttribute("eventsEnrolled", eventEnrollService.findByIdUser(idUser));
			model.addAttribute("myEvents", eventService.findByIdOwner(idUser));
			model.addAttribute("myEventsFinished", eventService.findFinishedByIdOwner(idUser));
			model.addAttribute("pendingEvents", eventService.findPendingEventByIdOwner(idUser));
			model.addAttribute("finishedEvents", eventInvitedService.findEventInvitedFinishedByIdUser(idUser));
			model.addAttribute("idUser", idUser);
			model.addAttribute("user", userService.findById(idUser));
			if (!model.containsAttribute(USER_UPDATE_DTO)) {
				model.addAttribute(USER_UPDATE_DTO, userService.findUserDTOById(idUser));
			}
			Sessions.removeValue(request, Session.USERS_SELECTED);
			setSucessMessage(request, model);
			completeModelSession(request, model);
		} catch (ElementNotFoundException e) {
			logger.error("No se encontró el elemento {}", e.getMessage());
			return GO_LOGIN;
		} catch (SessionNotFoundException e1) {
			logger.warn("No hay sesión {}", e1.getMessage());
			return GO_LOGIN;
		}
		return USER_PANEL;
	}

	/**
	 * Ir a la pantalla de actualizar datos del usuario
	 * 
	 * @param userDTO
	 * @param errors
	 * @param attr
	 * @return
	 */
	@RequestMapping("/update")
	public String updateUser(@Valid @ModelAttribute(USER_UPDATE_DTO) UserUpdateDTO userDTO, BindingResult errors,
			RedirectAttributes attr) {
		if (errors.getAllErrors().size() == 0) {
			userService.update(userDTO);
		}
		return redirect(errors, REDIRECT_USER_PANEL, REDIRECT_USER_PANEL, USER_UPDATE_DTO, errors, attr);
	}

	/**
	 * 
	 * @param request
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	@RequestMapping("/remove/event/{idEvent}/{idUser}")
	public String updateUser(HttpServletRequest request, @PathVariable Long idEvent, @PathVariable Long idUser) {
		userService.removeEvent(idEvent, idUser);
		saveSucessMessage("Se ha dado de baja correctamente del evento.", request);
		return REDIRECT_USER_PANEL;
	}
}
