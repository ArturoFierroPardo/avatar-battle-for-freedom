/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.configuraciones;

import java.util.Random;
import modelos.escenario.Escenario;

/**
 *
 * @author artur
 */
public class Coordenadas {

    /**
     * Coordenada en eje X
     */
    private int x;

    /**
     * Coordenada en eje Y
     */
    private int y;

    /**
     * Tipo de escenario. Sirve para dar un limite maximo de coordenada en eje x
     * e y en el constructor de coordenadas aleatorias.
     */
    Escenario escenario;

    /**
     * Constructor de coordenadas, que recibe la coordenada x e y como se le
     * indique.
     *
     * @param x
     * @param y
     */
    public Coordenadas(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor de coordenadas por defecto, el cual pone a los atributos x e
     * y aleatoriamente en funcion del escenario para que no se pase.
     */
    public Coordenadas() {
        Random rand = new Random();
        this.x = rand.nextInt(escenario.getXmax());
        this.y = rand.nextInt(escenario.getYmax());
    }

    /**
     * Setters
     *
     * @param x
     * @param y
     */
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * Getters
     *
     * @return
     */
    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    /**
     * Función toString que devuelve un String con las coordenadas.
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
