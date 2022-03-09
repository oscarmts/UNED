package es.uned.portalreuniones.service.event;

import java.sql.Date;
import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventEnroll;

/**
 * Gestión de las inscripciones a eventos
 * 
 * @author omontes
 *
 */
public interface EventEnrollService {

	/**
	 * Recuperar todos los eventos a los que hay alguien inscrito
	 * 
	 * @return
	 */
	List<EventEnroll> findAll();

	/**
	 * Se recupera una inscripción a un evento
	 * 
	 * @param idEventEnroll
	 * @return
	 * @throws ElementNotFoundException
	 */
	EventEnroll findById(Long idEventEnroll) throws ElementNotFoundException;

	/**
	 * Encontrar a qué eventos está inscrito un usuario
	 * 
	 * @param idUser
	 * @return
	 */
	List<EventEnroll> findByIdUser(Long idUser);

	/**
	 * Recuperación de invitados inscritos a un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventEnroll> findEventEnrollByIdEvent(Long idEvent);

	/**
	 * Inscribir los usuarios a un evento
	 * 
	 * @param idEvent
	 * @param date
	 * @param hour
	 * @return
	 */
	List<EventEnroll> enrrolUsersToEvent(Long idEvent, Date date, Integer hour);

	/**
	 * Guardado inscripción a un evento
	 * 
	 * @param eventEnroll
	 * @return
	 */
	EventEnroll save(EventEnroll eventEnroll);

	/**
	 * Borrada una suscripción a un evento
	 * 
	 * @param idEventEnroll
	 */
	void delete(Long idEventEnroll);

	/**
	 * Borrada una suscripción a un evento por el id del evento
	 * 
	 * @param idEvent
	 * @param idUser
	 */
	void deleteByIdEvent(Long idEvent, Long idUser);
}
