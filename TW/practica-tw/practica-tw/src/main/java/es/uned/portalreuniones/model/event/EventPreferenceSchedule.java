package es.uned.portalreuniones.model.event;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;
import es.uned.portalreuniones.model.user.User;

/**
 * Entity de la tabla EVENT_PREFERENCE_SCHEDULE
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "EVENT_PREFERENCE_SCHEDULE")
public class EventPreferenceSchedule extends EntityIdentifiable {

	private Date date;

	private Integer hour;

	@ManyToOne
	@JoinColumn(name = "EVENT_ID", nullable = false)
	private Event event;

	@OneToOne
	@JoinColumn(name = "USER_ID", nullable = false)
	private User user;

	@Column(name = "OWNER_HOUR")
	private boolean isOwnerHours;

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
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

	public boolean isOwnerHours() {
		return isOwnerHours;
	}

	public void setOwnerHours(boolean isOwnerHours) {
		this.isOwnerHours = isOwnerHours;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
