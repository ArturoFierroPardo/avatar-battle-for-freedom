package modelos.ser;

import java.io.Serializable;
import java.util.Objects;

/**
 * Ser vivo que puede tripular una unidad. Aporta su vida al aguante de la
 * unidad y unos puntos al enemigo que lo derrota.
 *
 * @author Arturo Fierro Pardo
 */
public abstract class Ser implements Serializable {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

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

    /**
     *
     * @return
     */
    public int getCoste() {
        return this.coste;
    }

    /**
     *
     * @return
     */
    public TipoCargo getCargo() {
        return this.cargo;
    }

    /**
     *
     * @return
     */
    public TipoRaza getRaza() {
        return this.raza;
    }

    /**
     *
     * @return
     */
    public int getVida() {
        return this.vida;
    }

    /**
     *
     * @return
     */
    public int getPuntosderrota() {
        return this.puntosderrota;
    }

    /**
     * Funcion que imprime toda la informacion del ser
     *
     * @return
     */
    public String toStringInfo() {
        return "\tNombre: " + this.nombre + "\n"
                + "\tCoste: " + this.coste + "\n"
                + "\tCargo: " + this.cargo + "\n"
                + "\tRaza: " + this.raza + "\n"
                + "\tVida: " + this.vida + "\n"
                + "\tPuntos: " + this.puntosderrota + " \n";

    }

    /**
     * Override de la funcion equals para comparar que dos unidades son iguales
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Ser) {
            Ser s = (Ser) o;
            return (this.nombre.equals(s.nombre) && this.cargo == s.cargo && this.raza == s.raza && this.vida == s.vida);
        } else {
            return false;
        }
    }

    /**
     * Override del hashcode para esta clase
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.coste;
        hash = 71 * hash + Objects.hashCode(this.cargo);
        hash = 71 * hash + Objects.hashCode(this.raza);
        hash = 71 * hash + this.vida;
        hash = 71 * hash + this.puntosderrota;
        hash = 71 * hash + Objects.hashCode(this.nombre);
        return hash;
    }

    /**
     * Override de toString, el cual imprime solo el nombre.
     */
    @Override
    public String toString() {
        return this.nombre;
    }

}
