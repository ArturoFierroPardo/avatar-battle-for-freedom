/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser.humano;

import modelos.ser.Ser;
import modelos.ser.TipoCargo;
import modelos.ser.TipoRaza;

/**
 *
 * @author artur
 */
public abstract class Humano extends Ser {

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
     * @param puntos
     */
    public Humano(String nombre, int coste, TipoCargo cargo, int vida, int puntos) {
        super(nombre, coste, cargo, TipoRaza.HUMANO, vida, puntos);

    }

}
