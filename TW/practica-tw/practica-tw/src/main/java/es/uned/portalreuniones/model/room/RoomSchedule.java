package es.uned.portalreuniones.model.room;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;

/**
 * Entity de la tabla ROOM_SCHEDULE
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "ROOM_SCHEDULE")
public class RoomSchedule extends EntityIdentifiable {

	private Integer hour;

	@ManyToOne
	@JoinColumn(name = "ROOM_ID", nullable = false)
	private Room room;

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
