package es.uned.portalreuniones.service.room;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.RoomSchedule;

/**
 * Servicios para la gestión de la disponibilidad horaria de la sala
 * 
 * @author omontes
 *
 */
public interface RoomScheduleService {

	/**
	 * Se recuperan todas las salas con su disponibilidad horaria del sistema
	 * 
	 * @return
	 */
	List<RoomSchedule> findAll();

	/**
	 * Recuperar una hora por su identificación
	 * 
	 * @param idRoomSchedule
	 * @return
	 * @throws ElementNotFoundException
	 */
	RoomSchedule findById(Long idRoomSchedule) throws ElementNotFoundException;

	/**
	 * Se recuperan las horas disponibles que tiene una sala por su identificador
	 * 
	 * @param idRoom
	 * @return
	 */
	List<RoomSchedule> findByIdRoom(Long idRoom);

	/**
	 * Se guarda una hora relacionada con la sala
	 * 
	 * @param roomSchedule
	 * @return
	 */
	RoomSchedule save(RoomSchedule roomSchedule);

	/**
	 * Se borra una hora de la sala por su identificador
	 * 
	 * @param idRoomSchedule
	 * @throws ElementNotFoundException
	 */
	void delete(Long idRoomSchedule) throws ElementNotFoundException;
}
