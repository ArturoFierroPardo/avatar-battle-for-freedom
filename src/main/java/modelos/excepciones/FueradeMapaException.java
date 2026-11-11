/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.excepciones;

/**
 *
 * @author artur
 */
public class FueradeMapaException extends Exception {

    /**
     * Excepcion para las coordenadas que salta si se salen del mapa. Se chequea
     * en coordenadas, try catch en el metodo moverse de jugador que lanzara una
     * excepcion nueva o la misma que se catcheara y se repetira en bucle en el
     * menu del jugador con un while(true) try catch().
     */
}
