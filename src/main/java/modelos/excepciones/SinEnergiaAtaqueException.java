package modelos.excepciones;

/**
 * Se lanza cuando una unidad se queda sin energia de ataque.
 *
 * @author Arturo Fierro Pardo
 */
public class SinEnergiaAtaqueException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Excepcion para poder comprobar que la unidad tiene energia de ataque
     * suficiente para realizar un ataque, se lanza en recibir daño, restar
     * energia y atacar unidad. Si salta, con un bucle se comprueba y se va
     * repitiendo hasta que pueda. Si no se puede con nada, como en todas las
     * funciones se puede cancelar el ataque para no estar en bucle.
     */

}
