package modelos.excepciones;

/**
 * Se lanza al intentar usar una casilla que cae fuera de los limites del
 * escenario.
 *
 * @author Arturo Fierro Pardo
 */
public class FueradeMapaException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Excepcion para las coordenadas que salta si se salen del mapa. Se chequea
     * en coordenadas, try catch en el metodo moverse de jugador que lanzara una
     * excepcion nueva o la misma que se catcheara y se repetira en bucle en el
     * menu del jugador con un while(true) try catch().
     */
}
