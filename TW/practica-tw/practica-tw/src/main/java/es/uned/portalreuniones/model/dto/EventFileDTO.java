package es.uned.portalreuniones.model.dto;

import org.springframework.web.multipart.MultipartFile;

/**
 * DTO para los ficheros de los eventos
 * 
 * @author omontes
 *
 */
public class EventFileDTO {

	private Long idEvent;
	private MultipartFile multipartFile;

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

}
