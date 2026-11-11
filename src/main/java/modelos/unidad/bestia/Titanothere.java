package modelos.unidad.bestia;

import java.util.ArrayList;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.ser.Ser;

/**
 * Titanothere Cabezamartillo: bestia barata y muy resistente, pero lenta y
 * de alcance de ataque corto. Gasta una cantidad fija de energia al
 * moverse.
 *
 * @author Arturo Fierro Pardo
 */
public class Titanothere extends Bestia {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Titanothere. Hereda de Bestia y luego de Unidad.
     * Titanothere asigna sus atributos unicos (nombre, coste, energias,
     * potencia, puntos, tripulantes, posicion, energia gastada al moverse,
     * maximo movimiento, rango de ataque y seres necesarios para el
     * funcionamiento), Unidad (el padre de todos) asigna a todos los valores
     * como los que se le pasa por parametro, que son los que se le pasa
     * mediante sus hijos. Posicion y tripulantes se asignan con setters al
     * comprar la unidad
     */
    public Titanothere() {
        super("Titanothere Cabezamartillo", null, 1000, 11000, 11000, 4500, 300, 250, new ArrayList<Ser>(), null, 25, 2, 1, 1);
    }

    /**
     * Override de la funcion restar energia.Esta resta energia de diferente
     * manera a la general.
     *
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     */
    @Override
    public void restarEnergia() throws SinEnergiaMovimientoException {
        if (this.getEnergiamov() >= this.getEnergiagastadaxmov() * (this.getPosicion().getCasillasMovidasX() + this.getPosicion().getCasillasMovidasY())) {
            this.setEnergiamov(this.getEnergiamov() - this.getEnergiagastadaxmov());
        } else {
            throw new SinEnergiaMovimientoException();
        }
    }

}
