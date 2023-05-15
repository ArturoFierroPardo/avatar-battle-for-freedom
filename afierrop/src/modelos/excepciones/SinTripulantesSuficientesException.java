/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.excepciones;

/**
 *
 * @author artur
 */
public class SinTripulantesSuficientesException extends Exception {
    /**
     * Excepcion para poder comprobar que la unidad tiene tripulantes suficiente
     * en funcion de los que necesita, para realizar un ataque o moverse, se
     * lanza moverunidad y atacar unidad. Si salta, con un bucle se comprueba y
     * se va repitiendo hasta que pueda. Si no se puede con nada, como en todas
     * las funciones se puede cancelar el ataque o el movimiento para no estar
     * en bucle.
     */

}
