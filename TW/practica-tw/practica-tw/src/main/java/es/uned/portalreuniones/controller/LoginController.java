package es.uned.portalreuniones.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.uned.portalreuniones.constants.Session;
import es.uned.portalreuniones.exception.UserNotFoundException;
import es.uned.portalreuniones.model.dto.LoginDTO;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.service.user.LoginService;
import es.uned.portalreuniones.util.Sessions;

/**
 * Controlador para la pantalla de logueo
 * 
 * @author omontes
 *
 */
@Controller
public class LoginController extends AbstractController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	/**
	 * Redirección a la pantalla de logueo
	 */
	public static final String REDIRECT_LOGIN = "redirect:/";

	/**
	 * Ir a la pantalla de logueo
	 */
	public static final String INICIO = "index";

	@Autowired
	private LoginService loginService;

	/**
	 * Ir a la pantalla de logueo
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping({ "/" })
	public String index(Model model) {
		model.addAttribute("login", new LoginDTO());
		return INICIO;
	}

	/**
	 * Entrar un usuario en el sistema
	 * 
	 * @param login
	 * @param errors
	 * @param request
	 * @param model
	 * @return
	 */
	@PostMapping("/login")
	public String loginUser(@Valid @ModelAttribute("login") LoginDTO login, BindingResult errors,
			HttpServletRequest request, Model model) {
		logger.info("Se intenta loguear el usuario {}", login.getNickname());
		if (!errors.hasErrors()) {
			logger.info("Sin errores");
			try {
				User user = loginService.loginUser(login.getNickname(), login.getPassword());
				logger.info("Usuario logueado encontrado:: {}", user);

				Sessions.setValue(request, Session.ID_USER, user.getId());
				Sessions.setValue(request, Session.USERNAME, user.getNickName());
				Sessions.setValue(request, Session.USERNAME, user.getNickName());
				Sessions.setValue(request, Session.ADMIN, user.isAdmin() || user.isJP());
				Sessions.setValue(request, Session.SHOW_ALL, user.isAdmin());
				Sessions.setValue(request, Session.SHOW_NEW_EVENT, user.isJP());
				model.addAttribute("user", user);
				model.addAttribute("isAdmin", user.isAdmin());
			} catch (UserNotFoundException e) {
				logger.error("Se sale por la excepción: {} ", e.getMessage());
				addError(errors, e.getMessage());
			}
		}
		return go(errors, INICIO, REDIRECT_USER_PANEL);
	}

	/**
	 * Salir un usuario del sistema
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(path = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(true).invalidate();
		return REDIRECT_LOGIN;
	}
}
