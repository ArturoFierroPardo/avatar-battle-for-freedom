package modelos.unidad.maquina;

import java.util.ArrayList;
import modelos.coordenadas.Coordenadas;
import modelos.jugador.Jugador;
import modelos.ser.Ser;
import modelos.unidad.Unidad;

/**
 * Unidad del bando de la RDA: vehiculos y exoesqueletos tripulados por
 * humanos.
 *
 * @author Arturo Fierro Pardo
 */
public abstract class Maquina extends Unidad {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Maquina.Su hijo esepcifica los atributos.Hereda de la
     * clase unidad su constructor que asigna a los atributos como los
     * parametros que se le asigna, asignados con sus hijos.Datos sin
     * especificar:
     *
     * @param nombre
     * @param jugador
     * @param coste
     * @param energiadefensa
     * @param energiataque
     * @param energiamov
     * @param potencia
     * @param puntosderrota
     * @param tripulantes
     * @param posicion
     * @param energiagastadaxmov
     * @param maxmov
     * @param rangoataque
     * @param seresneed
     */
    public Maquina(String nombre, Jugador jugador, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntosderrota, ArrayList<Ser> tripulantes, Coordenadas posicion, int energiagastadaxmov, int maxmov, int rangoataque, int seresneed) {
        super(nombre, jugador, coste, energiadefensa, energiataque, energiamov, potencia, puntosderrota, tripulantes, posicion, energiagastadaxmov, maxmov, rangoataque, seresneed);

    }

}
