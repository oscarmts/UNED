package es.uned.portalreuniones.service.room;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.RoomNameFoundException;
import es.uned.portalreuniones.model.dto.RoomDTO;
import es.uned.portalreuniones.model.room.Room;
import es.uned.portalreuniones.model.room.RoomSchedule;
import es.uned.portalreuniones.model.room.RoomTechResource;

@SpringBootTest
public class RoomServiceTest {

	@Autowired
	private RoomService roomService;

	@Autowired
	private TechResourceService techResourceService;

	@DisplayName("Recuperar todas las salas")
	@Test
	public void findAllTest() {
		List<Room> rooms = roomService.findAll();
		assertTrue(rooms.size() > 0);

	}

	@DisplayName("Crear una sala")
	@Test
	public void saveTest() throws ElementNotFoundException {
		Room room = new Room();
		room.setCapacity(10);
		Room roomSaved = roomService.save(room);

		Room newRoom = roomService.findById(roomSaved.getId());

		assertNotNull(newRoom.getId(), "Se esperaba un ID");
		assertEquals(10, room.getCapacity());

	}

	@DisplayName("Crear una sala completa")
	@Test
	public void saveCompleteRoom() throws ElementNotFoundException {
		Room room = new Room();
		room.setCapacity(10);

		Room roomSaved = roomService.save(room);

		Set<RoomSchedule> roomScheduleList = new HashSet<RoomSchedule>();
		RoomSchedule roomSchedule1 = new RoomSchedule();
		roomSchedule1.setHour(10);
		roomSchedule1.setRoom(roomSaved);
		roomScheduleList.add(roomSchedule1);
		RoomSchedule roomSchedule2 = new RoomSchedule();
		roomSchedule2.setHour(11);
		roomSchedule2.setRoom(roomSaved);
		roomScheduleList.add(roomSchedule2);
		room.setRoomSchedules(roomScheduleList);

		Set<RoomTechResource> roomTechResourcesList = new HashSet<RoomTechResource>();
		RoomTechResource roomTechResource = new RoomTechResource();
		roomTechResource.setTechResource(techResourceService.findById(1l));
		roomTechResource.setRoom(roomSaved);
		roomTechResourcesList.add(roomTechResource);

		room.setTechnicalResources(roomTechResourcesList);

		Room roomComplete = roomService.save(roomSaved);

		assertEquals(2, roomComplete.getRoomSchedules().size());
		assertEquals(1, roomComplete.getTechnicalResources().size());
	}

	@DisplayName("Creación de una habitación")
	@Test
	public void createRoom() throws RoomNameFoundException {
		RoomDTO roomDTO = new RoomDTO();

		Room room = new Room();
		room.setName("Habitacion de prueba");
		room.setCapacity(20);
		roomDTO.setName("Habitación");
		roomDTO.setTechResources(new int[] { 1, 2 });
		roomDTO.setHours(new int[] { 8, 9 });
		roomDTO.setCapacity("10");
		Room roomCreated = roomService.createRoom(roomDTO);

		assertNotNull(roomCreated);
	}

	@DisplayName("Borrado de una sala")
	@Test
	public void deleteTest() throws ElementNotFoundException {
		roomService.delete(2l);

		assertThrows(ElementNotFoundException.class, () -> {
			roomService.findById(2l);
		});

	}

}
