package es.uned.portalreuniones.service.user;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.user.UserNotRegistered;

/**
 * Gestión de usuarios no registrados
 * 
 * @author omontes
 *
 */
public interface UserNotRegisteredService {

	/**
	 * Recuperación de un usuario no registrado por su id
	 * 
	 * @param idUser
	 * @return
	 * @throws ElementNotFoundException
	 */
	UserNotRegistered findById(Long idUser) throws ElementNotFoundException;

	/**
	 * Listado de usuarios no registrados en un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<UserNotRegistered> findByIdEvent(Long idEvent);

	/**
	 * Guardado de usuarios
	 * 
	 * @param userNotRegistered
	 * @return
	 */
	UserNotRegistered save(UserNotRegistered userNotRegistered);

	/**
	 * Añadir un usuario no registrado a un evento
	 * 
	 * @param name
	 * @param idEvent
	 * @return
	 */
	UserNotRegistered create(String name, Long idEvent);

}
