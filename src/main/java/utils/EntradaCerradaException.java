package utils;

/**
 * Se lanza cuando la entrada estandar se ha cerrado y ya no se puede pedir mas
 * datos al usuario.
 *
 * <p>
 * Es una excepcion no comprobada a proposito: puede surgir en cualquier
 * lectura, y propagarla por toda la cadena de llamadas obligaria a declararla
 * en practicamente todos los metodos del juego sin que ninguno de ellos pueda
 * hacer nada por recuperarse. Se captura una sola vez, en
 * {@code main}, para terminar el programa de forma ordenada.
 *
 * @author Arturo Fierro Pardo
 */
public class EntradaCerradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Crea la excepcion con un mensaje descriptivo por defecto.
     */
    public EntradaCerradaException() {
        super("La entrada estandar se ha cerrado");
    }
}
