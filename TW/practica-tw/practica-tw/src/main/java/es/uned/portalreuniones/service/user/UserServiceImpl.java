package es.uned.portalreuniones.service.user;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.NicknameFoundException;
import es.uned.portalreuniones.model.dto.UserDTO;
import es.uned.portalreuniones.model.dto.UserUpdateDTO;
import es.uned.portalreuniones.model.user.User;
import es.uned.portalreuniones.repository.user.UserRepository;
import es.uned.portalreuniones.service.event.EventEnrollService;
import es.uned.portalreuniones.service.event.EventInvitedService;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EventEnrollService eventEnrollService;

	@Autowired
	private EventInvitedService eventInvitedService;

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findById(Long idUser) throws ElementNotFoundException {
		Optional<User> userOpt = userRepository.findById(idUser);
		if (userOpt.isPresent()) {
			return userOpt.get();
		} else {
			throw new ElementNotFoundException("User not found with id " + idUser);
		}
	}

	@Override
	public UserUpdateDTO findUserDTOById(Long idUser) throws ElementNotFoundException {
		User user = findById(idUser);
		UserUpdateDTO userDTO = new UserUpdateDTO();
		userDTO.setIdUser(idUser);
		userDTO.setEmail(user.getEmail());
		userDTO.setName(user.getName());
		userDTO.setNickName(user.getNickName());
		userDTO.setSurname(user.getSurname());
		return userDTO;
	}

	@Override
	public User findByNickName(String nickName) {
		return userRepository.findByNickName(nickName);
	}

	@Override
	public User findByNickNameAndPassword(String nickName, String password) throws ElementNotFoundException {
		List<User> users = userRepository.findByNickNameAndPassword(nickName, password);
		if (users.isEmpty())
			throw new ElementNotFoundException("Usuario " + nickName + " no encontrado o password incorrecto");
		else
			return users.get(0);
	}

	@Override
	public User create(UserDTO userDTO) throws NicknameFoundException {
		if (findByNickName(userDTO.getNickName().trim()) == null) {
			User user = new User();
			user.setName(userDTO.getName());
			user.setSurname(userDTO.getSurname());
			user.setNickName(userDTO.getNickName());
			user.setPassword(userDTO.getPassword());
			user.setEmail(userDTO.getEmail());
			user.setRol(userDTO.getRol());
			return save(user);
		} else {
			throw new NicknameFoundException("Ya existe un usuario con el apodo " + userDTO.getNickName());
		}
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public User update(User user) {
		logger.info("Se actualiza el usuario:: {}", user);
		User userUpdated = null;
		try {
			User userPersist = findById(user.getId());
			userPersist.setName(user.getName());
			userPersist.setSurname(user.getSurname());
			userPersist.setEmail(user.getEmail());
			userUpdated = save(userPersist);
		} catch (ElementNotFoundException e) {
			// TODO Ya veremos cómo gestionamos esto
		}
		return userUpdated;
	}

	@Override
	public User update(UserUpdateDTO user) {
		User userUpdated = null;
		try {
			User userPersist = findById(user.getIdUser());
			userPersist.setName(user.getName());
			userPersist.setSurname(user.getSurname());
			userPersist.setEmail(user.getEmail());
			userUpdated = save(userPersist);
		} catch (ElementNotFoundException e) {
			// TODO Ya veremos cómo gestionamos esto
		}
		return userUpdated;
	}

	@Override
	public void removeEvent(Long idEvent, Long idUser) {
		eventEnrollService.deleteByIdEvent(idEvent, idUser);
		eventInvitedService.deleteByIdEvent(idEvent, idUser);

	}

}
