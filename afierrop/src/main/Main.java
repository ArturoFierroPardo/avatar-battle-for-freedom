/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import interfaz.partida.Partida;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.jugador.Jugador;

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
    public static void main(String[] args) throws FueradeMapaException, PuntosdeCompraInsuficientesException {

        /**
         * Main para comprobar que funcionan cosas manualmente
         */
        Escenario escenario = new ArbolMadre();
        Jugador jugador1 = new Jugador("Arturo", Equipo.RDA, escenario);
        Jugador jugador2 = new Jugador("Pedro", Equipo.Navi, escenario);
        Partida partida = new Partida(jugador1, jugador2, escenario);
        partida.comienzaPartida();

    }

}
