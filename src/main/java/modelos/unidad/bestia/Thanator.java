package modelos.unidad.bestia;

import java.util.ArrayList;
import modelos.ser.Ser;

/**
 * Thanator: la bestia mas potente del bando Navi, con mucho aguante y buen
 * alcance de movimiento.
 *
 * @author Arturo Fierro Pardo
 */
public class Thanator extends Bestia {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Thanator. Hereda de Bestia y luego de Unidad. Thanator
     * asigna sus atributos unicos (nombre, coste, energias, potencia, puntos,
     * tripulantes, posicion, energia gastada al moverse, maximo movimiento,
     * rango de ataque y seres necesarios para el funcionamiento), Unidad (el
     * padre de todos) asigna a todos los valores como los que se le pasa por
     * parametro, que son los que se le pasa mediante sus hijos. Posicion y
     * tripulantes se asignan con setters al comprar la unidad
     */
    public Thanator() {
        super("Thanator", null, 8000, 9000, 9000, 6000, 600, 4000, new ArrayList<Ser>(), null, 10, 50, 5, 3);
    }

}
