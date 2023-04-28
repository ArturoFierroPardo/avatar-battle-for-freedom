/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.configuraciones;

/**
 *
 * @author artur
 */
public abstract class Escenario {

    private int puntoscomienzo;
    protected int xmax;
    protected int ymax;

    public Escenario(int puntoscomienzo, int xmax, int ymax) {
        this.puntoscomienzo = puntoscomienzo;
        this.xmax = xmax;
        this.ymax = ymax;
    }

    public int getXmax() {
        return this.xmax;
    }

    public int getYmax() {
        return this.ymax;
    }
}
