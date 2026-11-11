/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.bestia;

import java.util.ArrayList;
import modelos.coordenadas.Coordenadas;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.ser.Ser;

/**
 *
 * @author artur
 */
public class Banshee extends Bestia {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor de Banshee. Hereda de Bestia y luego de Unidad. Banshee
     * asigna sus atributos unicos (nombre, coste, energias, potencia, puntos,
     * tripulantes, posicion, energia gastada al moverse, maximo movimiento,
     * rango de ataque y seres necesarios para el funcionamiento), Unidad (el
     * padre de todos) asigna a todos los valores como los que se le pasa por
     * parametro, que son los que se le pasa mediante sus hijos. Posicion y
     * tripulantes se asignan con setters al comprar la unidad
     */
    public Banshee() {
        super("Banshee de Montaña", null, 2000, 3500, 8000, 3500, 300, 1000, new ArrayList<Ser>(), null, 10, MOVIMIENTO_ALEATORIO, 10, 2);
    }

    /**
     * Override de la funcion restar energia. Esta resta energia de diferente
     * manera a la general, para banshee.
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

    /**
     * Override de la funcion moverunidad.Esta mueve la unidad banshee de
     * diferente manera a la general.
     *
     * @param escenario
     */
    @Override
    public void moverUnidad(Escenario escenario) {
        boolean coordenadacorrecta = true;
        boolean bucleexception = true;
        while (bucleexception) {
            try {
                Coordenadas coordenada;
                while (coordenadacorrecta) {
                    coordenada = new Coordenadas(escenario);
                    if (escenario.colocarUnidadSiLibre(this, coordenada)) {
                        escenario.getMapa()[this.getPosicion().getX()][this.getPosicion().getY()] = null;
                        this.setPosicion(coordenada);
                        escenario.colocarUnidadSiLibre(this, coordenada);
                        coordenadacorrecta = false;
                    } else {
                        coordenada = new Coordenadas(escenario);
                        coordenadacorrecta = true;
                    }
                }
                break;
            } catch (FueradeMapaException fme) {
            }
        }
    }

}
