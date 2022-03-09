import java.io.BufferedReader;
import java.io.FileReader;

public class PruebaJFlex {

	public static void main(String[] args) {

		try {

			// Asignación del nombre de archivo por defecto que usará la aplicación
			String archivo = "ejemplo_enunciado.txt";

			// Se trata de leer el archivo y analizarlo en la clase que se ha creado con
			// JFlex
			BufferedReader buffer = new BufferedReader(new FileReader(archivo));
			AnalizadorLexico analizadorJFlex = new AnalizadorLexico(buffer);

			while (true) {

				// Obtener el token analizado y mostrar su información
				TokenInfo token = analizadorJFlex.yylex();

				if (!analizadorJFlex.existenTokens())
					break;

				System.out.println(token.toString());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} catch (Error e) {
			System.out.println("ERROR: Encontrado lexema no válido");
		}
	}
}