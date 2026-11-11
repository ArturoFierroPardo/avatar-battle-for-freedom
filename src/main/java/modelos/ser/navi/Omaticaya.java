package modelos.ser.navi;

import modelos.ser.TipoCargo;

/**
 * Guerrero del clan Omaticaya: el tripulante navi de cargo oficial.
 *
 * @author Arturo Fierro Pardo
 */
public class Omaticaya extends Navi {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Omaticaya. Hereda de navi y luego de ser. Omaticaya asigna
     * sus atributos unicos (nombre, coste, tipo de cargo, vida y puntos), navi
     * asigna su atributo unico que es la raza navi y los demás los deja, y Ser
     * (el padre de todos) asigna a todos los valores como los que se le pasa
     * por parametro, que son los que se le pasa mediante sus hijos.
     */
    public Omaticaya() {
        super("Omaticaya", 100, TipoCargo.OFICIAL, 300, 400);

    }
}
