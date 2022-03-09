package es.uned.portalreuniones.model.event;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;
import es.uned.portalreuniones.model.user.User;

/**
 * Entity de la tabla EVENT_INVITED
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "EVENT_INVITED")
public class EventInvited extends EntityIdentifiable {

	@ManyToOne
	@JoinColumn(name = "EVENT_ID", nullable = false)
	private Event event;

	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	private Boolean enrolled;

	private Boolean voted;

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

	public Boolean getEnrolled() {
		return enrolled;
	}

	public void setEnrolled(Boolean enrolled) {
		this.enrolled = enrolled;
	}

	public Boolean getVoted() {
		return voted;
	}

	public void setVoted(Boolean voted) {
		this.voted = voted;
	}

}
