/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.escenario;

/**
 *
 * @author artur
 */
public class ArbolMadre extends Escenario {

    /**
     * Constructor de Arbol Madre. Hereda de Escenario. Arbol Madre asigna sus
     * atributos unicos (nombre, puntoscomienzo, xmax e ymax), y Escenario (el
     * padre de todos) asigna a todos los valores como los que se le pasa por
     * parametro, que son los que se le pasa mediante sus hijos.
     */
    public ArbolMadre() {
        super("Arbol Madre", 45000, 300, 300);
    }

}
