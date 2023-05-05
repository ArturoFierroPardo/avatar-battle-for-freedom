/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.coordenadas;

import java.util.Random;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import utils.Utils;

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
    private Escenario escenario;

    /**
     * Constructor de coordenadas, que recibe la coordenada x e y como se le
     * indique y un escenario en el que se juega para poder ver cual es el
     * limite maximo.
     *
     * @param x
     * @param y
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Coordenadas(int x, int y, Escenario escenario) throws FueradeMapaException {
        while (true) {
            try {
                this.x = x;
                this.y = y;
                checkCoordenadaX(this.x, escenario);
                checkCoordenadaY(this.y, escenario);
                break;
            } catch (FueradeMapaException fme) {
                x = Utils.leerEntero("Vuelva a introducir su posicion en X que no se pase del limite " + escenario.getXmax() + ":");
                y = Utils.leerEntero("Vuelva a introducir su posicion en Y que no se pase del limite " + escenario.getYmax() + ":");
            }
        }
    }

    /**
     * Constructor de coordenadas que recibe un escenario por parametro para
     * poder ver su limite maximo, el cual pone a los atributos x e y
     * aleatoriamente en funcion del escenario para que no se pase.
     *
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Coordenadas(Escenario escenario) throws FueradeMapaException {
        while (true) {
            try {
                Random rand = new Random();
                this.x = rand.nextInt(escenario.getXmax());
                this.y = rand.nextInt(escenario.getYmax());
                checkCoordenadaX(this.x, escenario);
                checkCoordenadaY(this.y, escenario);
                break;
            } catch (FueradeMapaException fme) {
                Random rand = new Random();
                x = rand.nextInt(escenario.getXmax());
                y = rand.nextInt(escenario.getYmax());
            }
        }
    }

    /**
     * Setters
     *
     * @param x
     * @param y
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void setX(int x) throws FueradeMapaException {
        this.x = x;
        checkCoordenadaX(x, escenario);

    }

    public void setY(int y) throws FueradeMapaException {
        this.y = y;
        checkCoordenadaY(this.y, escenario);
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
     * Funcion para comprobar la excepcion de si esta fuera del mapa la
     * coordenada X. Recibe un escenario para comprobar dependiendo de su maximo
     * en X.
     *
     * @param X
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void checkCoordenadaX(int X, Escenario escenario) throws FueradeMapaException {
        if (X > escenario.getXmax()) {
            throw new FueradeMapaException();
        }
    }

    /**
     * Funcion para comprobar la excepcion de si esta fuera del mapa la
     * coordenada Y.Recibe un escenario para comprobar dependiendo de su maximo
     * en Y.
     *
     * @param Y
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void checkCoordenadaY(int Y, Escenario escenario) throws FueradeMapaException {
        if (Y > escenario.getYmax()) {
            throw new FueradeMapaException();
        }
    }

    /**
     * Función toString que devuelve un String con las coordenadas.
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

}
