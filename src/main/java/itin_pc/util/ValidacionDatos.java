package itin_pc.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ValidacionDatos {

    /**
     * Valida si una cadena contiene solo letras y espacios. No permite números,
     * símbolos ni cadenas vacías o nulas.
     *
     * Ejemplos de uso:
     *
     * <pre>
     * esSoloTexto("Juan Perez"); // true
     * esSoloTexto("1234"); // false
     * esSoloTexto("Juan123"); // false
     * esSoloTexto("Juan Perez!");// false
     * </pre>
     *
     * @param texto La cadena de texto a validar.
     * @return {@code true} si la cadena contiene solo letras y espacios;
     * {@code false} si contiene números, símbolos, está vacía o es nula.
     */
    public static boolean esSoloTexto(String texto) {
        return texto != null && !texto.trim().isEmpty() && texto.matches("^[A-Za-zÁÉÍÓÚáéíóúÑñ ]+$");
    }


    /*
     * Valida si una cadena es un texto general
     * 
     * Ejemplos de uso:
     * 
     * <pre>
     * esTextoGeneral("Hola Mundo"); // true
     * esTextoGeneral(""); // false
     * esTextoGeneral(null); // false
     * esTextoGeneral("   "); // false
     * esTextoGeneral("Hola123"); // true
     * </pre>
     * 
     * @param texto La cadena de texto a validar.
     * @return {@code true} si la cadena no es nula ni vacía (
     */
    public static boolean esTextoGeneral(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }

    // Valida una fecha con el formato indicado (ej. "yyyy-MM-dd")
    public static boolean esFecha(String fecha, String formato) {
        if (fecha == null || formato == null) {
            return false;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        sdf.setLenient(false);
        try {
            sdf.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    // Valida si es un número entero
    public static boolean esEntero(Object valor) {
        return valor instanceof Integer;
    }

    // Valida si es un número decimal
    public static boolean esDecimal(Object valor) {
        return valor instanceof Double || valor instanceof Float;
    }

    // Valida si el rango esta entre enero(1) y diciembre(12)
    public static boolean esMesValido(int mes) {
        return mes >= 1 && mes <= 12;
    }

    // Valida que el año este correcto
    public static boolean esAnioValido(int anio) {
        return anio >= 1900 && anio <= 2025;
    }

}
