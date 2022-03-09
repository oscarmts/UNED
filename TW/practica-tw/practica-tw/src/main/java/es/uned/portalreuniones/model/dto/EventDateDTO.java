package es.uned.portalreuniones.model.dto;

import java.sql.Date;

import javax.validation.constraints.NotNull;

/**
 * DTO para las fechas de los eventos
 * 
 * @author omontes
 *
 */
public class EventDateDTO {

	private Long idEvent;

	private Date date;

	@NotNull
	private Integer hour;

	public EventDateDTO() {
		super();
	}

	public EventDateDTO(Date date, Integer hour) {
		super();
		this.date = date;
		this.hour = hour;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

}
