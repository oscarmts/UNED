package es.uned.portalreuniones.model.user;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;
import es.uned.portalreuniones.model.event.Event;

/**
 * Entity de la tabla USER_NOT_REGISTERED
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "USER_NOT_REGISTERED")
public class UserNotRegistered extends EntityIdentifiable {

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "EVENT_ID", nullable = false)
	private Event event;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
