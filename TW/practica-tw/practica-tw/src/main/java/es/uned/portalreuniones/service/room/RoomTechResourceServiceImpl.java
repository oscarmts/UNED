package es.uned.portalreuniones.service.room;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.RoomTechResource;
import es.uned.portalreuniones.repository.room.RoomTechResourceRepository;

@Service
public class RoomTechResourceServiceImpl implements RoomTechResourceService {

	@Autowired
	private RoomTechResourceRepository roomTechResourceRepository;

	@Override
	public List<RoomTechResource> findAll() {
		return roomTechResourceRepository.findAll();
	}

	@Override
	public RoomTechResource findById(Long idTechResource) throws ElementNotFoundException {
		Optional<RoomTechResource> roomTechResourceOpt = roomTechResourceRepository.findById(idTechResource);
		if (roomTechResourceOpt.isPresent()) {
			return roomTechResourceOpt.get();
		} else {
			throw new ElementNotFoundException("RoomTechResource not found with id " + idTechResource);
		}
	}

	@Override
	public List<RoomTechResource> findByIdRoom(Long idRoom) {
		return roomTechResourceRepository.findByIdRoom(idRoom);
	}

	@Override
	public RoomTechResource save(RoomTechResource roomTechResource) {
		return roomTechResourceRepository.save(roomTechResource);
	}

	@Override
	public void delete(Long idTechResource) throws ElementNotFoundException {
		RoomTechResource roomTechResourcePersistent = findById(idTechResource);
		roomTechResourceRepository.delete(roomTechResourcePersistent);

	}

}
