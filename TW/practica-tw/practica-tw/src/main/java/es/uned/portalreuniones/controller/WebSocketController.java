package es.uned.portalreuniones.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import es.uned.portalreuniones.exception.ForbiddenException;
import es.uned.portalreuniones.model.dto.ChatMessageDTO;
import es.uned.portalreuniones.service.event.EventMessageChatService;

/**
 * Controlador para el web socket
 * 
 * @author omontes
 *
 */
@Controller
public class WebSocketController {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketController.class);

	@Autowired
	private EventMessageChatService eventMessageChatService;

	/**
	 * Publicar un mensaje en el chat
	 * 
	 * @param chatMessage
	 * @param idEvent
	 * @return
	 */
	@MessageMapping("/chat.sendMessage/{idEvent}")
	@SendTo("/topic/publicChatRoom/{idEvent}")
	public ChatMessageDTO sendMessage(@Payload ChatMessageDTO chatMessage, @DestinationVariable Long idEvent) {
		logger.info("Enviando mensaje al evento:: {}", idEvent);
		chatMessage.setIdEvent(idEvent);
		try {
			eventMessageChatService.saveMessage(chatMessage);
		} catch (ForbiddenException e) {
			logger.warn(e.getMessage());
		}

		return chatMessage;
	}

	/**
	 * Entrada de un usuario en el chat
	 * 
	 * @param chatMessage
	 * @param idEvent
	 * @param headerAccessor
	 * @return
	 */
	@MessageMapping("/chat.addUser/{idEvent}")
	@SendTo("/topic/publicChatRoom/{idEvent}")
	public ChatMessageDTO addUser(@Payload ChatMessageDTO chatMessage, @DestinationVariable Long idEvent,
			SimpMessageHeaderAccessor headerAccessor) {
		logger.info("Add usuario al evento:: {}", idEvent);
		// Add username in web socket session
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}
