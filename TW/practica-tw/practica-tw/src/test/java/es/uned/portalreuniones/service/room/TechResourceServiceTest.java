package es.uned.portalreuniones.service.room;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.TechResource;

@SpringBootTest
public class TechResourceServiceTest {

	@Autowired
	private TechResourceService techResourceService;
	


	@DisplayName("Recuperar todos los medios técnicos")
	@Test
	public void findAllTest() {
		List<TechResource> techResources = techResourceService.findAll();
		assertEquals(true, techResources.size()>0);
	}
	
	@DisplayName("Recuperar un medio técnico por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		TechResource event = techResourceService.findById(1l);
		assertEquals(1l, event.getId());
	}
	
}
