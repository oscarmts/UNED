package es.uned.portalreuniones.service.room;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.RoomNameFoundException;
import es.uned.portalreuniones.model.dto.RoomDTO;
import es.uned.portalreuniones.model.room.Room;
import es.uned.portalreuniones.model.room.RoomSchedule;
import es.uned.portalreuniones.model.room.RoomTechResource;
import es.uned.portalreuniones.model.room.TechResource;
import es.uned.portalreuniones.repository.room.RoomRepository;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomScheduleService roomScheduleService;

	@Autowired
	private RoomTechResourceService roomTechResourceService;

	@Autowired
	private TechResourceService techResourceService;

	@Override
	public List<Room> findAll() {
		return roomRepository.findAll();
	}

	@Override
	public Room findById(Long idRoom) throws ElementNotFoundException {
		Optional<Room> roomOpt = roomRepository.findById(idRoom);
		if (roomOpt.isPresent()) {
			return roomOpt.get();
		} else {
			throw new ElementNotFoundException("Room not found with id " + idRoom);
		}
	}

	@Override
	public Room findByName(String name) {
		return roomRepository.findByName(name);
	}

	@Override
	public Room save(Room room) {
		return roomRepository.save(room);
	}

	@Override
	public Room createRoom(RoomDTO roomDTO) throws RoomNameFoundException {
		if (findByName(roomDTO.getName().trim()) == null) {
			Room room = new Room();
			room.setName(roomDTO.getName());
			room.setCapacity(Integer.valueOf(roomDTO.getCapacity()));
			Room roomPersist = save(room);
			int[] techResources = roomDTO.getTechResources();
			int[] hours = roomDTO.getHours();

			try {
				if (techResources != null) {
					for (int i = 0; i < techResources.length; i++) {
						TechResource techResource = techResourceService.findById(Long.valueOf(techResources[i]));
						RoomTechResource roomTechResource = new RoomTechResource();
						roomTechResource.setRoom(roomPersist);
						roomTechResource.setTechResource(techResource);
						roomTechResourceService.save(roomTechResource);
					}
				}
				if (hours != null) {
					for (int i = 0; i < hours.length; i++) {
						RoomSchedule roomSchedule = new RoomSchedule();
						roomSchedule.setHour(hours[i]);
						roomSchedule.setRoom(roomPersist);
						roomScheduleService.save(roomSchedule);
					}
				}

			} catch (ElementNotFoundException e) {

			}
			return roomPersist;
		} else {
			throw new RoomNameFoundException("Ya existe una sala con el nombre " + roomDTO.getName());
		}

	}

	@Override
	public void delete(Long idRoom) throws ElementNotFoundException {
		Room roomPersistent = findById(idRoom);
		roomRepository.delete(roomPersistent);

	}

	@Override
	public List<Integer> getHours() {
		return Arrays.asList(8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
	}

	@Override
	public boolean isAvailable(Long idRoom, int[] hours) {
		boolean isAvailable = false;
		List<RoomSchedule> roomSchedule = roomScheduleService.findByIdRoom(idRoom);
		List<Integer> hoursList = new ArrayList<Integer>();
		for (RoomSchedule roomScheduleHour : roomSchedule) {
			hoursList.add(roomScheduleHour.getHour());
		}

		for (Integer hour : hours) {
			isAvailable = hoursList.contains(hour);
		}
		return isAvailable;
	}

	@Override
	public boolean isReserved(Long idRoom, Long idEvent, Date date, Integer hour) {
		List<Room> roomsReserved = roomRepository.findByDateHour(idRoom, idEvent, date, hour);
		return roomsReserved.size() > 0;
	}

}
