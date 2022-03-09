package es.uned.portalreuniones.model.room;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;

/**
 * Entity de la tabla ROOM_TECH_RESOURCE
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "ROOM_TECH_RESOURCE")
public class RoomTechResource extends EntityIdentifiable {

	@ManyToOne
	@JoinColumn(name = "TECH_RESOURCE_ID")
	private TechResource techResource;

	@ManyToOne
	@JoinColumn(name = "ROOM_ID", nullable = false)
	private Room room;

	public TechResource getTechResource() {
		return techResource;
	}

	public void setTechResource(TechResource techResource) {
		this.techResource = techResource;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

}
