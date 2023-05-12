/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.jugador;

import modelos.coordenadas.Coordenadas;
import modelos.ejercito.Ejercito;
import modelos.equipo.Equipo;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.ser.Ser;
import modelos.unidad.Unidad;
import modelos.unidad.bestia.Banshee;
import modelos.unidad.bestia.Thanator;
import modelos.unidad.bestia.Titanothere;
import modelos.unidad.maquina.AT99;
import modelos.unidad.maquina.Aerospatiale;
import modelos.unidad.maquina.MK6;

/**
 *
 * @author artur
 */
public class Jugador {

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
    }

    /**
     * Setters
     *
     * @param ejercito
     * @param puntos
     */
    public void setEjercito(Ejercito ejercito) {
        this.ejercito = ejercito;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
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
     * Función de compra de seres.Si el jugador tiene los puntos necesarios para
     * la compra de el ser, se compra y se restan los puntos gastados.Si no, se
     * lanza la excepcion que sera catcheada en el menu para repetirlo con un
     * bucle.
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
     * Funcion para mover una unidad a una posicion aleatoria.En el menu se
     * llamara a esta funcion que para cada unidad tendra unos limites de
     * movimiento y se le restara una energia en funcion de las casillas que se
     * mueve. Ademas, controla si hay una unidad en el mapa o no, si no hay la
     * situa, si la hay cambia la coordenada.
     *
     * @param unidad
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void moverUnidad(Unidad unidad) throws FueradeMapaException {
        boolean coordenadacorrecta = true;
        boolean bucleexception = true;
        int antiguacoordx = unidad.getPosicion().getX();
        int antiguacoordy = unidad.getPosicion().getY();
        while (bucleexception) {
            try {
                if (unidad instanceof AT99) {
                    Coordenadas coordenada;
                    while (coordenadacorrecta) {
                        coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                        if (this.escenario.checkMapaAñadir(unidad, coordenada)) {
                            unidad.setPosicion(coordenada);
                            this.escenario.checkMapaAñadir(unidad, coordenada);
                            coordenadacorrecta = false;
                        } else {
                            coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                            coordenadacorrecta = true;
                        }
                    }
                }
                if (unidad instanceof MK6) {
                    Coordenadas coordenada;
                    while (coordenadacorrecta) {
                        coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                        if (this.escenario.checkMapaAñadir(unidad, coordenada)) {
                            unidad.setPosicion(coordenada);
                            this.escenario.checkMapaAñadir(unidad, coordenada);
                            coordenadacorrecta = false;
                        } else {
                            coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                            coordenadacorrecta = true;
                        }
                    }
                }
                if (unidad instanceof Aerospatiale) {
                    Coordenadas coordenada;
                    while (coordenadacorrecta) {
                        coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                        if (this.escenario.checkMapaAñadir(unidad, coordenada)) {
                            unidad.setPosicion(coordenada);
                            this.escenario.checkMapaAñadir(unidad, coordenada);
                            coordenadacorrecta = false;
                        } else {
                            coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                            coordenadacorrecta = true;
                        }
                    }
                }
                if (unidad instanceof Banshee) {
                    Coordenadas coordenada;
                    while (coordenadacorrecta) {
                        coordenada = new Coordenadas(this.escenario);
                        if (this.escenario.checkMapaAñadir(unidad, coordenada)) {
                            unidad.setPosicion(coordenada);
                            this.escenario.checkMapaAñadir(unidad, coordenada);
                            coordenadacorrecta = false;
                        } else {
                            coordenada = new Coordenadas(this.escenario);
                            coordenadacorrecta = true;
                        }
                    }
                }
                if (unidad instanceof Thanator) {
                    Coordenadas coordenada;
                    while (coordenadacorrecta) {
                        coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                        if (this.escenario.checkMapaAñadir(unidad, coordenada)) {
                            unidad.setPosicion(coordenada);
                            this.escenario.checkMapaAñadir(unidad, coordenada);
                            coordenadacorrecta = false;
                        } else {
                            coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                            coordenadacorrecta = true;
                        }
                    }
                }
                if (unidad instanceof Titanothere) {
                    Coordenadas coordenada;
                    while (coordenadacorrecta) {
                        coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                        if (this.escenario.checkMapaAñadir(unidad, coordenada)) {
                            unidad.setPosicion(coordenada);
                            this.escenario.checkMapaAñadir(unidad, coordenada);
                            coordenadacorrecta = false;
                        } else {
                            coordenada = new Coordenadas(unidad, antiguacoordx, antiguacoordy, this.escenario);
                            coordenadacorrecta = true;
                        }
                    }
                }
                break;
            } catch (FueradeMapaException fme) {

            }
        }
    }

    /**
     * Funcion para recibir daño restando las energias de defensa de una unidad
     * y la de los seres
     *
     * @param unidad
     * @param posicion
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void recibirdaño(Unidad unidad, Coordenadas posicion) throws FueradeMapaException {
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
            }
        }
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
