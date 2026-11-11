/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.excepciones;

/**
 *
 * @author artur
 */
public class PuntosdeCompraInsuficientesException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Excepcion que se lanza en la clase jugador al intentar comprar una unidad
     * sin tener puntos suficientes. En el menu se hara otra excpecion la cual
     * hara que se repita en bucle.
     */
}
