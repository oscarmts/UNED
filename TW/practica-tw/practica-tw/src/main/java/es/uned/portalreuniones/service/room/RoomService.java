package es.uned.portalreuniones.service.room;

import java.sql.Date;
import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.RoomNameFoundException;
import es.uned.portalreuniones.model.dto.RoomDTO;
import es.uned.portalreuniones.model.room.Room;

/**
 * Gestión de las salas de los eventos
 * 
 * @author omontes
 *
 */
public interface RoomService {

	/**
	 * Recuperar todas las salas que están en el sistem
	 * 
	 * @return
	 */
	List<Room> findAll();

	/**
	 * Encontrar una sala por su identificador
	 * 
	 * @param idRoom
	 * @return
	 * @throws ElementNotFoundException
	 */
	Room findById(Long idRoom) throws ElementNotFoundException;

	/**
	 * Búsqueda de sala por su nombre
	 * 
	 * @param name
	 * @return
	 */
	Room findByName(String name);

	/**
	 * Guardar una sala
	 * 
	 * @param room
	 * @return
	 */
	Room save(Room room);

	/**
	 * Crear y guardar una sala desde front
	 * 
	 * @param roomDTO
	 * @return
	 * @throws RoomNameFoundException
	 */
	Room createRoom(RoomDTO roomDTO) throws RoomNameFoundException;

	/**
	 * Borrar una sala
	 * 
	 * @param idRoom
	 * @throws ElementNotFoundException
	 */
	void delete(Long idRoom) throws ElementNotFoundException;

	/**
	 * Recuperar las horas de disponibilidad de las salas
	 * 
	 * @return
	 */
	List<Integer> getHours();

	/**
	 * Saber si esa sala está disponible a esa hora por su configuración
	 * 
	 * @param idRoom
	 * @param hours
	 * @return
	 */
	boolean isAvailable(Long idRoom, int[] hours);

	/**
	 * Comprueba si esa sala está reservada para ese día y a esa hora
	 * 
	 * @param idRoom
	 * @param idEvent
	 * @param date
	 * @param hour
	 * @return
	 */
	boolean isReserved(Long idRoom, Long idEvent, Date date, Integer hour);
}
