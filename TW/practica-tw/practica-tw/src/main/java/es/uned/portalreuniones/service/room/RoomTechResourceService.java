package es.uned.portalreuniones.service.room;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.RoomTechResource;

/**
 * Gestión de los medios técnicos de una sala
 * 
 * @author omontes
 *
 */
public interface RoomTechResourceService {

	/**
	 * Se recuperan todos los medios técnicos de todas las salas
	 * 
	 * @return
	 */
	List<RoomTechResource> findAll();

	/**
	 * Se recupera un medio técnico de una sala por su identificador
	 * 
	 * @param idTechResource
	 * @return
	 * @throws ElementNotFoundException
	 */
	RoomTechResource findById(Long idTechResource) throws ElementNotFoundException;

	/**
	 * Recuperación de los medios técnicos de una sala
	 * 
	 * @param idRoom
	 * @return
	 */
	List<RoomTechResource> findByIdRoom(Long idRoom);

	/**
	 * Se guarda un medio técnico de una sala
	 * 
	 * @param roomTechResource
	 * @return
	 */
	RoomTechResource save(RoomTechResource roomTechResource);

	/**
	 * Se borra un medio técnico de una sala por su identificador
	 * 
	 * @param idTechResource
	 * @throws ElementNotFoundException
	 */
	void delete(Long idTechResource) throws ElementNotFoundException;
}
