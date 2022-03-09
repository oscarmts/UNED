package es.uned.portalreuniones.service.event;

import java.sql.Date;
import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.EventFinishedException;
import es.uned.portalreuniones.exception.ForbiddenException;
import es.uned.portalreuniones.model.dto.EventDTO;
import es.uned.portalreuniones.model.dto.EventDateDTO;
import es.uned.portalreuniones.model.event.Event;

/**
 * Gestión de los eventos
 * 
 * @author omontes
 *
 */
public interface EventService {

	/**
	 * Recuperación de todos los eventos del sistema
	 * 
	 * @return
	 */
	List<Event> findAll();

	/**
	 * Obtener un evento por su identificador
	 * 
	 * @param idEvent
	 * @return
	 * @throws ElementNotFoundException
	 */
	Event findById(Long idEvent) throws ElementNotFoundException;

	/**
	 * Obtener un evento por su token
	 * 
	 * @param token
	 * @return
	 * @throws ElementNotFoundException
	 */
	Event findByToken(String token) throws ElementNotFoundException;

	/**
	 * Búsqueda de eventos de una sala
	 * 
	 * @param idRoom
	 * @return
	 */
	List<Event> findByIdRoom(Long idRoom);

	/**
	 * Búsqueda de eventos por organizador
	 * 
	 * @param idOwner
	 * @return
	 */
	List<Event> findByIdOwner(Long idOwner);

	/**
	 * Buscar los eventos pendientes de configurar (todavía en fase de votación)
	 * 
	 * @param idOwner
	 * @return
	 */
	List<Event> findPendingEventByIdOwner(Long idOwner);

	/**
	 * Buscar eventos que han finalizado por su organizador
	 * 
	 * @param idOwner
	 * @return
	 */
	List<Event> findFinishedByIdOwner(Long idOwner);

	/**
	 * Obtener la hora definitiva del evento
	 * 
	 * @param idEvent
	 * @return
	 */
	EventDateDTO configureDateEvent(Long idEvent);

	/**
	 * Guardado de un evento
	 * 
	 * @param event
	 * @return
	 */
	Event save(Event event);

	/**
	 * Creación de un evento
	 * 
	 * @param eventDTO
	 * @return
	 */
	Event createEvent(EventDTO eventDTO);

	/**
	 * Actualización de un evento
	 * 
	 * @param event
	 * @return
	 */
	Event update(Event event) throws ElementNotFoundException;

	/**
	 * Asignar la hora al evento
	 * 
	 * @param idEvent
	 * @param hour
	 * @return
	 */
	Event configureEventWithDate(Long idEvent, Date date, Integer hour);

	/**
	 * Finalización de un evento por parte del organizador
	 * 
	 * @param idEvent
	 * @return
	 */
	Event finishEvent(Long idEvent);

	/**
	 * Borrado de un evento
	 * 
	 * @param idEvent
	 */
	void delete(Long idEvent);

	/**
	 * Se comprueba que son usuarios invitados al evento y que es la hora correcta
	 * para entrar
	 * 
	 * @param idEvent
	 * @param idUser
	 * @param withToken
	 * @return
	 * @throws ForbiddenException
	 * @throws EventFinishedException
	 */
	boolean checkPermission(Long idEvent, Long idUser, boolean withToken)
			throws ForbiddenException, EventFinishedException;

	/**
	 * Se comprueba que son usuarios que fueron invitados al evento para poder ver
	 * el detalle
	 * 
	 * @param idEvent
	 * @param idUser
	 * @param withToken
	 * @return
	 * @throws ForbiddenException
	 */
	boolean checkDetailPermission(Long idEvent, Long idUser, boolean withToken) throws ForbiddenException;
}
