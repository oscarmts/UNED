package es.uned.portalreuniones.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.UserNotFoundException;
import es.uned.portalreuniones.model.user.User;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;

	@Override
	public User loginUser(String nickName, String password) throws UserNotFoundException {
		User user = null;
		try {
			user = userService.findByNickNameAndPassword(nickName, password);
		} catch (ElementNotFoundException e) {
			throw new UserNotFoundException(e.getMessage());
		}
		return user;
	}

}
