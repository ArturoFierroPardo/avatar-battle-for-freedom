/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.excepciones;

/**
 *
 * @author artur
 */
public class SinEnergiaMovimientoException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Excepcion para poder comprobar que la unidad tiene energia de movimiento
     * suficiente para moverse, se lanza en mover unidad y el menu. Si salta,
     * con un bucle se comprueba y se va repitiendo hasta que pueda. Si no se
     * puede con nada, como en todas las funciones se puede cancelar el ataque
     * para no estar en bucle.
     */

}
