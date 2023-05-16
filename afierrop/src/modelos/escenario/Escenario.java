/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.escenario;

import java.io.Serializable;
import modelos.coordenadas.Coordenadas;
import modelos.excepciones.FueradeMapaException;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public abstract class Escenario implements Serializable {

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
     * Mapa de unidades
     */
    private Unidad mapa[][];

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
        this.mapa = new Unidad[this.xmax][this.ymax];
    }

    /**
     * Setters
     *
     * @param mapa
     */
    public void setMapa(Unidad[][] mapa) {
        this.mapa = mapa;
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

    public Unidad[][] getMapa() {
        return this.mapa;
    }

    /**
     * Getter que coge la posicon en el mapa de unidades
     *
     * @param coordenada
     * @return
     */
    public Unidad getunidadMapa(Coordenadas coordenada) {
        int posx = coordenada.getX();
        int posy = coordenada.getY();
        return this.mapa[posx][posy];
    }

    /**
     * Funcion para comprobar el mapa del escenario por si hay unidades o no y
     * añadirlas
     *
     * @param unidad
     * @param posicion
     * @return
     * @throws modelos.excepciones.FueradeMapaException
     */
    public boolean checkMapaAñadir(Unidad unidad, Coordenadas posicion) throws FueradeMapaException {
        if (this.mapa[posicion.getX()][posicion.getY()] == null) {
            this.mapa[posicion.getX()][posicion.getY()] = unidad;
            return true;
        } else {
            return false;
        }

    }

    /**
     * Funcion para comprobar que hay una unidad en el mapa, devuelve true si no
     * hay , si hay un false.
     *
     * @param unidad
     * @param posicion
     * @param escenario
     * @return
     * @throws modelos.excepciones.FueradeMapaException
     */
    public boolean checkMapa(Unidad unidad, Coordenadas posicion, Escenario escenario) throws FueradeMapaException {
        if (this.mapa[posicion.getX()][posicion.getY()] == null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * Función toString que devuelve un String con el nombre, los puntos y las
     * dimensiones del escenario
     */
    @Override
    public String toString() {
        return "\tNombre: " + this.nombre + "\n"
                + "\tPuntos iniciales de cada jugador: " + this.puntoscomienzo + "\n"
                + "\tDimension: " + this.xmax + "X" + this.ymax + "\n";
    }
}
