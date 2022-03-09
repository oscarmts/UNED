package es.uned.portalreuniones.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.user.Rol;
import es.uned.portalreuniones.repository.user.RolRepository;

@Service
public class RolServiceImpl implements RolService {

	@Autowired
	private RolRepository rolRepository;

	@Override
	public List<Rol> findAll() {
		return rolRepository.findAll();
	}

	public Rol findById(Long idRol) throws ElementNotFoundException {
		Optional<Rol> rolOpt = rolRepository.findById(idRol);
		if (rolOpt.isPresent()) {
			return rolOpt.get();
		} else {
			throw new ElementNotFoundException("Rol not found with id: " + idRol);
		}
	}
}
