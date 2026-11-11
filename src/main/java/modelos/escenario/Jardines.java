/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.escenario;

/**
 *
 * @author artur
 */
public class Jardines extends Escenario {

    /**
     * Constructor de Jardines. Hereda de Escenario. Jardines asigna sus
     * atributos unicos (nombre, puntoscomienzo, xmax e ymax), y Escenario (el
     * padre de todos) asigna a todos los valores como los que se le pasa por
     * parametro, que son los que se le pasa mediante sus hijos.
     */
    public Jardines() {
        super("Jardines Colgantes", 20000, 200, 200);
    }

}
