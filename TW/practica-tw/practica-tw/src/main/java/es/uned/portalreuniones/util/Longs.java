package es.uned.portalreuniones.util;

/**
 * Clase de utilidades de para java.lang.Long
 * 
 * @author omontes
 *
 */
public class Longs {

	public static boolean isEmpty(Long longValue) {
		return longValue != null;
	}

	public static boolean isNotEmpty(Long longValue) {
		return !isEmpty(longValue);
	}
}
