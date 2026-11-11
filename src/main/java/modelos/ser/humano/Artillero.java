/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser.humano;

import modelos.ser.TipoCargo;

/**
 *
 * @author artur
 */
public class Artillero extends Humano {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Artillero. Hereda de humano y luego de ser. Artillero
     * asigna sus atributos unicos (nombre, coste, tipo de cargo, vida y
     * puntos), Humano asigna su atributo unico que es la raza humana y los
     * demás los deja, y Ser (el padre de todos) asigna a todos los valores como
     * los que se le pasa por parametro, que son los que se le pasa mediante sus
     * hijos.
     */
    public Artillero() {
        super("Artillero", 50, TipoCargo.GUERRERO, 150, 100);
    }
}
