/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.partida;

import interfaz.menu.Menu;
import static io.Io.guardarPartida;
import java.io.FileNotFoundException;
import java.io.Serializable;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.excepciones.SinTripulantesSuficientesException;
import modelos.jugador.Jugador;

/**
 *
 * @author artur
 */
public class Partida implements Serializable {

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
        this.turnos = 1;
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
     * Funcion que inicia la partida.Crea un menu que recibe por parametro el
     * jugador que juega en funcion de una funcion de turnos y un
     * escenario.Enseña el menu al jugador 1 o 2 y cuando acabe se añade un
     * turno más, así, se pasa gracias a un contador los turnos.Gracias a un
     * bucle dowhile se va repitiendo hasta que alguien gane. La condicion para
     * ganar una partida es que el enemigo no tenga unidades y que no tenga
     * suficientes puntos para comprar algo.
     *
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     */
    public void comienzaPartida() throws PuntosdeCompraInsuficientesException, FueradeMapaException, SinEnergiaMovimientoException, SinEnergiaAtaqueException, SinTripulantesSuficientesException, FileNotFoundException {
        System.out.println(toString());
        boolean bucle = true;
        do {
            if (this.turnos % 2 == 1) {
                System.out.println("TURNO---------->  " + this.turnos);
                Menu menu = new Menu(this.jugador1, this.escenario);
                menu.enseñarMenu();
                this.turnos++;
            }
            if (this.jugador1.getAcabarPartida() == true) {
                this.jugador1.setAcabarPartida(false);
                this.jugador2.setAcabarPartida(false);
                guardarPartida(this);
                System.out.println("LA PARTIDA SE HA GUARDADO Y FINALIZADO");
                bucle = false;
            }
            if (this.turnos % 2 == 0) {
                System.out.println("TURNO---------->  " + this.turnos);
                Menu menu = new Menu(this.jugador2, this.escenario);
                menu.enseñarMenu();
                this.turnos++;
            }
            if (this.jugador2.getAcabarPartida() == true) {
                this.jugador1.setAcabarPartida(false);
                this.jugador2.setAcabarPartida(false);
                guardarPartida(this);
                System.out.println("LA PARTIDA SE HA GUARDADO Y FINALIZADO");
                bucle = false;
            }
            if (this.jugador1.getEjercito().getUnidades().isEmpty() && this.turnos > 5) {
                System.out.println("\tEL GANADOR DE LA PARTIDA ES " + this.jugador2.getNombre());
                bucle = false;
            }
            if (this.jugador2.getEjercito().getUnidades().isEmpty() && this.turnos > 5) {
                System.out.println("\tEL GANADOR DE LA PARTIDA ES " + this.jugador1.getNombre());
                bucle = false;
            }
            if (this.jugador1.getEjercito().getUnidades().isEmpty() && this.jugador2.getEjercito().getUnidades().isEmpty() && this.turnos > 5) {
                System.out.println("\tEMPATE");
                bucle = false;
            }

        } while (bucle);
    }

    /**
     * Override de toString que devuelve un String con la informacion de la
     * partida, que jugadores juegan y en que escenario
     */
    @Override
    public String toString() {
        return "La partida comienza, lucharan " + this.jugador1.getNombre() + " contra " + this.jugador2.getNombre() + " en el escenario " + this.escenario.getNombre() + "\n"
                + "\nEmpezara en el primer turno " + this.jugador1.getNombre() + "\n";
    }

}
