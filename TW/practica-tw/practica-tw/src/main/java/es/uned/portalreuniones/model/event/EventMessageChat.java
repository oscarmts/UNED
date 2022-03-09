package es.uned.portalreuniones.model.event;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;
import es.uned.portalreuniones.model.user.User;

/**
 * Entity de la tabla EVENT_MESSAGE_CHAT
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "EVENT_MESSAGE_CHAT")
public class EventMessageChat extends EntityIdentifiable {

	private Date date;

	private String message;

	@OneToOne
	@JoinColumn(name = "EVENT_ID")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateFormated() {
		SimpleDateFormat simpDate = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		return simpDate.format(date);

	}

	public String getHour() {
		SimpleDateFormat simpDate = new SimpleDateFormat("hh:mm:ss");
		return simpDate.format(date);

	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
