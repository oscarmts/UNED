package es.uned.portalreuniones.service.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.dto.EventPreferenceScheduleDTO;
import es.uned.portalreuniones.model.event.EventPreferenceSchedule;
import es.uned.portalreuniones.service.user.UserService;

@SpringBootTest
public class EventPreferenceScheduleServiceTest {

	@Autowired
	private EventPreferenceScheduleService eventPreferenceScheduleService;

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@DisplayName("Recuperar todas las preferencias de horario de un evento")
	@Test
	public void findAllTest() {
		List<EventPreferenceSchedule> events = eventPreferenceScheduleService.findAll();
		assertTrue(events.size() > 0);
	}

	@DisplayName("Encontrar una preferencia de horario por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		EventPreferenceSchedule event = eventPreferenceScheduleService.findById(1l);
		assertEquals(1l, event.getId());
	}

	@DisplayName("Encontrar una preferencia de horario por id del evento")
	@Test
	public void findByIdEventTest() throws ElementNotFoundException {
		List<EventPreferenceSchedule> eventPreferenceSchedules = eventPreferenceScheduleService.findByIdEvent(3l);
		assertTrue(eventPreferenceSchedules.size() > 0);
	}

	@DisplayName("Encontrar usuarios y sus horas de evento")
	@Test
	public void findUserWithHoursByIdEventTest() throws ElementNotFoundException {
		List<EventPreferenceScheduleDTO> list = eventPreferenceScheduleService.findUserWithHoursByIdEvent(3l);
		assertTrue(list.size() > 0);
	}

	@DisplayName("Encontrar usuarios y sus horas de evento")
	@Test

	public void findHoursFromOwnerTest() {
		List<Integer> hours = eventPreferenceScheduleService.findHoursFromOwner(1l);
		assertTrue(hours.size() > 0);
	}

	@DisplayName("Suscribir un usuario a un evento")
	@Test
	public void saveEvent() throws ElementNotFoundException {
		EventPreferenceSchedule eventPreferenceSchedule = new EventPreferenceSchedule();
		eventPreferenceSchedule.setUser(userService.findById(3l));
		eventPreferenceSchedule.setEvent(eventService.findById(1l));
		eventPreferenceSchedule.setHour(10);

		EventPreferenceSchedule eventSaved = eventPreferenceScheduleService.save(eventPreferenceSchedule);
		assertEquals(3l, eventSaved.getUser().getId());
	}

	@DisplayName("Crear preferencia de evento de un usuario")
	@Test
	public void createTest() {
		EventPreferenceScheduleDTO dto = new EventPreferenceScheduleDTO();
		dto.setHours(new int[] { 10 });
		dto.setIdUser(2l);
		dto.setIdEvent(4l);
		List<EventPreferenceSchedule> list = eventPreferenceScheduleService.create(dto);
		assertEquals(1, list.size());
	}

}
