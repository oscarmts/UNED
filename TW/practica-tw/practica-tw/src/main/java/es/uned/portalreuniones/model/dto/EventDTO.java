package es.uned.portalreuniones.model.dto;

import java.sql.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import es.uned.portalreuniones.model.room.Room;
import es.uned.portalreuniones.model.user.User;

/**
 * DTO para los eventos
 * 
 * @author omontes
 *
 */
public class EventDTO {

	@NotEmpty
	private Date[] dates;

	@NotEmpty
	@NotBlank
	private String topic;

	private Integer[] users;

	private Integer[] usersSelected;

	private Integer[] waitingList;

	private Integer[] waitingListSelected;
	@NotEmpty
	private int[] hours;

	private Room room;
	private User owner;

	public Integer[] getUsers() {
		return users;
	}

	public void setUsers(Integer[] users) {
		this.users = users;
	}

	public int[] getHours() {
		return hours;
	}

	public void setHours(int[] hours) {
		this.hours = hours;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Integer[] getUsersSelected() {
		return usersSelected;
	}

	public void setUsersSelected(Integer[] usersSelected) {
		this.usersSelected = usersSelected;
	}

	public Integer[] getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(Integer[] waitingList) {
		this.waitingList = waitingList;
	}

	public Integer[] getWaitingListSelected() {
		return waitingListSelected;
	}

	public void setWaitingListSelected(Integer[] waitingListSelected) {
		this.waitingListSelected = waitingListSelected;
	}

	public Date[] getDates() {
		return dates;
	}

	public void setDates(Date[] date) {
		this.dates = date;
	}

}
