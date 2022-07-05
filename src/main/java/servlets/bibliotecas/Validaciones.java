package servlets.bibliotecas;

public class Validaciones {
	
	private static final String letras = "ÑQAEIOU";

	public static boolean validarMatricula(String matricula) {
		
		boolean respuesta = true;
		
		// Comprueba si la metrícula está vacía o tiene menos de 7 carácteres
		if (matricula == null || matricula.trim().length() != 7) {
			respuesta = false;
		}

		// Comprueba si tiene una de las letras no permitidas de la lista "letras"
		for(int y=3; y<matricula.length(); y++) {
			for(int x=0; x<letras.length(); x++) {
				if (matricula.charAt(y) == letras.charAt(x)) {
					respuesta = false;
				}
			}
		}
		
		// Comprueba si los primeros 4 carácteres son números
		for(int y=1; y<=3; y++) {
			if (Character.isDigit(matricula.charAt(y))) {
			} else {
				respuesta = false;
				break;
			}
		}
		
		// Comprueba si los últimos 3 carácteres son letras
		for(int y=4; y<=6; y++) {
			if (Character.isLetter(matricula.charAt(y))) {
			} else {
				respuesta = false;
				break;
			}
		}
		
		return respuesta;

	}
}
