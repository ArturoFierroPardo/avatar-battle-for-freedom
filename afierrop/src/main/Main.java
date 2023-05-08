/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import interfaz.menu.Menu;
import modelos.coordenadas.Coordenadas;
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
     */
    public static void main(String[] args) throws FueradeMapaException, PuntosdeCompraInsuficientesException {

        /**
         * Main para comprobar que funcionan cosas manualmente
         */
        Escenario escenario = new ArbolMadre();
        Coordenadas coordenada = new Coordenadas(escenario);
        Jugador jugador = new Jugador("Arturo", Equipo.RDA, escenario);

        Menu menu = new Menu(jugador, escenario);
        menu.enseñarMenu();

    }

}
