package es.uned.portalreuniones.model.dto;

import javax.validation.constraints.NotEmpty;

/**
 * DTO para controlar los datos que mete el usuario no registrado al recibir el
 * token
 * 
 * @author omontes
 *
 */
public class EventTokenDTO {

	private String token;

	@NotEmpty
	private String nickName;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
