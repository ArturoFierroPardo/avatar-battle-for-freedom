/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser;

/**
 *
 * @author artur
 */
public abstract class Ser {

    /**
     * Cuanto cuesta el ser
     */
    private int coste;

    /**
     * Tipo de cargo
     */
    private TipoCargo cargo;

    /**
     * Tipo de raza
     */
    private TipoRaza raza;

    /**
     * Cuanto ataque tiene el ser
     */
    private int vida;

    /**
     * Puntos que se recibe al derrotarlo
     */
    private int puntos;

    /**
     * Nombre del ser
     */
    private String nombre;

    /**
     * Constructor de Ser.Recibe por parametros todo sus atributos que son
     * especificados por sus hijos los cuales heredan este constructor.Datos sin
     * especificar:
     *
     * @param nombre
     * @param coste
     * @param cargo
     * @param raza
     * @param vida
     * @param puntos
     */
    public Ser(String nombre, int coste, TipoCargo cargo, TipoRaza raza, int vida, int puntos) {
        this.nombre = nombre;
        this.cargo = cargo;
        this.coste = coste;
        this.raza = raza;
        this.vida = vida;
        this.puntos = puntos;
    }
}
