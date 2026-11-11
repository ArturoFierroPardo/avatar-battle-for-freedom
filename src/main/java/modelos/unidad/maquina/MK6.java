package modelos.unidad.maquina;

import java.util.ArrayList;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.ser.Ser;

/**
 * Plataforma de Movilidad Amplificada Mitsubishi MK-6: el exoesqueleto
 * barato de la RDA. Gasta una cantidad fija de energia al moverse.
 *
 * @author Arturo Fierro Pardo
 */
public class MK6 extends Maquina {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de MK6. Hereda de Maquina y luego de Unidad. MK6 asigna sus
     * atributos unicos (nombre, coste, energias, potencia, puntos, tripulantes,
     * posicion, energia gastada al moverse, maximo movimiento, rango de ataque
     * y seres necesarios para el funcionamiento), Unidad (el padre de todos)
     * asigna a todos los valores como los que se le pasa por parametro, que son
     * los que se le pasa mediante sus hijos. Posicion y tripulantes se asignan
     * con setters al comprar la unidad
     */
    public MK6() {
        super("Plataforma de Movilidad Amplificada Mitsubishi MK-6", null, 500, 0, 1000, 500, 20, 50, new ArrayList<Ser>(), null, 25, 2, 1, 1);
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
