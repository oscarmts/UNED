package es.uned.portalreuniones.model.room;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import es.uned.portalreuniones.model.common.EntityIdentifiable;
import es.uned.portalreuniones.model.event.Event;

/**
 * Entity de la tabla ROOM
 * 
 * @author omontes
 *
 */
@Entity
public class Room extends EntityIdentifiable {

	private String name;

	private Integer capacity;

	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	Set<RoomSchedule> roomSchedules;

	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	Set<RoomTechResource> technicalResources;

	@OneToMany(mappedBy = "room", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	Set<Event> events;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Set<RoomSchedule> getRoomSchedules() {
		return roomSchedules;
	}

	public void setRoomSchedules(Set<RoomSchedule> roomSchedules) {
		this.roomSchedules = roomSchedules;
	}

	public Set<RoomTechResource> getTechnicalResources() {
		return technicalResources;
	}

	public void setTechnicalResources(Set<RoomTechResource> technicalResources) {
		this.technicalResources = technicalResources;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

	public String getHoursToString() {
		StringBuffer sb = new StringBuffer();
		if (getRoomSchedules() != null) {
			SortedSet<Integer> sortedSet = new TreeSet<Integer>();
			// Ordenamos las horas
			for (RoomSchedule roomSchedule : roomSchedules) {
				sortedSet.add(roomSchedule.getHour());
			}
			for (Integer hour : sortedSet) {
				sb.append(hour).append(":00").append(", ");
			}
			sb.delete(sb.length() - 2, sb.length() - 1);
		}
		return sb.toString().trim();
	}

}
