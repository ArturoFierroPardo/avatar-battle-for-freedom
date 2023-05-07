/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad;

import java.util.ArrayList;
import modelos.coordenadas.Coordenadas;
import modelos.jugador.Jugador;
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
     * Jugador al que pertenece la unidad
     */
    private Jugador jugador;

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
     * Cuanta energia de movimiento tiene, se gasta en funcion de cuanto se va
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
    private int puntosderrota;

    /**
     * Numero de tripulantes que necesita
     */
    private ArrayList<Ser> tripulantes;

    /**
     * Posición en la que se coloca
     */
    private Coordenadas posicion;

    /**
     * Energia consumida al moverse una casilla
     */
    private int energiagastadaxmov;

    /**
     * Maximas casillas que se puede mover en un turno
     */
    private String maxmov;

    /**
     * Rango de ataque
     */
    private int rangoataque;

    /**
     * Seres necesarios para el funcionamiento
     */
    private int seresneed;

    /**
     * Constructor de Unidad.Recibe por parametros todo sus atributos que son
     * especificados por sus hijos los cuales heredan este constructor.Datos sin
     * especificar:
     *
     * @param nombre
     * @param jugador
     * @param coste
     * @param energiadefensa
     * @param energiataque
     * @param energiamov
     * @param potencia
     * @param puntosderrota
     * @param tripulantes
     * @param posicion
     * @param energiagastadaxmov
     * @param maxmov
     * @param rangoataque
     * @param seresneed
     *
     *
     */
    public Unidad(String nombre, Jugador jugador, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntosderrota, ArrayList<Ser> tripulantes, Coordenadas posicion, int energiagastadaxmov, String maxmov, int rangoataque, int seresneed) {
        this.nombre = nombre;
        this.jugador = jugador;
        this.coste = coste;
        this.energiadefensa = energiadefensa;
        this.energiataque = energiataque;
        this.energiamov = energiamov;
        this.potencia = potencia;
        this.puntosderrota = puntosderrota;
        this.tripulantes = tripulantes;
        this.posicion = posicion;
        this.energiagastadaxmov = energiagastadaxmov;
        this.maxmov = maxmov;
        this.rangoataque = rangoataque;
        this.seresneed = seresneed;

    }

    /**
     * Setters
     *
     * @param energiadefensa
     * @param energiataque
     * @param energiamov
     * @param posicion
     * @param tripulantes
     * @param jugador
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

    public void setPosicion(Coordenadas posicion) {
        this.posicion = posicion;
    }

    public void setTripulantes(ArrayList<Ser> tripulantes) {
        this.tripulantes = tripulantes;
    }

    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Getters
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    public int getCoste() {
        return this.coste;
    }

    public int getEnergiadefensa() {
        return this.energiadefensa;
    }

    public int getEnergiataque() {
        return this.energiataque;
    }

    public int getEnergiamov() {
        return this.energiamov;
    }

    public int getPotencia() {
        return this.potencia;
    }

    public int getPuntosderrota() {
        return this.puntosderrota;
    }

    public ArrayList<Ser> getTripulantes() {
        return this.tripulantes;
    }

    public Coordenadas getPosicon() {
        return this.posicion;
    }

    public int getEnergiagastadaxmov() {
        return this.energiagastadaxmov;
    }

    public String geMaxmov() {
        return this.maxmov;
    }

    public int getRangoataque() {
        return this.rangoataque;
    }

    public int getSeresneed() {
        return this.seresneed;
    }

    public Jugador getJugador() {
        return this.jugador;
    }

    /**
     * Funcion que solo devuelve en modo string el nombre de la unidad
     */
    public String toStringNombre() {
        return this.nombre;
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
                + "Puntos si se derrota: " + this.puntosderrota + " \n"
                + "Tripulantes actuales: " + this.tripulantes + "\n"
                + "Seres necesarios para el funcionamiento: " + this.seresneed + "\n"
                + "Energia que gasta al moverse: " + this.energiagastadaxmov + "\n"
                + "Maximas casillas que puede moverse por turno: " + this.maxmov + "\n"
                + "Rango de ataque: " + this.rangoataque + " casillas\n";
    }
}
