/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.jugador;

import java.io.Serializable;
import modelos.coordenadas.Coordenadas;
import modelos.ejercito.Ejercito;
import modelos.equipo.Equipo;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.ser.Ser;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public class Jugador implements Serializable {

    /**
     * Nombre del jugador
     */
    private String nombre;

    /**
     * Tipo de equipo del jugador
     */
    private Equipo equipo;

    /**
     * Cuantos puntos tiene el jugador
     */
    private int puntos;

    /**
     * El ejercito del jugador
     */
    private Ejercito ejercito;

    /**
     * Tipo de escenario en el que juega el jugador
     */
    private Escenario escenario;

    /**
     * Atributo para acabar la partida
     */
    private boolean acabarpartida;

    /**
     * Constructor de un jugador el cual recibira por parametros, el nombre de
     * jugador, su equipo (Navi o RDA) y el escenario en el que va a
     * jugar.Además, cuenta con un atributo ejercito que se instancia en null,
     * ya que se irán añadiendo según compre unidades.El atributo puntos, se
     * inicializa con los puntoscomienzo que da el escenario.
     *
     * @param nombre
     * @param equipo
     * @param escenario
     */
    public Jugador(String nombre, Equipo equipo, Escenario escenario) {
        this.nombre = nombre;
        this.equipo = equipo;
        this.puntos = escenario.getPuntoscomienzo();
        this.ejercito = new Ejercito();
        this.escenario = escenario;
        this.acabarpartida = false;
    }

    /**
     * Setters
     *
     * @param ejercito
     * @param puntos
     * @param acabarpartida
     */
    public void setEjercito(Ejercito ejercito) {
        this.ejercito = ejercito;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public void setAcabarPartida(boolean acabarpartida) {
        this.acabarpartida = acabarpartida;
    }

    /**
     * Getters
     *
     * @return
     */
    public String getNombre() {
        return this.nombre;
    }

    public Equipo getEquipo() {
        return this.equipo;
    }

    public int getPuntos() {
        return this.puntos;
    }

    public Ejercito getEjercito() {
        return this.ejercito;
    }

    public boolean getAcabarPartida() {
        return this.acabarpartida;
    }

    /**
     * Función de compra de Unidades.Si el jugador tiene los puntos necesarios
     * para la compra de la unidad, se compra y se restan los puntos gastados.Si
     * no, se lanza la excepcion que sera catcheada en el menu para repetirlo
     * con un bucle.Al comprar la unidad se añade al mapa de unidades viendo si
     * hay o no una unidad ya.
     *
     * @param unidad
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void comprarUnidad(Unidad unidad) throws PuntosdeCompraInsuficientesException, FueradeMapaException {
        boolean coordenadacorrecta = true;
        Coordenadas posicion;
        if (this.puntos >= unidad.getCoste()) {
            while (coordenadacorrecta) {
                posicion = new Coordenadas(this.escenario);
                if (this.escenario.checkMapaAñadir(unidad, posicion) == false) {
                    unidad.setPosicion(posicion);
                    this.escenario.checkMapaAñadir(unidad, posicion);
                    this.ejercito.getUnidades().add(unidad);
                    this.puntos = this.puntos - unidad.getCoste();
                    break;
                } else {
                    posicion = new Coordenadas(this.escenario);
                    coordenadacorrecta = true;
                }
            }
        } else {
            throw new PuntosdeCompraInsuficientesException();
        }
    }

    /**
     * Función de compra de seres. Si el jugador tiene los puntos necesarios
     * para la compra de el ser, se compra y se restan los puntos gastados.Si
     * no, se lanza la excepcion que sera catcheada en el menu para repetirlo
     * con un bucle.
     *
     * @param unidad
     * @param ser
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     */
    public void comprarSeres(Unidad unidad, Ser ser) throws PuntosdeCompraInsuficientesException {
        if (this.puntos >= ser.getCoste()) {
            unidad.getTripulantes().add(ser);
            this.puntos = this.puntos - ser.getCoste();
        } else {
            throw new PuntosdeCompraInsuficientesException();
        }
    }

    /**
     * Funcion moverunidad, que llama a la funcion moverunidad de unidad ya que
     * es distinta para algunas unidades y no general. Pide la unidad que hay
     * que mover por parametro
     *
     * @param unidad
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void moverUnidad(Unidad unidad) throws FueradeMapaException {
        unidad.moverUnidad(this.escenario);
    }

    /**
     * Funcion para restar energia de movimiento a una unidad en funcion de sus
     * limitaciones, llamando a la funcion restar energia de unidad.
     *
     * @param unidad
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     */
    public void restarEnergia(Unidad unidad) throws SinEnergiaMovimientoException {
        unidad.restarEnergia();
    }

    /**
     * Funcion atacar unidad, que llama a la funcion atacarunidad de unidad ya
     * que es distinta para algunas unidades y no general. Pide por parametro
     * una unidad que es la que va a atacar.
     *
     * @param unidad
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     */
    public void atacarUnidad(Unidad unidad) throws FueradeMapaException, SinEnergiaAtaqueException {
        unidad.atacarUnidad(this, this.escenario);
    }

    /**
     * Funcion para restar energia de ataque en funcion de cada tipo de unidad,
     * llamando a la funcion restarenergiataque en unidad y comprueba las
     * excepciones
     *
     * @param unidad
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     */
    public void restarEnergiaAtaque(Unidad unidad) throws SinEnergiaAtaqueException {
        unidad.restarEnergiaAtaque();
    }

    /**
     * Funcion para recibir daño restando las energias de defensa de una unidad
     * y la de los seres. Se castea en unidad. Pide por parametro la unidad que
     * ataca y la posicion en donde esta la unidad a la que se ataca
     *
     * @param unidad
     * @param posicion
     * @throws modelos.excepciones.FueradeMapaException
     */
    public String recibirdaño(Unidad unidad, Coordenadas posicion) throws FueradeMapaException {
        if (unidad.getPotencia() <= this.escenario.getunidadMapa(posicion).getEnergiadefensa()) {
            this.escenario.getunidadMapa(posicion).setEnergiadefensa(this.escenario.getunidadMapa(posicion).getEnergiadefensa() - unidad.getPotencia());
        } else {
            this.escenario.getunidadMapa(posicion).setEnergiadefensa(0);
            int dañoigual = (unidad.getPotencia() - this.escenario.getunidadMapa(posicion).getEnergiadefensa()) / this.escenario.getunidadMapa(posicion).getTripulantes().size();
            for (Ser ser : this.escenario.getunidadMapa(posicion).getTripulantes()) {
                ser.setVida(ser.getVida() - dañoigual);
                if (ser.getVida() <= 0) {
                    this.escenario.getunidadMapa(posicion).getTripulantes().remove(ser);
                    this.puntos = this.puntos + ser.getPuntosderrota();
                }
            }
            if (this.escenario.getunidadMapa(posicion).getTripulantes().isEmpty() && this.escenario.getunidadMapa(posicion).getEnergiadefensa() <= 0) {
                this.puntos = this.puntos + this.escenario.getunidadMapa(posicion).getPuntosderrota();
                this.ejercito.getUnidades().remove(this.escenario.getunidadMapa(posicion));
                this.escenario.getMapa()[this.escenario.getunidadMapa(posicion).getPosicion().getX()][this.escenario.getunidadMapa(posicion).getPosicion().getY()] = null;
            }
        }
        return "La unidad " + this.escenario.getunidadMapa(posicion) + " ha sido dañada. Su defensa ha sido reducida a " + this.escenario.getunidadMapa(posicion).getEnergiadefensa();
    }

    /**
     * Función toString que devuelve un string con el nombre, el equipo y los
     * puntos del jugador
     */
    @Override
    public String toString() {
        return "El jugador " + this.nombre + " es del equipo " + this.equipo + " y consta de " + this.puntos + " puntos";
    }

}
