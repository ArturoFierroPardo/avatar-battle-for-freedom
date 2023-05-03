/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.jugador;

import modelos.ejercito.Ejercito;
import modelos.equipo.Equipo;
import modelos.escenario.Escenario;

/**
 *
 * @author artur
 */
public class Jugador {

    /**
     * Nombre del jugador
     */
    private String nombre;

    /**
     * Tipo de equipo del jugador
     */
    private Equipo equipo;

    /**
     * Cuantos puntos tiene el jugador
     */
    private int puntos;

    /**
     * El ejercito del jugador
     */
    private Ejercito ejercito;

    /**
     * Tipo de escenario en el que juega el jugador
     */
    private Escenario escenario;

    /**
     * Constructor de un jugador el cual recibira por parametros, el nombre de
     * jugador, su equipo (Navi o RDA) y el escenario en el que va a
     * jugar.Además, cuenta con un atributo ejercito que se instancia en null,
     * ya que se irán añadiendo según compre unidades.El atributo puntos, se
     * inicializa con los puntoscomienzo que da el escenario.
     *
     * @param nombre
     * @param equipo
     * @param escenario
     */
    public Jugador(String nombre, Equipo equipo, Escenario escenario) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.puntos = escenario.getPuntoscomienzo();
        this.ejercito = null;
    }

    /**
     * Setters
     *
     * @param ejercito
     */
    public void setEjercito(Ejercito ejercito) {
        this.ejercito = ejercito;
    }

    /**
     * Getters
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public Ejercito getEjercito() {
        return this.ejercito;
    }

    /**
     * Función toString que devuelve un string con el nombre, el equipo y los
     * puntos del jugador
     */
    @Override
    public String toString() {
        return "El jugador " + this.nombre + " es del equipo " + this.equipo + " y consta de " + this.puntos + " puntos";
    }

}
