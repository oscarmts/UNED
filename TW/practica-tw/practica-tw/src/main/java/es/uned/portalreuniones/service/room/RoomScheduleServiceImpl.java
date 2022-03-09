package es.uned.portalreuniones.service.room;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.RoomSchedule;
import es.uned.portalreuniones.repository.room.RoomScheduleRepository;

@Service
public class RoomScheduleServiceImpl implements RoomScheduleService {

	@Autowired
	private RoomScheduleRepository repository;

	@Override
	public List<RoomSchedule> findAll() {
		return repository.findAll();
	}

	@Override
	public RoomSchedule findById(Long idRoomSchedule) throws ElementNotFoundException {
		Optional<RoomSchedule> roomScheduleOpt = repository.findById(idRoomSchedule);
		if (roomScheduleOpt.isPresent()) {
			return roomScheduleOpt.get();
		} else {
			throw new ElementNotFoundException("RoomSchedule not found with id " + idRoomSchedule);
		}
	}

	@Override
	public RoomSchedule save(RoomSchedule roomSchedule) {
		return repository.save(roomSchedule);
	}

	@Override
	public void delete(Long idRoomSchedule) throws ElementNotFoundException {
		RoomSchedule roomSchedulePersistent = findById(idRoomSchedule);
		repository.delete(roomSchedulePersistent);

	}

	@Override
	public List<RoomSchedule> findByIdRoom(Long idRoom) {
		return repository.findByIdRoom(idRoom);
	}
}
