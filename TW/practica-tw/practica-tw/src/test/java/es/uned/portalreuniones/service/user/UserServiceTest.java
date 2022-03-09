package es.uned.portalreuniones.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.user.Rol;
import es.uned.portalreuniones.model.user.User;

@SpringBootTest
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Autowired
	private RolService rolService;

	@DisplayName("Recuperar a todos los usuarios")
	@Test
	public void findAllTest() {
		List<User> users = userService.findAll();
		assertEquals(true, users.size() > 0);
	}

	@DisplayName("Encontrar un usuario por id")
	@Test
	public void findByIdTest() throws ElementNotFoundException {
		User user = userService.findById(1l);
		assertEquals(1l, user.getId());
	}

	@DisplayName("Encontrar un usuario por usuario y contraseña")
	@Test
	public void findByNickNameAndPasswordTest() throws ElementNotFoundException {
		User user = userService.findByNickNameAndPassword("omontes", "1234");
		assertNotNull(user);
	}

	@DisplayName("No encontrar un usuario por usuario y contraseña")
	@Test
	public void notFindByNickNameAndPasswordTest() throws ElementNotFoundException {
		assertThrows(ElementNotFoundException.class, () -> {
			userService.findByNickNameAndPassword("usuarioInventado", "1234");
		});

	}

	@DisplayName("Guardar usuario")
	@Test
	public void saveUser() throws ElementNotFoundException {
		User user = new User();
		user.setName("Juan");
		user.setSurname("Gutierrez");
		user.setNickName("jgutierrez");
		user.setPassword("1234");
		Rol rol = rolService.findById(1l);
		user.setRol(rol);
		User userSaved = userService.save(user);
		assertEquals("Juan", userSaved.getName());
	}

}
