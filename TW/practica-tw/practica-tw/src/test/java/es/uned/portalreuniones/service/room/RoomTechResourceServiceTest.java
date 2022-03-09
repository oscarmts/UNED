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
import es.uned.portalreuniones.model.room.RoomTechResource;

@SpringBootTest
public class RoomTechResourceServiceTest {

	@Autowired
	private RoomTechResourceService roomTechResourceService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private TechResourceService techResourceService;

	@DisplayName("Recuperar todos los médios técnicos")
	@Test
	public void findAllTest() {
		List<RoomTechResource> techResources = roomTechResourceService.findAll();
		assertEquals(true, techResources.size() > 0);

	}

	@DisplayName("Crear un medio técnico")
	@Test
	public void saveTest() throws ElementNotFoundException {
		RoomTechResource roomTechResource = new RoomTechResource();
		roomTechResource.setTechResource(techResourceService.findById(1l));
		roomTechResource.setRoom(roomService.findById(1l));

		RoomTechResource roomTechResourceSaved = roomTechResourceService.save(roomTechResource);

		assertEquals("Presentación", roomTechResourceSaved.getTechResource().getDescription());
		assertNotNull(roomTechResourceSaved.getId(), "Se esperaba un ID");

	}

	@DisplayName("Borrado de un medio técnico")
	@Test
	public void deleteTest() throws ElementNotFoundException {
		roomTechResourceService.delete(1l);

		assertThrows(ElementNotFoundException.class, () -> {
			roomTechResourceService.findById(1l);
		});

	}

}
