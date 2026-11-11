/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ejercito;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public class Ejercito implements Serializable {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

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

    /**
     * Compara dos ejercitos por las unidades que los componen.
     *
     * <p>
     * La version anterior comparaba las listas con {@code ==}, es decir
     * comprobaba si ambos ejercitos compartian el mismo objeto lista, de modo
     * que dos ejercitos con las mismas unidades nunca resultaban iguales.
     *
     * @param o objeto con el que comparar
     * @return {@code true} si ambos ejercitos tienen las mismas unidades en el
     * mismo orden
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Ejercito) {
            Ejercito e = (Ejercito) o;
            return Objects.equals(this.unidades, e.unidades);
        } else {
            return false;
        }
    }

    /**
     * Devuelve un codigo hash coherente con {@link #equals(Object)}.
     *
     * @return el codigo hash del ejercito
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.unidades);
    }

}
