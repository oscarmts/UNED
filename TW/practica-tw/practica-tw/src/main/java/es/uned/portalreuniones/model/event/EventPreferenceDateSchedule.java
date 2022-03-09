package es.uned.portalreuniones.model.event;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;

/**
 * Entity de la tabla EVENT_PREFERENCE_DATE_SCHEDULE
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "EVENT_PREFERENCE_DATE_SCHEDULE")
public class EventPreferenceDateSchedule extends EntityIdentifiable {

	private Date date;

	@ManyToOne
	@JoinColumn(name = "EVENT_ID", nullable = false)
	private Event event;

	public Date getDate() {
		return date;
	}

	public String getDateFormated() {
		SimpleDateFormat simpDate = new SimpleDateFormat("dd/MM/yyyy");
		return simpDate.format(date);
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

}
