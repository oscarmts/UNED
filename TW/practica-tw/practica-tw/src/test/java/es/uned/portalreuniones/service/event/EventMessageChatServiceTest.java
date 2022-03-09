package es.uned.portalreuniones.service.event;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.model.event.EventMessageChat;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.service.user.UserService;

@SpringBootTest
public class EventMessageChatServiceTest {

	@Autowired
	private EventMessageChatService eventMessageChatService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@DisplayName("Buscar mensaje por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		assertNotNull(eventMessageChatService.findById(1l));
	}

	@DisplayName("Buscar mensajes de un evento")
	@Test
	public void findByIdEventTest() {
		assertTrue(eventMessageChatService.findByIdEvent(2l).size() > 0);
	}

	@DisplayName("Guardar mensajes de un evento")
	@Test
	public void saveTest() throws ElementNotFoundException {
		User user = userService.findById(1l);
		Event event = eventService.findById(1l);

		EventMessageChat message = new EventMessageChat();
		Date date = new Date();
		message.setDate(date);
		message.setMessage("Hola");
		message.setUser(user);
		message.setEvent(event);

		assertNotNull(eventMessageChatService.save(message));
	}
}
