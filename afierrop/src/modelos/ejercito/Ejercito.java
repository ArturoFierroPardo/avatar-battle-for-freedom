/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ejercito;

import java.util.ArrayList;
import modelos.ser.Ser;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public class Ejercito {

    /**
     * Lista de las unidades que tiene el ejercito
     */
    private ArrayList<Unidad> unidades;

    /**
     * Lista de seres que tiene el ejercito
     */
    private ArrayList<Ser> seres;

    /**
     * Constructor de Ejercito, el cual, tiene de atributos dos arraylists de
     * unidades y seres, los cuales, estan instaciados en null, porque, no se
     * añadiran hasta que se compren.
     */
    public Ejercito() {
        this.unidades = null;
        this.seres = null;
    }

    /**
     * Setters
     *
     * @param unidades
     * @param seres
     */
    public void setUnidades(ArrayList<Unidad> unidades) {
        this.unidades = unidades;
    }

    public void setSeres(ArrayList<Ser> seres) {
        this.seres = seres;
    }

    /**
     * Getters
     *
     * @return
     */
    public ArrayList<Unidad> getUnidades() {
        return this.unidades;
    }

    public ArrayList<Ser> getSeres() {
        return this.seres;
    }

    /**
     * Función toString que devuelve un String con el ejercito de unidades y el
     * numeros de seres que los pilotan
     */
    @Override
    public String toString() {
        return "Su ejercito consta de " + this.unidades + " pilotados por " + this.seres;
    }

}
