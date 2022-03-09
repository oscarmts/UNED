package es.uned.portalreuniones.util;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Dates {

	public static Date now() {
		return new Date(System.currentTimeMillis());
	}

	public static boolean isValidDate(Date date) {
		// create a java calendar instance
		Calendar calendar = Calendar.getInstance();

		// get a java date (java.util.Date) from the Calendar instance.
		// this java date will represent the current date, or "now".
		java.util.Date currentDate = calendar.getTime();

		// now, create a java.sql.Date from the java.util.Date
		String[] todayArray = new java.sql.Date(currentDate.getTime()).toString().split("-");
		String[] dateArray = date.toString().split("-");

		return todayArray[0].equals(dateArray[0]) && todayArray[1].equals(dateArray[1])
				&& todayArray[2].equals(dateArray[2]);

	}

	public static boolean isValidTime(Integer hour) {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		String[] timeNow = formatter.format(date).split(":");
		Integer hourNow = Integer.valueOf(timeNow[0]);
		Integer minuteNow = Integer.valueOf(timeNow[1]);
		Integer diferentHours = hourNow - hour;
		Integer diferenteMinutes = 60 - minuteNow;
		// Estamos en la hora exacta
		if (diferentHours == 0) {
			return true;
		} else {
			// Estamos entrando una hora antes
			if (diferentHours == -1) {
				// Entonces comprobamos los minutos
				if (diferenteMinutes <= 5) {
					return true;
				}
			} else {
				// Ni es la hora exacta ni una hora antes
				return false;
			}
		}
		return false;
	}

}
