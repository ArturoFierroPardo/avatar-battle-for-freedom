/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ejercito;

import java.util.ArrayList;
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
     * Constructor de Ejercito, el cual, tiene de atributos un arraylist de
     * unidade, el cuale, esta instaciado como un ArrayList vacio, porque, no se
     * añadiran hasta que se compren.
     */
    public Ejercito() {
        this.unidades = new ArrayList<>();
    }

    /**
     * Setters
     *
     * @param unidades
     */
    public void setUnidades(ArrayList<Unidad> unidades) {
        this.unidades = unidades;
    }

    /**
     * Getters
     *
     * @return
     */
    public ArrayList<Unidad> getUnidades() {
        return this.unidades;
    }

    /**
     * Override de toString que devuelve un String con un ArrayList de las
     * unidades.
     */
    @Override
    public String toString() {
        return "\nSu ejercito consta de " + this.unidades;
    }

}
