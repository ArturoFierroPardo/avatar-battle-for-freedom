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
public class Tipani extends Navi {

    /**
     * Constructor de Tipani. Hereda de navi y luego de ser. Omaticaya asigna
     * sus atributos unicos (nombre, coste, tipo de cargo, vida y puntos), navi
     * asigna su atributo unico que es la raza navi y los demás los deja, y Ser
     * (el padre de todos) asigna a todos los valores como los que se le pasa
     * por parametro, que son los que se le pasa mediante sus hijos.
     */
    public Tipani() {
        super("Tipani", 50, TipoCargo.GUERRERO, 300, 250);

    }
}
