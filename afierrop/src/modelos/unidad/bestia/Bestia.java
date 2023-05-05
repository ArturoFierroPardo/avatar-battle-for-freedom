/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.bestia;

import java.util.ArrayList;
import modelos.coordenadas.Coordenadas;
import modelos.jugador.Jugador;
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
     * se le asigna, asignados con sus hijos.Datos sin especificar:
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
     * @param seresneed
     */
    public Bestia(String nombre, Jugador jugador, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntosderrota, ArrayList<Ser> tripulantes, Coordenadas posicion, int energiagastadaxmov, String maxmov, int rangoataque, int seresneed) {
        super(nombre, jugador, coste, energiadefensa, energiataque, energiamov, potencia, puntosderrota, tripulantes, posicion, energiagastadaxmov, maxmov, rangoataque, seresneed);
    }

}
