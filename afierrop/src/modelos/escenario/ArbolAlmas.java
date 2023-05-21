/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.escenario;

/**
 *
 * @author artur
 */
public class ArbolAlmas extends Escenario {

    /**
     * Constructor de Arbol de las Almas. Hereda de Escenario. Arbol de las
     * Almas asigna sus atributos unicos (nombre, puntoscomienzo, xmax e ymax),
     * y Escenario (el padre de todos) asigna a todos los valores como los que
     * se le pasa por parametro, que son los que se le pasa mediante sus hijos.
     */
    public ArbolAlmas() {
        super("Arbol de las Almas", 5000, 100, 100);
    }

}
