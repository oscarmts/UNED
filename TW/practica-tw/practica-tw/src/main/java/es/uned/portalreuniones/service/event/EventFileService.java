package es.uned.portalreuniones.service.event;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.EventFinishedException;
import es.uned.portalreuniones.model.dto.EventFileDTO;
import es.uned.portalreuniones.model.event.EventFile;

/**
 * Clase que gestiona los ficheros adjuntos a un evento
 * 
 * @author omontes
 *
 */
public interface EventFileService {

	/**
	 * Recuperar un ficheropor su identificador
	 * 
	 * @param idFile
	 * @return
	 */
	EventFile findById(Long idFile) throws ElementNotFoundException;

	/**
	 * Obtener todos los ficheros adjuntos a un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventFile> findByIdEvent(Long idEvent);

	/**
	 * Guardado de un fichero en un evento
	 * 
	 * @param eventFile
	 * @return
	 */
	EventFile save(EventFile eventFile);

	/**
	 * Se añade un fichero a un evento
	 * 
	 * @param eventFileDTO
	 * @return
	 * @throws ElementNotFoundException
	 * @throws EventFinishedException
	 */
	EventFile addFileToEvent(EventFileDTO eventFileDTO) throws ElementNotFoundException, EventFinishedException;

	/**
	 * Borrado de un fichero
	 * 
	 * @param idFile
	 * @throws ElementNotFoundException
	 */
	void delete(Long idFile) throws ElementNotFoundException;
}
