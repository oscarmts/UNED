package es.uned.portalreuniones.model.event;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.uned.portalreuniones.model.common.EntityIdentifiable;

/**
 * Entity de la tabla EVENT_FILE
 * 
 * @author omontes
 *
 */
@Entity
@Table(name = "EVENT_FILE")
public class EventFile extends EntityIdentifiable {

	@OneToOne
	@JoinColumn(name = "EVENT_ID")
	private Event event;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

}
