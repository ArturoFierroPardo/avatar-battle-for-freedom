/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import interfaz.partida.Partida;
import static io.Io.cargarPartida;
import java.io.FileNotFoundException;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolAlmas;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.escenario.Jardines;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.excepciones.SinTripulantesSuficientesException;
import modelos.jugador.Jugador;
import utils.Utils;

/**
 *
 * @author artur
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws FueradeMapaException, PuntosdeCompraInsuficientesException, SinEnergiaMovimientoException, SinEnergiaAtaqueException, SinTripulantesSuficientesException, FileNotFoundException, ClassNotFoundException {
        /**
         * Main para empezar el juego. Además selecciona un escenario el cual se
         * le pide al usuario. Y si quiere empezar una partida nueva o cargar la
         * anterior.
         */
        boolean buclepart = true;
        System.out.println("\tAVATAR BATTLE FOR FREEDOM\n");
        System.out.println("\n-------------------SELECCION DE PARTIDA-------------------\n");
        System.out.println("1) Cargar Partida Anterior\n");
        System.out.println("2) Nueva Partida\n");
        do {
            int eleccionpartida = Utils.leerEntero("Quieres cargar la  partida anterior o empezar una nueva?");
            switch (eleccionpartida) {
                case 1:
                    cargarPartida().comienzaPartida();
                    buclepart = false;
                    break;
                case 2:
                    Escenario escenario = null;
                    boolean bucle = true;
                    Escenario arbolalmas = new ArbolAlmas();
                    Escenario jardines = new Jardines();
                    Escenario arbolmadre = new ArbolMadre();
                    System.out.println("\n-------------------ESCENARIOS-------------------\n");
                    System.out.println("1) Arbol de las Almas:\n" + arbolalmas.toString());
                    System.out.println("2) Jardines Colgantes:\n" + jardines.toString());
                    System.out.println("3) Arbol Madre:\n" + arbolmadre.toString());
                    do {
                        int eleccionesc = Utils.leerEntero("Elija en que escenario quiere disputar la batalla introduciendo el numero correspondiente ");
                        switch (eleccionesc) {
                            case 1:
                                escenario = arbolalmas;
                                bucle = false;
                                break;
                            case 2:
                                escenario = jardines;
                                bucle = false;
                                break;
                            case 3:
                                escenario = arbolmadre;
                                bucle = false;
                                break;
                            default:
                                System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                bucle = true;
                                break;
                        }
                    } while (bucle);

                    Jugador jugador1 = new Jugador("Arturo", Equipo.RDA, escenario);
                    Jugador jugador2 = new Jugador("Pedro", Equipo.Navi, escenario);
                    Partida partida = new Partida(jugador1, jugador2, escenario);
                    partida.comienzaPartida();
                    buclepart = false;
                    break;
                default:
                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                    buclepart = true;
                    break;

            }
        } while (buclepart);
    }

}
