/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacion;

import modelos.coordenadas.Coordenadas;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.jugador.Jugador;
import modelos.ser.Ser;
import modelos.ser.humano.Artillero;
import modelos.unidad.Unidad;
import modelos.unidad.maquina.AT99;

/**
 *
 * @author artur
 */
public class Aplicacion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FueradeMapaException {
        Escenario escenario = new ArbolMadre();
        Unidad at99 = new AT99();
        Ser artillero = new Artillero();
        Coordenadas coordenada = new Coordenadas(299, 299, escenario);
        Jugador jugador = new Jugador("Arturo", Equipo.RDA, escenario);

        System.out.println(at99.toString());

    }

}
