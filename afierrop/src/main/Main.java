/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import interfaz.partida.Partida;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolAlmas;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.escenario.Jardines;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaMovimientoException;
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
     */
    public static void main(String[] args) throws FueradeMapaException, PuntosdeCompraInsuficientesException, SinEnergiaMovimientoException {
        /**
         * Main para empezar el juego. Además selecciona un escenario el cual se
         * le pide al usuario.
         */

        System.out.println("\tAVATAR BATTLE FOR FREEDOM\n");
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
        /**
         * HACER EXCEPCION DE ENERGIA DE ATAQUE Y FUNCION RESTARLA, ENERGIA DE
         * DEFENSA, VIDA. PODER FINALIZAR TURNO SI CATCHEA EXCEPCION. Y PONER
         * MAS EXCEPCIONES EN MENU POR SI QUIERE HACER COSAS QUE NO PUEDE.
         * CONDICION DE GANADOR. NO ATACAR SI NO TIENES UNIDADES
         */

    }

}
