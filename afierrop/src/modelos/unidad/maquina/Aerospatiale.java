/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.maquina;

import java.util.ArrayList;
import modelos.ser.Ser;

/**
 *
 * @author artur
 */
public class Aerospatiale extends Maquina {

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
        super("Aerospatiale SA-2 Samson", null, 2500, 1000, 1000, 500, 80, 1500, new ArrayList<Ser>(), null, 50, "10", 5, 2);
    }

}
