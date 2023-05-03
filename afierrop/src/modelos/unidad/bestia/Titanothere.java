/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.bestia;

/**
 *
 * @author artur
 */
public class Titanothere extends Bestia {

    /**
     * Constructor de Titanothere. Hereda de Bestia y luego de Unidad.
     * Titanothere asigna sus atributos unicos (nombre, coste, energias,
     * potencia, puntos, tripulantes y posicion), Unidad (el padre de todos)
     * asigna a todos los valores como los que se le pasa por parametro, que son
     * los que se le pasa mediante sus hijos. Posicion y tripulantes se asignan
     * con setters al comprar la unidad
     */
    public Titanothere() {
        super("Titanothere Cabezamartillo", 1000, 11000, 11000, 4500, 300, 250, null, null, 25, "2", 1);
    }

}
