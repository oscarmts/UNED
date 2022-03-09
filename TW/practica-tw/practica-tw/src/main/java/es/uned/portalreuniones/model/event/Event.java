package es.uned.portalreuniones.model.event;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import es.uned.portalreuniones.model.common.EntityIdentifiable;
import es.uned.portalreuniones.model.room.Room;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.model.user.UserNotRegistered;

/**
 * Entity de la tabla EVENT
 * 
 * @author omontes
 *
 */
@Entity
public class Event extends EntityIdentifiable {

	private Date date;

	private Integer hour;

	private String topic;

	@OneToOne
	@JoinColumn(name = "ROOM_ID", nullable = false)
	private Room room;

	@OneToOne
	@JoinColumn(name = "OWER_ID", nullable = false)
	private User owner;

	private String token;

	private Boolean configured;

	private Boolean finished;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<EventFile> files;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<EventEnroll> enrolls;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<EventWaitingList> waitingList;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<EventPreferenceSchedule> preferenceSchedule;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<EventInvited> userInvited;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<UserNotRegistered> userNotRegisteredInvited;

	@OneToMany(mappedBy = "event", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<EventMessageChat> messagesChat;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDateFormated() {
		SimpleDateFormat simpDate = new SimpleDateFormat("dd/MM/yyyy");
		return simpDate.format(date);
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public String getHourFormated() {
		return hour + ":00";
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

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Set<EventFile> getFiles() {
		return files;
	}

	public void setFiles(Set<EventFile> files) {
		this.files = files;
	}

	public Set<EventEnroll> getEnrolls() {
		return enrolls;
	}

	public void setEnrolls(Set<EventEnroll> enrolls) {
		this.enrolls = enrolls;
	}

	public Set<EventWaitingList> getWaitingList() {
		return waitingList;
	}

	public void setWaitingList(Set<EventWaitingList> waitingList) {
		this.waitingList = waitingList;
	}

	public Set<EventPreferenceSchedule> getPreferenceSchedule() {
		return preferenceSchedule;
	}

	public void setPreferenceSchedule(Set<EventPreferenceSchedule> preferenceSchedule) {
		this.preferenceSchedule = preferenceSchedule;
	}

	public Set<EventInvited> getUserInvited() {
		return userInvited;
	}

	public void setUserInvited(Set<EventInvited> userInvited) {
		this.userInvited = userInvited;
	}

	public Set<UserNotRegistered> getUserNotRegisteredInvited() {
		return userNotRegisteredInvited;
	}

	public void setUserNotRegisteredInvited(Set<UserNotRegistered> userNotRegisteredInvited) {
		this.userNotRegisteredInvited = userNotRegisteredInvited;
	}

	public Set<EventMessageChat> getMessagesChat() {
		return messagesChat;
	}

	public void setMessagesChat(Set<EventMessageChat> messagesChat) {
		this.messagesChat = messagesChat;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public Boolean getConfigured() {
		return configured;
	}

	public void setConfigured(Boolean configured) {
		this.configured = configured;
	}

	public Boolean getFinished() {
		return finished;
	}

	public void setFinished(Boolean finished) {
		this.finished = finished;
	}

}
