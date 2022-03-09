// Clase que será utilizada para devolver los tokens
public class TokenInfo {

	public static final String COMENTARIO = " COMENTARIO ";
	public static final String PALABRA_RESERVADA = " PALABRA RESERVADA ";
	public static final String IDENTIFICADOR = " IDENTIFICADOR ";
	public static final String CONSTANTE = " CONSTANTE ";
	public static final String CADENA = " CADENA ";
	public static final String DELIMITADOR = " DELIMITADOR ";
	public static final String OPERADOR = " OPERADOR ";

	String _token;
	String _lexema;

	public String getLexema() {
		return this._lexema;
	}

	public String getToken() {
		return this._token;
	}

	TokenInfo(String lexema, String token) {
		this._lexema = lexema;
		this._token = token;
	}

	public String toString() {
		if (this._token.equals(COMENTARIO)) {
			return "ENCONTRADO" + this._token + ";";
		} else {
			return "Lexema: " + this._lexema + " Token:" + this._token;
		}
	}
}