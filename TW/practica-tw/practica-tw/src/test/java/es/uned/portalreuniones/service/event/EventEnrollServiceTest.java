package es.uned.portalreuniones.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventEnroll;
import es.uned.portalreuniones.service.user.UserService;

@SpringBootTest
public class EventEnrollServiceTest {

	@Autowired
	private EventEnrollService eventEnrollService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@DisplayName("Recuperar todos los inscritos a eventos")
	@Test
	public void findAllTest() {
		List<EventEnroll> events = eventEnrollService.findAll();
		assertTrue(events.size() > 0);
	}

	@DisplayName("Encontrar una suscripción de evento por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		EventEnroll event = eventEnrollService.findById(1l);
		assertEquals(1l, event.getId());
	}

	@DisplayName("Búsqueda de eventos donde un usuario está suscrito")
	@Test
	public void findByIdUser() {
		List<EventEnroll> eventsEnroll = eventEnrollService.findByIdUser(3l);
		assertTrue(eventsEnroll.size() > 0);
	}

	@DisplayName("Suscribir un usuario a un evento")
	@Test
	public void saveEvent() throws ElementNotFoundException {
		EventEnroll eventEnroll = new EventEnroll();
		eventEnroll.setUser(userService.findById(3l));
		eventEnroll.setEvent(eventService.findById(2l));
		EventEnroll eventSaved = eventEnrollService.save(eventEnroll);
		assertEquals(3l, eventSaved.getUser().getId());
	}

}
