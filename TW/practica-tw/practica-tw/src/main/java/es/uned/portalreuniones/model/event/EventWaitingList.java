package es.uned.portalreuniones.model.event;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;
import es.uned.portalreuniones.model.user.User;

/**
 * Entity de la tabla EVENT_WAITING_LIST
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "EVENT_WAITING_LIST")
public class EventWaitingList extends EntityIdentifiable {

	@OneToOne
	@JoinColumn(name = "EVENT_ID")
	private Event event;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User user;

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
