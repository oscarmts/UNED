package es.uned.portalreuniones.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventInvited;
import es.uned.portalreuniones.service.user.UserService;

@SpringBootTest
public class EventInvitedServiceTest {

	@Autowired
	private EventInvitedService eventInvitedService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@DisplayName("Invitar a un usuario a un evento")
	@Test
	public void saveTest() throws ElementNotFoundException {
		EventInvited eventInvited = new EventInvited();
		eventInvited.setUser(userService.findById(3l));
		eventInvited.setEvent(eventService.findById(2l));
		eventInvited.setVoted(false);
		EventInvited eventSaved = eventInvitedService.save(eventInvited);
		assertEquals(3l, eventSaved.getUser().getId());
	}

	@DisplayName("Recuperación de eventos a los que ha sido invitado")
	@Test
	public void findEventInvitedByIdUserTest() throws ElementNotFoundException {
		EventInvited eventInvited = new EventInvited();
		eventInvited.setUser(userService.findById(3l));
		eventInvited.setEvent(eventService.findById(2l));
		eventInvited.setVoted(false);
		eventInvitedService.save(eventInvited);
		List<EventInvited> eventInvitedList = eventInvitedService.findEventInvitedByIdUser(3l);
		assertTrue(eventInvitedList.size() > 0);
	}

	@DisplayName("Búsqueda de invitados por evento")
	@Test
	public void findEventInvitedByIdEventTest() throws ElementNotFoundException {
		EventInvited eventInvited = new EventInvited();
		eventInvited.setUser(userService.findById(3l));
		eventInvited.setEvent(eventService.findById(3l));
		eventInvited.setVoted(false);
		eventInvitedService.save(eventInvited);
		List<EventInvited> eventsInvited = eventInvitedService.findEventInvitedWithoutVoteByIdEvent(3l);
		assertTrue(eventsInvited.size() > 0);
	}

	@DisplayName("Borrada invitación de evento")
	@Test
	public void deleteByIdEventTest() throws ElementNotFoundException {
		EventInvited eventInvited = new EventInvited();
		eventInvited.setUser(userService.findById(3l));
		eventInvited.setEvent(eventService.findById(3l));
		eventInvited.setVoted(false);
		eventInvitedService.save(eventInvited);
		List<EventInvited> eventsInvitedBeforeDelete = eventInvitedService.findEventInvitedWithoutVoteByIdEvent(3l);

		assertFalse(eventsInvitedBeforeDelete.isEmpty());

		eventInvitedService.deleteByIdEvent(3l, 3l);

		List<EventInvited> eventsInvitedAfterDeleter = eventInvitedService.findEventInvitedWithoutVoteByIdEvent(3l);

		assertTrue(eventsInvitedAfterDeleter.isEmpty());

	}
}
