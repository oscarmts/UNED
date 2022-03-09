package es.uned.portalreuniones.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.uned.portalreuniones.constants.Session;
import es.uned.portalreuniones.exception.SessionNotFoundException;
import es.uned.portalreuniones.util.Sessions;

/**
 * Abstract Controller para métodos comunes a los controladores
 * 
 * @author omontes
 *
 */
public abstract class AbstractController {

	/**
	 * Redirección a la pantalla del login
	 */
	public static final String GO_LOGIN = "redirect:/";

	/**
	 * Redirección al panel de usuario
	 */
	public static final String REDIRECT_USER_PANEL = "redirect:/user/panel";

	/**
	 * DTO para Nuevo Usuario
	 */
	public static final String USER_DTO = "newUser";

	/**
	 * Éxito para saber si mostrar mensaje de éxito o no en la presentación
	 */
	public static final String SUCESS = "sucess";

	/**
	 * URL Event
	 */
	public static final String URL_EVENT = "URL_EVENT";

	/**
	 * Comprobar si es una sesión válida
	 * 
	 * @param request
	 * @throws SessionNotFoundException
	 */
	public void checkSession(HttpServletRequest request) throws SessionNotFoundException {
		if (!Sessions.isValidSession(request)) {
			throw new SessionNotFoundException("Session finalized!!");
		}
	}

	/**
	 * Datos comunes a la mayoría de las pantallas
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	public Model completeModelSession(HttpServletRequest request, Model model) {
		model.addAttribute("isAdmin", Sessions.getValue(request, Session.ADMIN));
		model.addAttribute("showAll", Sessions.getValue(request, Session.SHOW_ALL));
		model.addAttribute("showNewEvent", Sessions.getValue(request, Session.SHOW_NEW_EVENT));
		model.addAttribute("islogued", Sessions.isValidSession(request));
		return model;
	}

	/**
	 * Añadir nuevo error que mostrar en la presentación
	 * 
	 * @param errors
	 * @param errorMsg
	 * @return
	 */
	public BindingResult addError(BindingResult errors, String errorMsg) {
		ObjectError error = new ObjectError("error", errorMsg);
		errors.addError(error);
		return errors;
	}

	/**
	 * Ir a una página directamente
	 * 
	 * @param errors
	 * @param firstPlace
	 * @param secondPlace
	 * @return
	 */
	public String go(BindingResult errors, String firstPlace, String secondPlace) {
		return ((errors.hasErrors()) ? firstPlace : secondPlace);
	}

	/**
	 * Ir a una página por redireccionamiento
	 * 
	 * @param errors
	 * @param firstPlace
	 * @param secondPlace
	 * @param objectName
	 * @param dto
	 * @param attr
	 * @return
	 */
	public String redirect(BindingResult errors, String firstPlace, String secondPlace, String objectName, Object dto,
			RedirectAttributes attr) {
		attr.addFlashAttribute("org.springframework.validation.BindingResult.".concat(objectName), errors);
		attr.addFlashAttribute(objectName, dto);
		return ((errors.hasErrors()) ? firstPlace : secondPlace);
	}

	/**
	 * Guardado de mensaje de éxito con comprobación de errores
	 * 
	 * @param message
	 * @param request
	 * @param errors
	 */
	public void saveSucessMessage(String message, HttpServletRequest request, BindingResult errors) {
		if (errors.getAllErrors().size() == 0) {
			saveSucessMessage(message, request);
		}
	}

	/**
	 * Guardado de mensaje de éxito
	 * 
	 * @param message
	 * @param request
	 */
	public void saveSucessMessage(String message, HttpServletRequest request) {
		Sessions.setValue(request, SUCESS, message);
	}

	/**
	 * Guardado de mensaje de éxito con model
	 * 
	 * @param request
	 * @param model
	 */
	public void setSucessMessage(HttpServletRequest request, Model model) {
		if (Sessions.getValue(request, SUCESS) != null) {
			model.addAttribute(SUCESS, Sessions.getValue(request, SUCESS));
			Sessions.removeValue(request, SUCESS);
		}
	}

	public boolean isErrors(BindingResult errors) {
		return errors.getAllErrors().size() > 0;
	}

}
