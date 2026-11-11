/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.excepciones;

/**
 *
 * @author artur
 */
public class SinEnergiaDefensaException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Excepcion para poder comprobar que la unidad tiene energia de defensa
     * suficiente para recibir daño, se lanza en recibir daño y atacar unidad.
     * Si salta, con un bucle se comprueba y los tripulantes de la unidad
     * recibiran el daño, si no tiene energia de defensa, ni tripulantes, la
     * unidad se quita del ejercito.
     */
}
