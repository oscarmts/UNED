package es.uned.portalreuniones.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventWaitingList;
import es.uned.portalreuniones.service.user.UserService;

@SpringBootTest
public class EventWaitingListServiceTest {

	@Autowired
	private EventWaitingListService eventWaitingListService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@DisplayName("Recuperar todos los que están en una lista de espera a eventos")
	@Test
	public void findAllTest() {
		List<EventWaitingList> eventWaitingList = eventWaitingListService.findAll();
		assertTrue(eventWaitingList.size() > 0);
	}

	@DisplayName("Encontrar una persona en lista de espera de evento por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		EventWaitingList eventWaitingList = eventWaitingListService.findById(1l);
		assertEquals(1l, eventWaitingList.getId());
	}

	@DisplayName("Suscribir un usuario a lista de espera de un evento")
	@Test
	public void saveEvent() throws ElementNotFoundException {
		EventWaitingList eventWaitingList = new EventWaitingList();
		eventWaitingList.setUser(userService.findById(3l));
		eventWaitingList.setEvent(eventService.findById(2l));
		EventWaitingList eventSaved = eventWaitingListService.save(eventWaitingList);
		assertEquals(3l, eventSaved.getUser().getId());
	}

}
