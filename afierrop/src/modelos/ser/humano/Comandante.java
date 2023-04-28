/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser.humano;

import modelos.ser.TipoCargo;
import modelos.ser.TipoRaza;

/**
 *
 * @author artur
 */
public class Comandante extends Humano {

    public Comandante() {
        super();
        this.coste = 100;
        this.cargo = TipoCargo.OFICIAL;
        this.raza = TipoRaza.HUMANO;
        this.vida = 150;
        this.puntos = 400;
    }

}
