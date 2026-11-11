package modelos.unidad.maquina;

import java.util.ArrayList;
import modelos.ser.Ser;

/**
 * Aerospatiale SA-2 Samson: transporte ligero de la RDA, equilibrado en
 * coste y alcance.
 *
 * @author Arturo Fierro Pardo
 */
public class Aerospatiale extends Maquina {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Aerospatiale. Hereda de Maquina y luego de Unidad.
     * Aerospatiale asigna sus atributos unicos (nombre, coste, energias,
     * potencia, puntos, tripulantes, posicion, energia gastada al moverse,
     * maximo movimiento, rango de ataque y seres necesarios para el
     * funcionamiento), Unidad (el padre de todos) asigna a todos los valores
     * como los que se le pasa por parametro, que son los que se le pasa
     * mediante sus hijos. Posicion y tripulantes se asignan con setters al
     * comprar la unidad
     */
    public Aerospatiale() {
        super("Aerospatiale SA-2 Samson", null, 2500, 1000, 1000, 500, 80, 1500, new ArrayList<Ser>(), null, 50, 10, 5, 2);
    }

}
