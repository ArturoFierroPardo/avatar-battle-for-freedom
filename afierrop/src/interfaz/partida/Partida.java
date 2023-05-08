/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.partida;

import interfaz.menu.Menu;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
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
        this.turnos = 0;
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

    /**
     * Funcion que inicia la partida. Crea un menu que recibe por parametro el
     * jugador que juega en funcion de una funcion de turnos y un escenario.
     * Enseña el menu al jugador actual y cuando acabe se añade un turno más.
     * Gracias a un bucle se va repitiendo hasta que alguien gane.
     *
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void comienzaPartida() throws PuntosdeCompraInsuficientesException, FueradeMapaException {
        Menu menu = new Menu(turnos(), escenario);
        menu.enseñarMenu();
        this.turnos++;

        /**
         * bucle while(boolean i = true) que invoque un objeto menu, lo enseñe y
         * el jugador elija que quiere hacer pidiendoselo por pantalla y
         * ejecutar la accion. Ir cambiando turnos cambiando el jugador actual y
         * turnos++ con un bucle if, hasta que uno sea derrotado entonces i =
         * false y se acaba la partida. Hacer funcion para ver cuando alguien
         * gane la partida.
         */
    }

    /**
     * Funcion que pasa los turnos calculando el resto de la variable turnos que
     * empieza en 0.
     *
     * @return
     */
    public Jugador turnos() {
        Jugador jugadorjuega = this.jugador1;
        if (this.turnos % 2 == 0) {
            jugadorjuega = this.jugador1;
        }
        if (this.turnos % 2 == 1) {
            jugadorjuega = this.jugador2;
        }
        return jugadorjuega;
    }

    /**
     * Override de toString que devuelve un String con la informacion de la
     * partida, que jugadores juegan y en que escenario
     */
    @Override
    public String toString() {
        return "La partida comienza, lucharán " + this.jugador1 + " contra " + this.jugador2 + " en el escenario " + this.escenario;
    }

}
