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
public class Artillero extends Humano {

    public Artillero() {
        super();
        this.coste = 50;
        this.cargo = TipoCargo.GUERRERO;
        this.raza = TipoRaza.HUMANO;
        this.vida = 150;
        this.puntos = 100;

    }
}
