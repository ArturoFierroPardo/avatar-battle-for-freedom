package modelos.excepciones;

/**
 * Se lanza cuando una unidad se queda sin energia de movimiento.
 *
 * @author Arturo Fierro Pardo
 */
public class SinEnergiaMovimientoException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Excepcion para poder comprobar que la unidad tiene energia de movimiento
     * suficiente para moverse, se lanza en mover unidad y el menu. Si salta,
     * con un bucle se comprueba y se va repitiendo hasta que pueda. Si no se
     * puede con nada, como en todas las funciones se puede cancelar el ataque
     * para no estar en bucle.
     */

}
