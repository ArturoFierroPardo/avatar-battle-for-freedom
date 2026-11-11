package modelos.ser.humano;

import modelos.ser.TipoCargo;

/**
 * Artillero de la RDA: el tripulante humano barato, de cargo guerrero.
 *
 * @author Arturo Fierro Pardo
 */
public class Artillero extends Humano {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Artillero. Hereda de humano y luego de ser. Artillero
     * asigna sus atributos unicos (nombre, coste, tipo de cargo, vida y
     * puntos), Humano asigna su atributo unico que es la raza humana y los
     * demás los deja, y Ser (el padre de todos) asigna a todos los valores como
     * los que se le pasa por parametro, que son los que se le pasa mediante sus
     * hijos.
     */
    public Artillero() {
        super("Artillero", 50, TipoCargo.GUERRERO, 150, 100);
    }
}
