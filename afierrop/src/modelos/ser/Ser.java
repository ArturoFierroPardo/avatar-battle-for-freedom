/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.ser;

/**
 *
 * @author artur
 */
public abstract class Ser {

    /**
     * Cuanto cuesta el ser
     */
    private int coste;

    /**
     * Tipo de cargo
     */
    private TipoCargo cargo;

    /**
     * Tipo de raza
     */
    private TipoRaza raza;

    /**
     * Cuanto ataque tiene el ser
     */
    private int vida;

    /**
     * Puntos que se recibe al derrotarlo
     */
    private int puntosderrota;

    /**
     * Nombre del ser
     */
    private String nombre;

    /**
     * Constructor de Ser.Recibe por parametros todo sus atributos que son
     * especificados por sus hijos los cuales heredan este constructor.Datos sin
     * especificar:
     *
     * @param nombre
     * @param coste
     * @param cargo
     * @param raza
     * @param vida
     * @param puntosderrota
     */
    public Ser(String nombre, int coste, TipoCargo cargo, TipoRaza raza, int vida, int puntosderrota) {
        this.nombre = nombre;
        this.coste = coste;
        this.cargo = cargo;
        this.raza = raza;
        this.vida = vida;
        this.puntosderrota = puntosderrota;
    }

    /**
     * Setters
     *
     * @param vida
     */
    public void setVida(int vida) {
        this.vida = vida;
    }

    /**
     * Getters
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    public int getCoste() {
        return this.coste;
    }

    public TipoCargo getCargo() {
        return this.cargo;
    }

    public TipoRaza getRaza() {
        return this.raza;
    }

    public int getVida() {
        return this.vida;
    }

    public int getPuntosderrota() {
        return this.puntosderrota;
    }

    /**
     * Override de toString, al cual acceden todos sus hijos cada uno con sus
     * datos gracias a la herencia de clases.
     */
    @Override
    public String toString() {
        return "Nombre: " + this.nombre;
    }

    /**
     * Funcion que imprime toda la informacion del ser
     */
    public String toStringCompleto() {
        return "Nombre: " + this.nombre + "\n"
                + "Coste: " + this.coste + "\n"
                + "Cargo: " + this.cargo + "\n"
                + "Raza: " + this.raza + "\n"
                + "Vida: " + this.vida + "\n"
                + "Puntos: " + this.puntosderrota + " \n";

    }
}
