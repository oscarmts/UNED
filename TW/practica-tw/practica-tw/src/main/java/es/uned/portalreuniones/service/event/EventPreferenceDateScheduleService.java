package es.uned.portalreuniones.service.event;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.event.EventPreferenceDateSchedule;

/**
 * Clase para la gestión de las fechas que escoge el creador del evento
 * 
 * @author omontes
 *
 */
public interface EventPreferenceDateScheduleService {

	/**
	 * Encontrar una fecha por su identificador
	 * 
	 * @param id
	 * @return
	 * @throws ElementNotFoundException
	 */
	EventPreferenceDateSchedule findById(Long id) throws ElementNotFoundException;

	/**
	 * Búsqueda de fechas elegidas por el creador del evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventPreferenceDateSchedule> findByIdEvent(Long idEvent);

	/**
	 * Guardado de una fecha para la preparación del evento
	 * 
	 * @param eventPreferenceDateSchedule
	 * @return
	 */
	EventPreferenceDateSchedule save(EventPreferenceDateSchedule eventPreferenceDateSchedule);
}
