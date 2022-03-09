package es.uned.portalreuniones.service.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.Room;
import es.uned.portalreuniones.model.room.RoomSchedule;

@SpringBootTest
public class RoomScheduleServiceTest {

	@Autowired
	private RoomScheduleService roomScheduleService;

	@Autowired
	private RoomService roomService;

	@DisplayName("Recuperar todos los horarios")
	@Test
	public void findAllTest() {
		List<RoomSchedule> roomSchedules = roomScheduleService.findAll();
		assertEquals(true, roomSchedules.size() > 0);

	}

	@DisplayName("Crear un horario")
	@Test
	public void saveTest() throws ElementNotFoundException {
		Room room = roomService.findById(1l);
		RoomSchedule roomSchedule = new RoomSchedule();
		roomSchedule.setHour(10);
		roomSchedule.setRoom(room);

		RoomSchedule roomScheduleSaved = roomScheduleService.save(roomSchedule);

		assertEquals(10, roomScheduleSaved.getHour());
		assertNotNull(roomScheduleSaved.getId(), "Se esperaba un ID");

	}

	@DisplayName("Borrado de un horario de la sala")
	@Test
	public void deleteTest() throws ElementNotFoundException {
		roomScheduleService.delete(1l);

		assertThrows(ElementNotFoundException.class, () -> {
			roomScheduleService.findById(1l);
		});

	}

}
