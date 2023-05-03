/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.bestia;

import java.util.ArrayList;
import modelos.configuraciones.Coordenadas;
import modelos.ser.Ser;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public abstract class Bestia extends Unidad {

    /**
     * Constructor de Bestia.Su hijo esepcifica los atributos.Hereda de la clase
     * unidad su constructor que asigna a los atributos como los parametros que
     * se le asigna, asignados con sus hijos. Datos sin especificar:
     *
     * @param nombre
     * @param coste
     * @param energiadefensa
     * @param energiataque
     * @param energiamov
     * @param potencia
     * @param puntosderrota
     * @param tripulantes
     * @param posicion
     */
    public Bestia(String nombre, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntosderrota, ArrayList<Ser> tripulantes, Coordenadas posicion) {
        super(nombre, coste, energiadefensa, energiataque, energiamov, potencia, puntosderrota, tripulantes, posicion);
    }

}
