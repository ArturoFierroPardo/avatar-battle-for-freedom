/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.bestia;

/**
 *
 * @author artur
 */
public class Banshee extends Bestia {

    /**
     * Constructor de Banshee. Hereda de Bestia y luego de Unidad. Banshee
     * asigna sus atributos unicos (nombre, coste, energias, potencia, puntos,
     * tripulantes y posicion), Unidad (el padre de todos) asigna a todos los
     * valores como los que se le pasa por parametro, que son los que se le pasa
     * mediante sus hijos. Posicion y tripulantes se asignan con setters al
     * comprar la unidad
     */
    public Banshee() {
        super("Banshee de Montaña", 2000, 3500, 8000, 3500, 300, 1000, null, null, 10, "Casilla aleatoria", 3);
    }

}
