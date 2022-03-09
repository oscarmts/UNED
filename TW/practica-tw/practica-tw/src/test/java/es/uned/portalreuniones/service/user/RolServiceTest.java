package es.uned.portalreuniones.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.user.Rol;

@SpringBootTest
public class RolServiceTest {

	@Autowired
	private RolService rolService;

	@DisplayName("Recuperar todos los roles")
	@Test
	public void findAllTest() {
		List<Rol> roles = rolService.findAll();
		assertTrue(roles.size() > 0);
	}

	@DisplayName("Encontrar un rol por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		Rol rol = rolService.findById(1l);
		assertEquals(1l, rol.getId());
	}
}
