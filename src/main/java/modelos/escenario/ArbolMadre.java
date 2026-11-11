package modelos.escenario;

/**
 * Escenario del Arbol Madre: el mas grande, 300x300 casillas y 45.000
 * puntos iniciales por jugador.
 *
 * @author Arturo Fierro Pardo
 */
public class ArbolMadre extends Escenario {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Arbol Madre. Hereda de Escenario. Arbol Madre asigna sus
     * atributos unicos (nombre, puntoscomienzo, xmax e ymax), y Escenario (el
     * padre de todos) asigna a todos los valores como los que se le pasa por
     * parametro, que son los que se le pasa mediante sus hijos.
     */
    public ArbolMadre() {
        super("Arbol Madre", 45000, 300, 300);
    }

}
