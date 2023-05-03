/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.bestia;

/**
 *
 * @author artur
 */
public class Thanator extends Bestia {

    /**
     * Constructor de Thanator. Hereda de Bestia y luego de Unidad. Thanator
     * asigna sus atributos unicos (nombre, coste, energias, potencia, puntos,
     * tripulantes y posicion), Unidad (el padre de todos) asigna a todos los
     * valores como los que se le pasa por parametro, que son los que se le pasa
     * mediante sus hijos. Posicion y tripulantes se asignan con setters al
     * comprar la unidad
     */
    public Thanator() {
        super("Thanator", 8000, 9000, 9000, 6000, 600, 4000, null, null, 10, "50", 5);
    }

}
