package es.uned.portalreuniones.service.event;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventInvited;

/**
 * Servicio para invitar a usuarios a eventos
 * 
 * @author omontes
 *
 */
public interface EventInvitedService {

	/**
	 * Recuperación de los eventos a los que está invitado un usuario
	 * 
	 * @param idUser
	 * @return
	 */
	List<EventInvited> findEventInvitedByIdUser(Long idUser);

	/**
	 * Recuperación de invitados a un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventInvited> findEventInvitedByIdEvent(Long idEvent);

	/**
	 * Recuperación de invitados sin votar por evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventInvited> findEventInvitedWithoutVoteByIdEvent(Long idEvent);

	/**
	 * Encontrar eventos finalizados donde el usuario fue invitado
	 * 
	 * @param idUser
	 * @return
	 */
	List<EventInvited> findEventInvitedFinishedByIdUser(Long idUser);

	/**
	 * Obtener la invitación a un evento de un usuario
	 * 
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	EventInvited findEventInvitedByIdEventAndIdUser(Long idEvent, Long idUser);

	/**
	 * Traspasar un usuario como invitado desde la lista de espera
	 * 
	 * @param idEvent
	 * @param idUser
	 * @return
	 * @throws ElementNotFoundException
	 */
	EventInvited transferFromEventWaitingList(Long idEvent, Long idUser) throws ElementNotFoundException;

	/**
	 * Asignar a un usuario que ya ha votado en un evento
	 * 
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	EventInvited setUserVoted(Long idEvent, Long idUser);

	/**
	 * Asignar que un usuario ha sido inscrito a un evento
	 * 
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	EventInvited setUserEnrolled(Long idEvent, Long idUser, boolean enrolled);

	/**
	 * Guardado de un evento
	 * 
	 * @param event
	 * @return
	 */
	EventInvited save(EventInvited event);

	/**
	 * Borrada invitación de evento
	 * 
	 * @param idEvent
	 * @param idUser
	 */
	void deleteByIdEvent(Long idEvent, Long idUser);

}
