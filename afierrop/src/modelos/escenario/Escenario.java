/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.escenario;

/**
 *
 * @author artur
 */
public abstract class Escenario {

    /**
     * Nombre del escenario
     */
    private String nombre;

    /**
     * Puntos que da el escenario al comenzar
     */
    private int puntoscomienzo;

    /**
     * Limite maximo de coordenada en el eje X
     */
    private int xmax;

    /**
     * Limite maximo de coordenada en el eje Y
     */
    private int ymax;

    /**
     * Constructor de escenario, que recibe un nombre, puntos de comienzo, y las
     * dimensiones del escenario.Gracias a sus hijos recibe directamente estos
     * atributos.Esta clase abstracta contiene todos los metodos de escenario
     * que sus hijos podrán tulizar.
     *
     * @param nombre
     * @param puntoscomienzo
     * @param xmax
     * @param ymax
     */
    public Escenario(String nombre, int puntoscomienzo, int xmax, int ymax) {
        this.nombre = nombre;
        this.puntoscomienzo = puntoscomienzo;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    /**
     * Getters
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    public int getPuntoscomienzo() {
        return this.puntoscomienzo;
    }

    public int getXmax() {
        return this.xmax;
    }

    public int getYmax() {
        return this.ymax;
    }

    /**
     * Función toString que devuelve un String con el nombre, los puntos y las
     * dimensiones del escenario
     */
    @Override
    public String toString() {
        return "El escenario es " + this.nombre + " con " + this.puntoscomienzo + " puntos para cada jugador y una dimensión de " + this.xmax + "X" + this.ymax;
    }
}
