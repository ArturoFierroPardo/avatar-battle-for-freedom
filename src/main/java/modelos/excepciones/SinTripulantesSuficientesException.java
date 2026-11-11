package modelos.excepciones;

/**
 * Se lanza cuando se intenta mover o atacar con una unidad que no tiene
 * todos los tripulantes que necesita para funcionar.
 *
 * @author Arturo Fierro Pardo
 */
public class SinTripulantesSuficientesException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Excepcion para poder comprobar que la unidad tiene tripulantes suficiente
     * en funcion de los que necesita, para realizar un ataque o moverse, se
     * lanza moverunidad y atacar unidad. Si salta, con un bucle se comprueba y
     * se va repitiendo hasta que pueda. Si no se puede con nada, como en todas
     * las funciones se puede cancelar el ataque o el movimiento para no estar
     * en bucle.
     */

}
