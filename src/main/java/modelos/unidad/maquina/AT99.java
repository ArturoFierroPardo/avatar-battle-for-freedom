package modelos.unidad.maquina;

import java.util.ArrayList;
import modelos.ser.Ser;

/**
 * AT-99 Escorpion: el helicoptero artillado de la RDA, la unidad mas cara y
 * potente del juego.
 *
 * @author Arturo Fierro Pardo
 */
public class AT99 extends Maquina {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de AT99. Hereda de Maquina y luego de Unidad. AT99 asigna sus
     * atributos unicos (nombre, coste, energias, potencia, puntos, tripulantes
     * posicion, energia gastada al moverse, maximo movimiento, rango de ataque
     * y seres necesarios para el funcionamiento), Unidad (el padre de todos)
     * asigna a todos los valores como los que se le pasa por parametro, que son
     * los que se le pasa mediante sus hijos. Posicion y tripulantes se asignan
     * con setters al comprar la unidad
     */
    public AT99() {
        super("AT-99 Escorpion", null, 15000, 7500, 7500, 5000, 500, 10000, new ArrayList<Ser>(), null, 100, 20, 10, 4);
    }

}
