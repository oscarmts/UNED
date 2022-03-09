package es.uned.portalreuniones.util;

/**
 * Clase de utilidades para java.lang.String
 * 
 * @author omontes
 *
 */
public class Strings {

	public static boolean isEmpty(String value) {
		return value != null && value.trim().length() > 0;
	}

	public static boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}
}
