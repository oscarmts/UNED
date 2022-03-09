package es.uned.portalreuniones.model.dto;

import javax.validation.constraints.NotBlank;

/**
 * DTO para la pantalla de login
 * 
 * @author omontes
 *
 */
public class LoginDTO {

	@NotBlank
	private String nickname;

	@NotBlank
	private String password;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
