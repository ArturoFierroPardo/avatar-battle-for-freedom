package modelos.excepciones;

/**
 * Se lanza cuando el jugador no tiene puntos suficientes para la compra que
 * intenta hacer.
 *
 * @author Arturo Fierro Pardo
 */
public class PuntosdeCompraInsuficientesException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Excepcion que se lanza en la clase jugador al intentar comprar una unidad
     * sin tener puntos suficientes. En el menu se hara otra excpecion la cual
     * hara que se repita en bucle.
     */
}
