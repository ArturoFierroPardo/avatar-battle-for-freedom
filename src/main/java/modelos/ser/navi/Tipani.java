package modelos.ser.navi;

import modelos.ser.TipoCargo;

/**
 * Guerrero del clan Tipani: el tripulante navi de cargo guerrero.
 *
 * @author Arturo Fierro Pardo
 */
public class Tipani extends Navi {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Tipani. Hereda de navi y luego de ser. Omaticaya asigna
     * sus atributos unicos (nombre, coste, tipo de cargo, vida y puntos), navi
     * asigna su atributo unico que es la raza navi y los demás los deja, y Ser
     * (el padre de todos) asigna a todos los valores como los que se le pasa
     * por parametro, que son los que se le pasa mediante sus hijos.
     */
    public Tipani() {
        super("Tipani", 50, TipoCargo.GUERRERO, 300, 250);

    }
}
