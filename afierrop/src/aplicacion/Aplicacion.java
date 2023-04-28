/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aplicacion;

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
        Unidad banshee = new Banshee();
        Ser artillero = new Artillero();
        System.out.println(banshee.toString());
        System.out.println(artillero.toString());
    }

}
