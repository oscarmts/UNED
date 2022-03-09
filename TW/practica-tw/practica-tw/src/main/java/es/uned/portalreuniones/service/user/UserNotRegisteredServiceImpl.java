package es.uned.portalreuniones.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.model.user.UserNotRegistered;
import es.uned.portalreuniones.repository.user.UserNotRegisteredRepository;
import es.uned.portalreuniones.service.event.EventService;

@Service
public class UserNotRegisteredServiceImpl implements UserNotRegisteredService {

	@Autowired
	private UserNotRegisteredRepository userNotRegisteredRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private RolService rolService;

	@Autowired
	EventService eventService;

	@Override
	public UserNotRegistered findById(Long idUser) throws ElementNotFoundException {
		Optional<UserNotRegistered> userNotRegisteredOpt = userNotRegisteredRepository.findById(idUser);
		if (userNotRegisteredOpt.isPresent()) {
			return userNotRegisteredOpt.get();
		} else {
			throw new ElementNotFoundException("UserNotRegistered not found with id: " + idUser);
		}
	}

	@Override
	public List<UserNotRegistered> findByIdEvent(Long idEvent) {
		return userNotRegisteredRepository.findByIdEvent(idEvent);
	}

	@Override
	public UserNotRegistered save(UserNotRegistered userNotRegistered) {
		return userNotRegisteredRepository.save(userNotRegistered);
	}

	@Override
	public UserNotRegistered create(String name, Long idEvent) {
		UserNotRegistered userNotRegistered = new UserNotRegistered();
		try {
			User user = new User();
			user.setName(name);
			user.setNickName(name);
			user.setRol(rolService.findById(0l));
			userService.save(user);
			userNotRegistered.setUser(user);
			userNotRegistered.setEvent(eventService.findById(idEvent));

		} catch (ElementNotFoundException e) {
			// TODO Ya veremos qué hacemos con esto
		}
		return save(userNotRegistered);
	}

}
