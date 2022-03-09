package es.uned.portalreuniones.service.event;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventWaitingList;

/**
 * Gestión de la lista de espera de los eventos
 * 
 * @author omontes
 *
 */
public interface EventWaitingListService {

	/**
	 * Recuperar todas las listas de espera del sistema
	 * 
	 * @return
	 */
	List<EventWaitingList> findAll();

	/**
	 * Recuperar lista de espera por el identificador
	 * 
	 * @param idEventWaitingList
	 * @return
	 * @throws ElementNotFoundException
	 */
	EventWaitingList findById(Long idEventWaitingList) throws ElementNotFoundException;

	/**
	 * Recuperar la lista de espera de un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventWaitingList> findByIdEvent(Long idEvent);

	/**
	 * Traspasar un usuario de la lista de invitados a la lista de espera
	 * 
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	EventWaitingList transferFromEventInvited(Long idEvent, Long idUser) throws ElementNotFoundException;

	/**
	 * Borrado un usuario de la lista de espera
	 * 
	 * @param idEvent
	 * @param idUser
	 */
	void deleteByIdEvent(Long idEvent, Long idUser);

	/**
	 * Guardado de la lista de espera
	 * 
	 * @param eventWaitingList
	 * @return
	 */
	EventWaitingList save(EventWaitingList eventWaitingList);
}
