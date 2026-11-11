package modelos.ser.humano;

import modelos.ser.Ser;
import modelos.ser.TipoCargo;
import modelos.ser.TipoRaza;

/**
 * Tripulante del bando de la RDA. Fija la raza humana para todos sus hijos.
 *
 * @author Arturo Fierro Pardo
 */
public abstract class Humano extends Ser {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Humano. Solo asigna el atributo de raza especificamente
     * como raza humana, su hijo esepcifica los demas atributos.Hereda de la
     * clase ser su constructor que asigna a los atributos como los parametros
     * que se le asigna, asignados con sus hijos. Datos sin especificar:
     *
     * @param nombre
     * @param coste
     * @param cargo
     * @param vida
     * @param puntosderrota
     */
    public Humano(String nombre, int coste, TipoCargo cargo, int vida, int puntosderrota) {
        super(nombre, coste, cargo, TipoRaza.HUMANO, vida, puntosderrota);

    }

}
