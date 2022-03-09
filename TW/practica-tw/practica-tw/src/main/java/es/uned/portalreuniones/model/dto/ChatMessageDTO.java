package es.uned.portalreuniones.model.dto;

/**
 * DTO para los mensajes de chat
 * 
 * @author omontes
 *
 */
public class ChatMessageDTO {
	private MessageType type;
	private String content;
	private String sender;
	private Long idEvent;

	public enum MessageType {
		CHAT, JOIN, LEAVE
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

}
