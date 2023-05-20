/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.partida;

import interfaz.centrodemandos.CentroDeMandos;
import interfaz.menu.Menu;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Objects;
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
     * Centro de mandos del jugador 1
     */
    private CentroDeMandos cm1;
    /**
     * Centro de mandos del jugador 2
     */
    private CentroDeMandos cm2;

    /**
     * Dicta si la partida esta terminada con un true y false si no
     */
    private boolean terminada;

    /**
     * Constructor de partida en el que se pasa por parametros dos jugadores que
     * jugaran uno contra el otro y un escenario en el que jugar.
     *
     * @param jugador1
     * @param jugador2
     * @param escenario
     * @param cm1
     * @param cm2
     */
    public Partida(Jugador jugador1, Jugador jugador2, Escenario escenario, CentroDeMandos cm1, CentroDeMandos cm2) {
        this.jugador1 = jugador1;
        this.jugador2 = jugador2;
        this.escenario = escenario;
        this.turnos = 1;
        this.terminada = false;
        this.cm1 = cm1;
        this.cm2 = cm2;
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

    public boolean getTerminada() {
        return this.terminada;
    }

    public Escenario getEscenario() {
        return this.escenario;
    }

    /**
     * Funcion que inicia la partida.Crea un menu que recibe por parametro el
     * jugador que juega en funcion de una funcion de turnos y un
     * escenario.Enseña el menu al jugador 1 o 2 y cuando acabe se añade un
     * turno más, así, se pasa gracias a un contador los turnos.Gracias a un
     * bucle dowhile se va repitiendo hasta que alguien gane.La condicion para
     * ganar una partida es que el enemigo no tenga unidades y que hayan pasado
     * 5 turnos como maximo. Si uno de los dos jugadores decide guardar partida
     * y acabar se cierra y se guarda en ses mimso turno.
     *
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     * @throws java.io.FileNotFoundException
     */
    public void comienzaPartida() throws PuntosdeCompraInsuficientesException, FueradeMapaException, SinEnergiaMovimientoException, SinEnergiaAtaqueException, SinTripulantesSuficientesException, FileNotFoundException {
        System.out.println(toString());
        boolean bucle = true;
        do {
            if (this.turnos % 2 == 1) {
                System.out.println("TURNO---------->  " + this.turnos);
                Menu menu = new Menu(this.jugador1, this.escenario, this.cm1, this.cm2);
                menu.enseñarMenu();
                this.turnos++;
            }
            if (this.jugador1.getAcabarPartida() == true) {
                this.jugador1.setAcabarPartida(false);
                this.jugador2.setAcabarPartida(false);
                this.turnos--;
                System.out.println("LA PARTIDA SE HA GUARDADO Y FINALIZADO");
                bucle = false;
                break;
            }
            if (this.turnos % 2 == 0) {
                System.out.println("TURNO---------->  " + this.turnos);
                Menu menu = new Menu(this.jugador2, this.escenario, this.cm2, this.cm1);
                menu.enseñarMenu();
                this.turnos++;
            }
            if (this.jugador2.getAcabarPartida() == true) {
                this.jugador1.setAcabarPartida(false);
                this.jugador2.setAcabarPartida(false);
                this.turnos--;
                System.out.println("LA PARTIDA SE HA GUARDADO Y FINALIZADO");
                bucle = false;
                break;
            }
            if (this.jugador1.getEjercito().getUnidades().isEmpty() && !(this.jugador2.getEjercito().getUnidades().isEmpty()) && this.turnos >= 5) {
                this.terminada = true;
                System.out.println("\tEL GANADOR DE LA PARTIDA ES " + this.jugador2.getNombre());
                bucle = false;
                break;
            }
            if (this.jugador2.getEjercito().getUnidades().isEmpty() && !(this.jugador1.getEjercito().getUnidades().isEmpty()) && this.turnos >= 5) {
                this.terminada = true;
                System.out.println("\tEL GANADOR DE LA PARTIDA ES " + this.jugador1.getNombre());
                bucle = false;
                break;
            }
            if (this.jugador1.getEjercito().getUnidades().isEmpty() && this.jugador2.getEjercito().getUnidades().isEmpty() && this.turnos >= 5) {
                this.terminada = true;
                System.out.println("\tEMPATE");
                bucle = false;
                break;
            }

        } while (bucle);
    }

    /**
     * Override de toString que devuelve un String con la informacion de la
     * partida, que jugadores juegan y en que escenario
     */
    @Override
    public String toString() {
        return "\nLa partida comienza en el escenario " + this.escenario.getNombre() + ".\nLuchara " + this.jugador1.getNombre() + " del equipo " + this.jugador1.getEquipo() + " contra " + this.jugador2.getNombre() + " del equipo " + this.jugador2.getEquipo() + "\n";
    }

    /**
     * Override de la funcion equals para comparar que dos unidades son iguales
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Partida) {
            Partida p = (Partida) o;
            return (this.jugador1 == p.jugador1 && this.jugador2 == p.jugador2 && this.turnos == p.turnos && this.escenario == p.escenario);
        } else {
            return false;
        }
    }

    /**
     * Override del hashcode para esta clase
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.jugador1);
        hash = 53 * hash + Objects.hashCode(this.jugador2);
        hash = 53 * hash + Objects.hashCode(this.escenario);
        hash = 53 * hash + this.turnos;
        hash = 53 * hash + (this.terminada ? 1 : 0);
        return hash;
    }

}
