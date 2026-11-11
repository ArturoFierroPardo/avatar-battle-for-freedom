/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser.navi;

import modelos.ser.TipoCargo;

/**
 *
 * @author artur
 */
public class Omaticaya extends Navi {

    /**
     * Constructor de Omaticaya. Hereda de navi y luego de ser. Omaticaya asigna
     * sus atributos unicos (nombre, coste, tipo de cargo, vida y puntos), navi
     * asigna su atributo unico que es la raza navi y los demás los deja, y Ser
     * (el padre de todos) asigna a todos los valores como los que se le pasa
     * por parametro, que son los que se le pasa mediante sus hijos.
     */
    public Omaticaya() {
        super("Omaticaya", 100, TipoCargo.OFICIAL, 300, 400);

    }
}
