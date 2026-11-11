/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser.navi;

import modelos.ser.Ser;
import modelos.ser.TipoCargo;
import modelos.ser.TipoRaza;

/**
 *
 * @author artur
 */
public abstract class Navi extends Ser {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Navi.Solo asigna el atributo de raza especificamente como
     * raza navi, su hijo esepcifica los demas atributos.Hereda de la clase ser
     * su constructor que asigna a los atributos como los parametros que se le
     * asigna, asignados con sus hijos. Datos sin especificar:
     *
     * @param nombre
     * @param coste
     * @param cargo
     * @param vida
     * @param puntosderrota
     */
    public Navi(String nombre, int coste, TipoCargo cargo, int vida, int puntosderrota) {
        super(nombre, coste, cargo, TipoRaza.NAVI, vida, puntosderrota);
    }

}
