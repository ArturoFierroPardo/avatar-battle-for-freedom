package modelos.escenario;

/**
 * Escenario del Arbol de las Almas: el mas pequeno, 100x100 casillas y
 * 5.000 puntos iniciales por jugador.
 *
 * @author Arturo Fierro Pardo
 */
public class ArbolAlmas extends Escenario {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Arbol de las Almas. Hereda de Escenario. Arbol de las
     * Almas asigna sus atributos unicos (nombre, puntoscomienzo, xmax e ymax),
     * y Escenario (el padre de todos) asigna a todos los valores como los que
     * se le pasa por parametro, que son los que se le pasa mediante sus hijos.
     */
    public ArbolAlmas() {
        super("Arbol de las Almas", 5000, 100, 100);
    }

}
