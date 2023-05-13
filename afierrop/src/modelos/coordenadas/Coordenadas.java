/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.coordenadas;

import java.io.Serializable;
import java.util.Random;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public class Coordenadas implements Serializable {

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
     * Casillas movidas en x
     */
    private int casillasmovidasx;

    /**
     * Casillas movidas en y
     */
    private int casillasmovidasy;

    /**
     * Constructor de coordenadas, que recibe la coordenada x e y como se le
     * indique y un escenario en el que se juega para poder ver cual es el
     * limite maximo.
     *
     * @param x
     * @param y
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Coordenadas(int x, int y, Escenario escenario) throws FueradeMapaException {
        this.x = x;
        this.y = y;
        this.escenario = escenario;
    }

    /**
     * Constructor de coordenada que en funcion de la unidad que se le pasa, los
     * parametros y el escenario, construye una coordenada que se genera desde
     * la posicon nueva en la que esta.
     */
    public Coordenadas(Unidad unidad, int x, int y, Escenario escenario, int z) {
        this.x = unidad.getPosicion().getX() + x;
        this.y = unidad.getPosicion().getY() + y;
        this.escenario = escenario;
        if (this.x > this.escenario.getXmax()) {
            this.x = this.escenario.getXmax();
        }
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y > this.escenario.getYmax()) {
            this.y = this.escenario.getYmax();
        }
        if (this.y < 0) {
            this.y = 0;
        }
    }

    /**
     * Constructor de coordenadas que recibe un escenario por parametro para
     * poder ver su limite maximo, el cual pone a los atributos x e y
     * aleatoriamente en funcion del escenario para que no se pase.
     *
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Coordenadas(Escenario escenario) throws FueradeMapaException {
        this.escenario = escenario;
        Random rand = new Random();
        this.x = rand.nextInt(this.escenario.getXmax());
        this.y = rand.nextInt(this.escenario.getYmax());
        checkCoordenadaX(this.x, this.escenario);
        checkCoordenadaY(this.y, this.escenario);
    }

    /**
     * Constructor de coordenada que genera aleatorios en funcion de los
     * parametros que reciba
     */
    public Coordenadas(int x, int y) throws FueradeMapaException {
        Random rand = new Random();
        this.x = rand.nextInt(x);
        this.y = rand.nextInt(y);
        checkCoordenadaX(this.x, this.escenario);
        checkCoordenadaY(this.y, this.escenario);
    }

    /**
     * Constructor de coordenadas que recibe un escenario por parametro para
     * poder ver su limite maximo, el cual pone a los atributos x e y
     * aleatoriamente en funcion de la unidad que se le pasa por parametro para
     * ponerlo dentro de sus limites para que no se pase más la coordenada en la
     * que estaba.Se gestiona cuantas casillas se mueve restando la posicion en
     * x y en y de la anterior coordenada con las de la nueva respectivamente.
     *
     * @param unidad
     * @param x
     * @param y
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Coordenadas(Unidad unidad, int x, int y, Escenario escenario) throws FueradeMapaException {
        int restacasillas;
        this.escenario = escenario;
        Random rand = new Random();
        int xaleatoria = rand.nextInt(-1 * unidad.getMaxmov(), unidad.getMaxmov());
        if (xaleatoria < 0) {
            restacasillas = -xaleatoria;
        } else {
            restacasillas = xaleatoria;
        }
        if (restacasillas == unidad.getMaxmov()) {
            this.y = y;
        } else {
            int yaleatoria = rand.nextInt(-1 * (unidad.getMaxmov() - restacasillas), unidad.getMaxmov() - restacasillas);
            this.y = y + yaleatoria;
        }
        this.x = x + xaleatoria;
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x > escenario.getXmax()) {
            this.x = escenario.getXmax();
        }
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.x > escenario.getXmax()) {
            this.x = escenario.getXmax();
        }
        if (this.y < 0) {
            this.y = 0;
        }
        if (this.y > escenario.getYmax()) {
            this.y = escenario.getYmax();
        }
        checkCoordenadaX(this.x, this.escenario);
        checkCoordenadaY(this.y, this.escenario);
        if (x > this.x) {
            this.casillasmovidasx = -1 * (this.x - x);
        }
        if (x <= this.x) {
            this.casillasmovidasx = this.x - x;
        }
        if (y > this.y) {
            this.casillasmovidasy = -1 * (this.y - y);
        }
        if (y <= this.y) {
            this.casillasmovidasy = this.y - y;
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
        checkCoordenadaX(x, this.escenario);

    }

    public void setY(int y) throws FueradeMapaException {
        this.y = y;
        checkCoordenadaY(this.y, this.escenario);
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

    public int getCasillasMovidasY() {
        return this.casillasmovidasy;
    }

    public int getCasillasMovidasX() {
        return this.casillasmovidasx;
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
