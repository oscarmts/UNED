package es.uned.portalreuniones.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import es.uned.portalreuniones.model.user.Rol;

/**
 * DTO para la pantalla de nuevo usuario
 * 
 * @author omontes
 *
 */
public class UserDTO {

	@NotEmpty
	@NotBlank
	private String name;

	@NotEmpty
	@NotBlank
	private String surname;

	@NotEmpty
	@NotBlank
	private String nickName;

	@NotEmpty
	@NotBlank
	private String password;

	@NotEmpty
	@Email
	private String email;

	private Rol rol;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

}
