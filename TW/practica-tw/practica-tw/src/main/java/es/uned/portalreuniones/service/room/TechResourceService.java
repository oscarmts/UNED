package es.uned.portalreuniones.service.room;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.TechResource;

/**
 * Medios técnicos para las salas del sistema
 * 
 * @author omontes
 *
 */
public interface TechResourceService {

	/**
	 * Se recuperan todos los medios técnicos que hay disponibles
	 * 
	 * @return
	 */
	List<TechResource> findAll();

	/**
	 * Recuperar un medio técnico por su identificador
	 * 
	 * @param idTechResource
	 * @return
	 * @throws ElementNotFoundException
	 */
	TechResource findById(Long idTechResource) throws ElementNotFoundException;
}
