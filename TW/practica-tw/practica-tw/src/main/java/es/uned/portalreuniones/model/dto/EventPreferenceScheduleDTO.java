package es.uned.portalreuniones.model.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.validation.constraints.NotEmpty;

import es.uned.portalreuniones.model.user.User;

/**
 * DTO para la votación de las preferencias de horario
 * 
 * @author omontes
 *
 */
public class EventPreferenceScheduleDTO {

	private Long idEvent;

	private Date date;

	private Date[] dates;

	@NotEmpty
	private int[] hours;

	private Long idUser;
	private User user;
	private List<Integer> hoursList;

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

	public int[] getHours() {
		return hours;
	}

	public void setHours(int[] hours) {
		this.hours = hours;
	}

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public Date getDate() {
		return date;
	}

	public String getDateFormated() {
		if (date != null) {
			SimpleDateFormat simpDate = new SimpleDateFormat("dd/MM/yyyy");
			return simpDate.format(date);
		} else {
			return "";
		}
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Integer> getHoursList() {
		return hoursList;
	}

	public void setHoursList(List<Integer> hoursList) {
		this.hoursList = hoursList;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] dates) {
		this.dates = dates;
	}

	public String getHoursToString() {
		StringBuffer sb = new StringBuffer();
		if (getHoursList() != null) {
			SortedSet<Integer> sortedSet = new TreeSet<Integer>();
			// Ordenamos las horas
			for (Integer hour : hoursList) {
				sortedSet.add(hour);
			}
			for (Integer hour : sortedSet) {
				sb.append(hour).append(":00").append(", ");
			}
			sb.delete(sb.length() - 2, sb.length() - 1);
		}
		return sb.toString();
	}

}
