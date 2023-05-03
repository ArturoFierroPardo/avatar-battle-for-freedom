/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacion;

import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.jugador.Jugador;
import modelos.ser.Ser;
import modelos.ser.humano.Artillero;
import modelos.unidad.Unidad;
import modelos.unidad.bestia.Banshee;

/**
 *
 * @author artur
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Escenario escenario = new ArbolMadre();
        Unidad banshee = new Banshee();
        Ser artillero = new Artillero();
        Jugador jugador = new Jugador("Arturo", Equipo.RDA, escenario);

        System.out.println(jugador.toString());

    }

}
