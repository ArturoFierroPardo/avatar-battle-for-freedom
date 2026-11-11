package modelos.ser.humano;

import modelos.ser.TipoCargo;

/**
 * Comandante de SecOps: el tripulante humano de cargo oficial, mas caro y
 * que da mas puntos al enemigo que lo derrota.
 *
 * @author Arturo Fierro Pardo
 */
public class Comandante extends Humano {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Comandante. Hereda de humano y luego de ser. Comandante
     * asigna sus atributos unicos (nombre, coste, tipo de cargo, vida y
     * puntos), Humano asigna su atributo unico que es la raza humana y los
     * demás los deja, y Ser (el padre de todos) asigna a todos los valores como
     * los que se le pasa por parametro, que son los que se le pasa mediante sus
     * hijos.
     */
    public Comandante() {
        super("Comandante de SecOps", 100, TipoCargo.OFICIAL, 150, 400);
    }

}
