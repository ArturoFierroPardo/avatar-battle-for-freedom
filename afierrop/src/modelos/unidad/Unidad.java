/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad;

import java.util.ArrayList;
import modelos.configuraciones.Coordenadas;
import modelos.ser.Ser;

/**
 *
 * @author artur
 */
public abstract class Unidad {

    /**
     * Nombre de la unidad
     */
    private String nombre;

    /**
     * Coste de la unidad
     */
    private int coste;

    /**
     * Cuanta vida tiene para que se destruya
     */
    private int energiadefensa;

    /**
     * Cuanta energia tiene de ataque, se gasta en funcion de cuantos ataques va
     * haciendo
     */
    private int energiataque;

    /**
     * Cuanta energia de movimiento, se gasta en funcion de cuanto se va
     * moviendo
     */
    private int energiamov;

    /**
     * Cuanto se le resta a la energia de ataque por cada ataque hecho y el daño
     * que hace
     */
    private int potencia;

    /**
     * Puntos que se ganan al destruirlo
     */
    private int puntos;

    /**
     * Numero de tripulantes que necesita
     */
    private ArrayList<Ser> tripulantes;

    /**
     * Posición en la que se coloca
     */
    private Coordenadas posicion;

    /**
     * Constructor de Unidad.Recibe por parametros todo sus atributos que son
     * especificados por sus hijos los cuales heredan este constructor.Datos
     * sinespecificar:
     *
     * @param nombre
     * @param coste
     * @param energiadefensa
     * @param energiataque
     * @param energiamov
     * @param potencia
     * @param puntos
     * @param tripulantes
     * @param posicion
     *
     *
     */
    public Unidad(String nombre, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntos, ArrayList<Ser> tripulantes, Coordenadas posicion) {
        this.nombre = nombre;
        this.coste = coste;
        this.energiadefensa = energiadefensa;
        this.energiataque = energiataque;
        this.energiamov = energiamov;
        this.potencia = potencia;
        this.puntos = puntos;
        this.tripulantes = null;
        this.posicion = null;

    }

    /**
     * Setters
     *
     * @param energiadefensa
     * @param energiataque
     * @param energiamov
     */
    public void setEnergiadefensa(int energiadefensa) {
        this.energiadefensa = energiadefensa;
    }

    public void setEnergiataque(int energiataque) {
        this.energiataque = energiataque;
    }

    public void setEnergiamov(int energiamov) {
        this.energiamov = energiamov;
    }

    /**
     * Getters
     *
     * @return
     */
    public int getEnergiadefensa() {
        return this.energiadefensa;
    }

    public int getEnergiataque() {
        return this.energiataque;
    }

    public int getEnergiamov() {
        return this.energiamov;
    }

    /**
     * Override de toString, al cual acceden todos sus hijos cada uno con sus
     * datos gracias a la herencia de clases.
     */
    @Override
    public String toString() {
        return "Nombre: " + this.nombre + "\n"
                + "Coste: " + this.coste + "\n"
                + "Energia de defensa: " + this.energiadefensa + "\n"
                + "Energia de ataque: " + this.energiataque + "\n"
                + "Energia de movimiento: " + this.energiamov + "\n"
                + "Potencia: " + this.potencia + "\n"
                + "Puntos: " + this.puntos + " \n";

    }
}
