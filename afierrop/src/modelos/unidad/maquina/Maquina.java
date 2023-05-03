/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.maquina;

import java.util.ArrayList;
import modelos.configuraciones.Coordenadas;
import modelos.ser.Ser;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public abstract class Maquina extends Unidad {

    /**
     * Constructor de Maquina.Su hijo esepcifica los atributos.Hereda de la
     * clase unidad su constructor que asigna a los atributos como los
     * parametros que se le asigna, asignados con sus hijos. Datos sin
     * especificar:
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
     * @param energiagastadaxmov
     * @param maxmov
     * @param rangoataque
     */
    public Maquina(String nombre, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntosderrota, ArrayList<Ser> tripulantes, Coordenadas posicion, int energiagastadaxmov, String maxmov, int rangoataque) {
        super(nombre, coste, energiadefensa, energiataque, energiamov, potencia, puntosderrota, tripulantes, posicion, energiagastadaxmov, maxmov, rangoataque);
    }

}
