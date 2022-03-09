package es.uned.portalreuniones.exception;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = -4378731492460339184L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
