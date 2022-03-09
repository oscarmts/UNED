package es.uned.portalreuniones.service.user;

import es.uned.portalreuniones.exception.UserNotFoundException;
import es.uned.portalreuniones.model.user.User;

/**
 * Gestión del login de usuarios
 * 
 * @author omontes
 *
 */
public interface LoginService {

	/**
	 * Se loguea un usuario en el sistema con su usuario y contraseña
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws UserNotFoundException
	 */
	User loginUser(String username, String password) throws UserNotFoundException;
}
