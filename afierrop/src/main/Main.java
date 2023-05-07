/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import modelos.coordenadas.Coordenadas;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.jugador.Jugador;
import modelos.ser.Ser;
import modelos.ser.humano.Artillero;
import modelos.ser.humano.Comandante;
import modelos.unidad.Unidad;
import modelos.unidad.bestia.Banshee;
import modelos.unidad.maquina.AT99;

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
        Unidad at99 = new AT99();
        Unidad banshee = new Banshee();
        Ser artillero = new Artillero();
        Ser comandante = new Comandante();
        Coordenadas coordenada = new Coordenadas(escenario);
        Jugador jugador = new Jugador("Arturo", Equipo.RDA, escenario);

        jugador.comprarUnidad(at99);
        jugador.comprarSeres(at99, artillero);

        System.out.println(jugador.getEjercito());

    }

}
