package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

/**
 * Utilidades para leer datos por la entrada estandar.
 *
 * <p>
 * La clase mantiene un unico {@link BufferedReader} compartido sobre
 * {@code System.in}. Esto es importante: si cada lectura creara su propio
 * lector, el buffer interno de cada instancia leeria por adelantado un bloque
 * entero de la entrada y lo descartaria al quedar el objeto fuera de uso, de
 * forma que las lineas siguientes se perderian. Con teclado el efecto pasa
 * desapercibido porque la consola entrega los datos linea a linea, pero con la
 * entrada redirigida desde un fichero o una tuberia se pierden datos.
 *
 * @author Arturo Fierro Pardo
 */
public final class Utils {

    /**
     * Lector unico y compartido sobre la entrada estandar.
     */
    private static BufferedReader consola;

    /**
     * Clase de utilidades: no se instancia.
     */
    private Utils() {
    }

    /**
     * Devuelve el lector compartido, creandolo la primera vez sobre la entrada
     * estandar que haya en ese momento.
     *
     * @return el lector de la entrada estandar
     */
    private static BufferedReader consola() {
        if (consola == null) {
            consola = new BufferedReader(new InputStreamReader(System.in));
        }
        return consola;
    }

    /**
     * Hace que las siguientes lecturas se tomen de la entrada indicada.
     *
     * <p>
     * Pensado para las pruebas automaticas y para cuando se sustituye
     * {@code System.in}: sin esto el lector quedaria atado para siempre a la
     * entrada que hubiera al cargar la clase.
     *
     * @param entrada nueva fuente de datos, o {@code null} para volver a
     * {@code System.in}
     */
    public static void usarEntrada(Reader entrada) {
        consola = entrada == null ? null : new BufferedReader(entrada);
    }

    /**
     * Lee una linea de la entrada estandar mostrando antes un mensaje.
     *
     * @param mensaje texto que se muestra al usuario antes de leer
     * @return la linea leida, sin el salto de linea final
     * @throws EntradaCerradaException si la entrada estandar se ha cerrado
     */
    public static String leerCadena(String mensaje) {
        System.out.println(mensaje);
        return leerCadena();
    }

    /**
     * Lee una linea de la entrada estandar sin mostrar ningun mensaje.
     *
     * @return la linea leida, sin el salto de linea final
     * @throws EntradaCerradaException si la entrada estandar se ha cerrado
     * @throws UncheckedIOException si falla la lectura por otro motivo
     */
    public static String leerCadena() {
        String linea;
        try {
            linea = consola().readLine();
        } catch (IOException ex) {
            throw new UncheckedIOException("Error de lectura por entrada estandar", ex);
        }
        if (linea == null) {
            // readLine() devuelve null al llegar al final de la entrada.
            // Sin este control, quien llama a leerEntero() volveria a pedir el
            // dato indefinidamente, porque el fin de entrada no se corrige
            // repitiendo la pregunta.
            throw new EntradaCerradaException();
        }
        return linea;
    }

    /**
     * Pide un numero entero por la entrada estandar y lo repite hasta que el
     * usuario introduzca uno valido.
     *
     * @param mensaje texto que se muestra al usuario antes de leer
     * @return el entero introducido
     * @throws EntradaCerradaException si la entrada estandar se ha cerrado
     */
    public static int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerCadena(mensaje).trim());
            } catch (NumberFormatException nfe) {
                System.out.println("Debe introducir un numero, por favor, repita");
            }
        }
    }

    /**
     * Pide un numero decimal de doble precision y lo repite hasta que el
     * usuario introduzca uno valido.
     *
     * @param mensaje texto que se muestra al usuario antes de leer
     * @return el numero introducido
     * @throws EntradaCerradaException si la entrada estandar se ha cerrado
     */
    public static double leerDouble(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerCadena(mensaje).trim());
            } catch (NumberFormatException nfe) {
                System.out.println("Debe introducir un numero, por favor, repita");
            }
        }
    }

    /**
     * Pide un numero decimal de precision simple y lo repite hasta que el
     * usuario introduzca uno valido.
     *
     * @param mensaje texto que se muestra al usuario antes de leer
     * @return el numero introducido
     * @throws EntradaCerradaException si la entrada estandar se ha cerrado
     */
    public static float leerFloat(String mensaje) {
        while (true) {
            try {
                return Float.parseFloat(leerCadena(mensaje).trim());
            } catch (NumberFormatException nfe) {
                System.out.println("Debe introducir un numero, por favor, repita");
            }
        }
    }
}
