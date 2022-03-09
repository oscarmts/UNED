package es.uned.portalreuniones.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.uned.portalreuniones.constants.Session;

/**
 * Clase de utilidades para la sessión
 * 
 * @author omontes
 *
 */
public class Sessions {

	private static final Logger logger = LoggerFactory.getLogger(Sessions.class);

	public static boolean isValidSession(HttpServletRequest request) {
		logger.info(":::Validando sesión usuario::: {}", request.getSession().getAttribute(Session.ID_USER));
		if (getValue(request, Session.ID_USER) != null)
			return true;
		return false;
	}

	public static boolean contains(HttpServletRequest request, String attribute) {
		return getValue(request, attribute) != null ? true : false;
	}

	public static Object getValue(HttpServletRequest request, String attribute) {
		return request.getSession().getAttribute(attribute);
	}

	public static void setValue(HttpServletRequest request, String attribute, Object value) {
		request.getSession().setAttribute(attribute, value);
	}

	public static void removeValue(HttpServletRequest request, String attribute) {
		request.getSession().removeAttribute(attribute);
	}
}
