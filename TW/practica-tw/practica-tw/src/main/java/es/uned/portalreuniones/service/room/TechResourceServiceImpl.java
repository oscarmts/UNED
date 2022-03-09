package es.uned.portalreuniones.service.room;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.room.TechResource;
import es.uned.portalreuniones.repository.room.TechResourceRepository;

@Service
public class TechResourceServiceImpl implements TechResourceService {

	@Autowired
	private TechResourceRepository techResourceRepository;

	@Override
	public List<TechResource> findAll() {
		return techResourceRepository.findAll();
	}

	@Override
	public TechResource findById(Long idTechResource) throws ElementNotFoundException {
		Optional<TechResource> techResourceOpt = techResourceRepository.findById(idTechResource);
		if (techResourceOpt.isPresent()) {
			return techResourceOpt.get();
		} else {
			throw new ElementNotFoundException("TechResource not found with id " + idTechResource);
		}
	}

}
