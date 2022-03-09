package es.uned.portalreuniones.service.user;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.user.Rol;

/**
 * Gestión de los roles de usuario
 * 
 * @author omontes
 *
 */
public interface RolService {

	/**
	 * Se recuperan todos los roles del sistema
	 * 
	 * @return
	 */
	List<Rol> findAll();

	/**
	 * Recuperar un rol por su identificador
	 * 
	 * @param idRol
	 * @return
	 * @throws ElementNotFoundException
	 */
	Rol findById(Long idRol) throws ElementNotFoundException;
}
