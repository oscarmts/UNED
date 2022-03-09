package es.uned.portalreuniones.service.event;

import java.sql.Date;
import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.dto.EventPreferenceScheduleDTO;
import es.uned.portalreuniones.model.event.EventPreferenceSchedule;
import es.uned.portalreuniones.model.user.User;

/**
 * Gestión para el proceso de preferencias de horario para un evento
 * 
 * @author omontes
 *
 */
public interface EventPreferenceScheduleService {

	/**
	 * Recuperar todas las preferencias existentes de todos los eventos en el
	 * sistema
	 * 
	 * @return
	 */
	List<EventPreferenceSchedule> findAll();

	/**
	 * Recuperación de las preferencias de horario de un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventPreferenceSchedule> findByIdEvent(Long idEvent);

	/**
	 * Obtener las horas que ha elegido el Administrador o el Jefe de proyecto para
	 * que los usuarios decidan
	 * 
	 * @param idEvent
	 * @return
	 */
	List<Integer> findHoursFromOwner(Long idEvent);

	/**
	 * Recuperación de las preferencias por usuario del proceso de selección de un
	 * evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<EventPreferenceScheduleDTO> findUserWithHoursByIdEvent(Long idEvent);

	/**
	 * Recuperación de una configuración de preferencias de horario por su
	 * identificador
	 * 
	 * @param idEventPreferenceSchedule
	 * @return
	 * @throws ElementNotFoundException
	 */
	EventPreferenceSchedule findById(Long idEventPreferenceSchedule) throws ElementNotFoundException;

	/**
	 * Guardado de una configuración de preferencia de horario
	 * 
	 * Asignamos usuarios al evento (EventEnroll) y creamos las preferencias del
	 * administrador o jefe de proyecto (EventPreferenceSchedule)
	 * 
	 * @param eventPreferenceSchedule
	 * @return
	 */
	EventPreferenceSchedule save(EventPreferenceSchedule eventPreferenceSchedule);

	/**
	 * Añadir las preferencias de horario de un usuario en el evento
	 * 
	 * @param eventPreferenceScheduleDTO
	 * @return
	 */
	List<EventPreferenceSchedule> create(EventPreferenceScheduleDTO eventPreferenceScheduleDTO);

	/**
	 * Recuperar horas escogidas por un usuario para un evento
	 * 
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	List<Integer> findHoursFromUser(Long idEvent, Long idUser);

	/**
	 * Recuperar usuarios que han escogido sus horas y fecha de un evento
	 * 
	 * @param idEvent
	 * @return
	 */
	List<User> findUsersWritePreferences(Long idEvent);

	/**
	 * Recuperar las fecha escogida por un usuario
	 * 
	 * @param idEvent
	 * @param idUser
	 * @return
	 */
	Date findDateUserPreferences(Long idEvent, Long idUser);

	/**
	 * Recuperar horas votadas en una fecha de un evento
	 * 
	 * @param idEvent
	 * @param date
	 * @return
	 */
	List<Integer> findHoursFromDateAndEvent(Long idEvent, Date date);

}
