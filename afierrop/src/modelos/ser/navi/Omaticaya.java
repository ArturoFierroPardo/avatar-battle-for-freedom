/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser.navi;

import modelos.ser.TipoCargo;
import modelos.ser.TipoRaza;

/**
 *
 * @author artur
 */
public class Omaticaya extends Navi {

    public Omaticaya() {
        super();
        this.coste = 100;
        this.cargo = TipoCargo.OFICIAL;
        this.raza = TipoRaza.NAVI;
        this.vida = 300;
        this.puntos = 400;

    }
}
