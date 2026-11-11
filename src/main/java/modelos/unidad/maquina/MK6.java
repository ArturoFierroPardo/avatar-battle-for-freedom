/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.maquina;

import java.util.ArrayList;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.ser.Ser;

/**
 *
 * @author artur
 */
public class MK6 extends Maquina {

    /**
     * Constructor de MK6. Hereda de Maquina y luego de Unidad. MK6 asigna sus
     * atributos unicos (nombre, coste, energias, potencia, puntos, tripulantes,
     * posicion, energia gastada al moverse, maximo movimiento, rango de ataque
     * y seres necesarios para el funcionamiento), Unidad (el padre de todos)
     * asigna a todos los valores como los que se le pasa por parametro, que son
     * los que se le pasa mediante sus hijos. Posicion y tripulantes se asignan
     * con setters al comprar la unidad
     */
    public MK6() {
        super("Plataforma de Movilidad Amplificada Mitsubishi MK-6", null, 500, 0, 1000, 500, 20, 50, new ArrayList<Ser>(), null, 25, "2", 1, 1);
    }

    /**
     * Override de la funcion restar energia.Esta resta energia de diferente
     * manera a la general.
     *
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     */
    @Override
    public void restarEnergia() throws SinEnergiaMovimientoException {
        if (this.getEnergiamov() >= this.getEnergiagastadaxmov() * (this.getPosicion().getCasillasMovidasX() + this.getPosicion().getCasillasMovidasY())) {
            this.setEnergiamov(this.getEnergiamov() - this.getEnergiagastadaxmov());
        } else {
            throw new SinEnergiaMovimientoException();
        }
    }

}
