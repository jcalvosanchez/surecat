package com.htic.hticarq.core.util;

public class StringUtils {

	/**
	 * Rellena la variable cadena por la izquierda con los caracteres introducidos hasta alcanzar 
	 * el tamaño informado.
	 */
	public static String rellenarIzquierda (String cadena, String caracter, int tamano) {

		while (cadena.length() < tamano) {
			cadena = caracter.concat(cadena);
		}

		return cadena;
	}

	/**
	 * Rellena la variable cadena por la derecha con los caracteres introducidos hasta alcanzar 
	 * el tamañ informado.
	 */
	public static String rellenarDerecha (String cadena, String caracter, int tamano) {

		while (cadena.length() < tamano) {
			cadena = cadena.concat(caracter);
		}

		return cadena;
	}

	/**
	 * Removes a character from the given String Chain.
	 */
	public static String removeChar (String stringChain, char character) {
		int characterIndex;

		while ((characterIndex = stringChain.indexOf(character)) != -1) {
			if (characterIndex == stringChain.length()) {
				stringChain = stringChain.substring(0, characterIndex);
			} else {
				stringChain = stringChain.substring(0, characterIndex).
					concat(stringChain.substring(characterIndex+1));
			}
		}

		return stringChain;
	}

	public static String intToString (int i, int padding) {
		String result = "" + i;

		while (result.length() < padding) {
			result = "0".concat(result);
		}

		return result;
	}
}