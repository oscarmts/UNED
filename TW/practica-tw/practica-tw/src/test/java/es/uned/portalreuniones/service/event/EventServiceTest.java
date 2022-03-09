package es.uned.portalreuniones.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.dto.EventDTO;
import es.uned.portalreuniones.model.dto.EventDateDTO;
import es.uned.portalreuniones.model.event.Event;
import es.uned.portalreuniones.service.room.RoomService;
import es.uned.portalreuniones.service.user.UserService;

@SpringBootTest
public class EventServiceTest {

	@Autowired
	private EventService eventService;

	@Autowired
	private UserService userService;

	@Autowired
	private RoomService roomService;

	@DisplayName("Recuperar todos los eventos")
	@Test
	public void findAllTest() {
		List<Event> events = eventService.findAll();
		assertTrue(events.size() > 0);
	}

	@DisplayName("Encontrar un evento por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		Event event = eventService.findById(1l);
		assertEquals(1l, event.getId());
	}

	@DisplayName("Encontrar un evento por id")
	@Test
	public void findByIdRoomTest() throws ElementNotFoundException {
		List<Event> events = eventService.findByIdRoom(2l);
		assertTrue(events.size() > 0);
	}

	@DisplayName("Recuperar la hora más votada")
	@Test
	public void getEventHourTest() {
		EventDateDTO eventDateDTO = eventService.configureDateEvent(5l);
		assertEquals(10, eventDateDTO.getHour());
	}

	@DisplayName("Guardar evento")
	@Test
	public void saveEvent() throws ElementNotFoundException {
		Event event = new Event();
		event.setOwner(userService.findById(1l));
		event.setRoom(roomService.findById(1l));

		Event eventSaved = eventService.save(event);
		assertEquals(1l, eventSaved.getOwner().getId());
	}

	@DisplayName("Crear evento")
	@Test
	public void createEventTest() throws ElementNotFoundException {
		EventDTO eventDTO = new EventDTO();
		eventDTO.setRoom(roomService.findById(1l));
		eventDTO.setHours(new int[] { 10, 11, 12 });
		eventDTO.setUsers(new Integer[] { 3, 4 });
		eventDTO.setOwner(userService.findById(1l));
		Event eventPersist = eventService.createEvent(eventDTO);

		assertNotNull(eventPersist);
	}

}
