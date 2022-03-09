package es.uned.portalreuniones.service.event;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.ForbiddenException;
import es.uned.portalreuniones.model.dto.ChatMessageDTO;
import es.uned.portalreuniones.model.event.EventMessageChat;

/**
 * Gestión de los mensajes de chat de los eventos
 * 
 * @author omontes
 *
 */
public interface EventMessageChatService {

	/**
	 * Recuperación de un mensaje de chat por su identificador
	 * 
	 * @param idEventMessageChat
	 * @return
	 * @throws ElementNotFoundException
	 */
	EventMessageChat findById(Long idEventMessageChat) throws ElementNotFoundException;

	/**
	 * Recuperación de los mensajes de chat de un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventMessageChat> findByIdEvent(Long idEvent);

	/**
	 * Guardado un mensaje escrito desde el chat del front
	 * 
	 * @param chatMessageDTO
	 * @return
	 * @throws ForbiddenException
	 */
	EventMessageChat saveMessage(ChatMessageDTO chatMessageDTO) throws ForbiddenException;

	/**
	 * Guardado de mensaje de chat en un evento
	 * 
	 * @param eventMessageChat
	 * @return
	 */
	EventMessageChat save(EventMessageChat eventMessageChat);

	/**
	 * Eliminación de un mensaje
	 * 
	 * @param idMessage
	 */
	void delete(Long idMessage);
}
