package es.uned.portalreuniones.service.user;

import java.util.List;

import es.uned.portalreuniones.exception.ElementNotFoundException;
import es.uned.portalreuniones.exception.NicknameFoundException;
import es.uned.portalreuniones.model.dto.UserDTO;
import es.uned.portalreuniones.model.dto.UserUpdateDTO;
import es.uned.portalreuniones.model.user.User;

/**
 * Gestión de usuarios
 * 
 * @author omontes
 *
 */
public interface UserService {

	/**
	 * Búsqueda de todos los usuarios
	 * 
	 * @return
	 */
	List<User> findAll();

	/**
	 * Encontrar un usuario por su identificador
	 * 
	 * @param idUser
	 * @return
	 * @throws ElementNotFoundException
	 */
	User findById(Long idUser) throws ElementNotFoundException;

	/**
	 * Recuperar UserDTO para el front
	 * 
	 * @param idUser
	 * @return
	 * @throws ElementNotFoundException
	 */
	UserUpdateDTO findUserDTOById(Long idUser) throws ElementNotFoundException;

	/**
	 * Encontrar usuario por su nick
	 * 
	 * @param nickName
	 * @return
	 */
	User findByNickName(String nickName);

	/**
	 * Encontrar un usuario por su usuario y su contraseña
	 * 
	 * @param nickName
	 * @param passWord
	 * @return
	 * @throws ElementNotFoundException
	 */
	User findByNickNameAndPassword(String nickName, String passWord) throws ElementNotFoundException;

	/**
	 * 
	 * @param user
	 * @return
	 * @throws NicknameFoundException
	 */
	User create(UserDTO user) throws NicknameFoundException;

	/**
	 * Guardado de usuarios
	 * 
	 * @param user
	 * @return
	 */
	User save(User user);

	/**
	 * Actualización de usuario
	 * 
	 * @param user
	 * @return
	 */
	User update(User user);

	/**
	 * Actualización de usuario desde el front
	 * 
	 * @param user
	 * @return
	 */
	User update(UserUpdateDTO user);

	/**
	 * Se elimina un usuario de un evento, se borra de la lista de invitados y de
	 * suscritos
	 * 
	 * @param idEvent
	 * @param idUser
	 */
	void removeEvent(Long idEvent, Long idUser);
}
