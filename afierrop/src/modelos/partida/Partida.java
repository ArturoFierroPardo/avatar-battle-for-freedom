/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.partida;

import modelos.escenario.Escenario;
import modelos.jugador.Jugador;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public class Partida {

    /**
     * Primer jugador
     */
    private Jugador jugador1;

    /**
     * Segundo jugador
     */
    private Jugador jugador2;

    /**
     * Escenario en el que van a jugar
     */
    private Escenario escenario;

    /**
     * Mapa en el que se juega. Para guardar donde estan las unidades
     */
    private Unidad[][] mapa;

    /**
     * turnos de la partida
     */
    private int turnos;

    /**
     * Constructor de partida en el que se pasa por parametros dos jugadores que
     * jugaran uno contra el otro y un escenario en el que jugar.
     *
     * @param jugador1
     * @param jugador2
     * @param escenario
     */
    public Partida(Jugador jugador1, Jugador jugador2, Escenario escenario) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.escenario = escenario;
        this.mapa = new Unidad[escenario.getXmax()][escenario.getYmax()];
    }

    /**
     * Getters
     *
     * @return
     */
    public Jugador getJugador1() {
        return this.jugador1;
    }

    public Jugador getJugador2() {
        return this.jugador2;
    }

    public int getTurnos() {
        return this.turnos;
    }

    @Override
    public String toString() {
        return "La partida comienza, lucharán " + this.jugador1 + " contra " + this.jugador2 + " en el escenario " + this.escenario;
    }

}
