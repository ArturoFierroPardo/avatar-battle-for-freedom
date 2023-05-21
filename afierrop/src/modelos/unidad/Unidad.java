/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;
import modelos.coordenadas.Coordenadas;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.jugador.Jugador;
import modelos.ser.Ser;
import utils.Utils;

/**
 *
 * @author artur
 */
public abstract class Unidad implements Serializable {

    /**
     * Nombre de la unidad
     */
    private String nombre;

    /**
     * Jugador al que pertenece la unidad
     */
    private Jugador jugador;

    /**
     * Coste de la unidad
     */
    private int coste;

    /**
     * Cuanta vida tiene para que se destruya
     */
    private int energiadefensa;

    /**
     * Cuanta energia tiene de ataque, se gasta en funcion de cuantos ataques va
     * haciendo
     */
    private int energiataque;

    /**
     * Cuanta energia de movimiento tiene, se gasta en funcion de cuanto se va
     * moviendo
     */
    private int energiamov;

    /**
     * Cuanto se le resta a la energia de ataque por cada ataque hecho y el daño
     * que hace
     */
    private int potencia;

    /**
     * Puntos que se ganan al destruirlo
     */
    private int puntosderrota;

    /**
     * Numero de tripulantes que necesita
     */
    private ArrayList<Ser> tripulantes;

    /**
     * Posición en la que se coloca
     */
    private Coordenadas posicion;

    /**
     * Energia consumida al moverse una casilla
     */
    private int energiagastadaxmov;

    /**
     * Maximas casillas que se puede mover en un turno. Es un String porque
     * Banshee se puede mover a infinitas casillas, no obstante en funciones de
     * moverse lo convertimos en un int con un Parse int
     */
    private String maxmov;

    /**
     * Rango de ataque
     */
    private int rangoataque;

    /**
     * Seres necesarios para el funcionamiento
     */
    private int seresneed;

    /**
     * Constructor de Unidad.Recibe por parametros todo sus atributos que son
     * especificados por sus hijos los cuales heredan este constructor.Datos sin
     * especificar:
     *
     * @param nombre
     * @param jugador
     * @param coste
     * @param energiadefensa
     * @param energiataque
     * @param energiamov
     * @param potencia
     * @param puntosderrota
     * @param tripulantes
     * @param posicion
     * @param energiagastadaxmov
     * @param maxmov
     * @param rangoataque
     * @param seresneed
     *
     *
     */
    public Unidad(String nombre, Jugador jugador, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntosderrota, ArrayList<Ser> tripulantes, Coordenadas posicion, int energiagastadaxmov, String maxmov, int rangoataque, int seresneed) {
        this.nombre = nombre;
        this.jugador = jugador;
        this.coste = coste;
        this.energiadefensa = energiadefensa;
        this.energiataque = energiataque;
        this.energiamov = energiamov;
        this.potencia = potencia;
        this.puntosderrota = puntosderrota;
        this.tripulantes = tripulantes;
        this.posicion = posicion;
        this.energiagastadaxmov = energiagastadaxmov;
        this.maxmov = maxmov;
        this.rangoataque = rangoataque;
        this.seresneed = seresneed;

    }

    /**
     * Setters
     *
     * @param energiadefensa
     */
    public void setEnergiadefensa(int energiadefensa) {
        this.energiadefensa = energiadefensa;
    }

    /**
     *
     * @param energiataque
     */
    public void setEnergiataque(int energiataque) {
        this.energiataque = energiataque;
    }

    /**
     *
     * @param energiamov
     */
    public void setEnergiamov(int energiamov) {
        this.energiamov = energiamov;
    }

    /**
     *
     * @param posicion
     */
    public void setPosicion(Coordenadas posicion) {
        this.posicion = posicion;
    }

    /**
     *
     * @param tripulantes
     */
    public void setTripulantes(ArrayList<Ser> tripulantes) {
        this.tripulantes = tripulantes;
    }

    /**
     *
     * @param jugador
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Getters.
     *
     *
     * Para maxmov se pasa a int ya que maxmov es un String puesto ya que
     * banshee no tiene rango de movimiento y se la he puesto como un string que
     * pone movimiento aleatorio, así le puedo poner ese atributo
     *
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
    public int getEnergiadefensa() {
        return this.energiadefensa;
    }

    /**
     *
     * @return
     */
    public int getEnergiataque() {
        return this.energiataque;
    }

    /**
     *
     * @return
     */
    public int getEnergiamov() {
        return this.energiamov;
    }

    /**
     *
     * @return
     */
    public int getPotencia() {
        return this.potencia;
    }

    /**
     *
     * @return
     */
    public int getPuntosderrota() {
        return this.puntosderrota;
    }

    /**
     *
     * @return
     */
    public ArrayList<Ser> getTripulantes() {
        return this.tripulantes;
    }

    /**
     *
     * @return
     */
    public Coordenadas getPosicion() {
        return this.posicion;
    }

    /**
     *
     * @return
     */
    public int getEnergiagastadaxmov() {
        return this.energiagastadaxmov;
    }

    /**
     *
     * @return
     */
    public int getMaxmov() {
        int maxmov1 = Integer.parseInt(this.maxmov);
        return maxmov1;
    }

    /**
     *
     * @return
     */
    public int getRangoataque() {
        return this.rangoataque;
    }

    /**
     *
     * @return
     */
    public int getSeresneed() {
        return this.seresneed;
    }

    /**
     *
     * @return
     */
    public Jugador getJugador() {
        return this.jugador;
    }

    /**
     * Funcion para restar energia de ataque en funcion de cada tipo de unidad y
     * lanza las excepciones
     *
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     */
    public void restarEnergiaAtaque() throws SinEnergiaAtaqueException {
        if (this.getEnergiataque() >= this.getPotencia()) {
            this.setEnergiataque(this.getEnergiataque() - this.getPotencia());
        } else {
            throw new SinEnergiaAtaqueException();
        }
    }

    /**
     * Funcion para restar energia de movimiento en funcion de cada tipo de
     * unidad y lanza las excepciones. Se hace override, ya que bansee,
     * titanothere y mk6 son las unicas unidades que consumen energia de
     * movimiento de manera estatica en vez de en funcion de las casilllas que
     * se mueven.
     *
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     */
    public void restarEnergia() throws SinEnergiaMovimientoException {
        if (this.getEnergiamov() >= this.getEnergiagastadaxmov() * (this.getPosicion().getCasillasMovidasX() + this.getPosicion().getCasillasMovidasY())) {
            this.setEnergiamov(this.getEnergiamov() - (this.getEnergiagastadaxmov() * (this.getPosicion().getCasillasMovidasX() + this.getPosicion().getCasillasMovidasY())));
        } else {
            throw new SinEnergiaMovimientoException();
        }
    }

    /**
     * La funcion moverunidad, genera unas coordenadas aleatorias dentro del
     * rango de movimiento sumadas a la antigua coordenada de la unidad y
     * chequea si puede moverse o no, si puede se mueve, y si no genera otra
     * hasta poder.Se controlan todas las excepciones. Esta funcion es
     * Overrideada ya que banshee utiliza unas coordenadas diferentes a las de
     * las demas unidades ya que no tiene rango de movimiento más que el propio
     * escenario. Comprueba que no se vaya del mapa.
     *
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void moverUnidad(Escenario escenario) throws FueradeMapaException {
        boolean coordenadacorrecta = true;
        boolean bucleexception = true;
        int antiguacoordx = this.getPosicion().getX();
        int antiguacoordy = this.getPosicion().getY();
        while (bucleexception) {
            try {
                Coordenadas coordenada;
                while (coordenadacorrecta) {
                    coordenada = new Coordenadas(this, antiguacoordx, antiguacoordy, escenario);
                    if (escenario.checkMapaAñadir(this, coordenada)) {
                        escenario.getMapa()[this.posicion.getX()][this.posicion.getY()] = null;
                        this.setPosicion(coordenada);
                        escenario.checkMapaAñadir(this, coordenada);
                        coordenadacorrecta = false;
                    } else {
                        coordenada = new Coordenadas(this, antiguacoordx, antiguacoordy, escenario);
                        coordenadacorrecta = true;
                    }
                }
                break;
            } catch (FueradeMapaException fme) {
            }
        }
    }

    /**
     * Funcion atacar unidad que controla donde desea atacar el jugador
     * solictando las nuevas coordenadas en x y en y y chequeando que estan
     * dentro del rango de la unidad, si no se repite en bucle recordando el
     * rango de ataque de la unidad.Si esta dentro del rango de ataque, se crea
     * una coordenada con las posiciones antiguas de la unidad sumadas a donde
     * quiere atacar y chequea que hay una unidad ahi o no.Si hay una unidad se
     * llama a la funcion recibir daño que esta en jugador y hace daño a la
     * unidad establecida como la atacada, y sale del bucle.Si no habia ninguna
     * unidad, sale del bucle y avisa de que no ha daod a ningun enemigo el
     * disparo.Se controlan todas las excepciones posibles. Tambien comprueba
 que el rango de ataque esta dentro del mapa, si no le pide que vuelva a
 intentarlo.
     *
     * @param jugador
     * @param escenario
     * @return 
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     */
    public String atacarUnidad(Jugador jugador, Escenario escenario) throws FueradeMapaException, SinEnergiaAtaqueException {
        boolean nounidad = true;
        int ataquex2 = 0;
        int ataquey2 = 0;
        while (nounidad) {
            int ataquex = Utils.leerEntero("\nDonde desea atacar en el eje X desde su posicion? Dentro de su rango de ataque que es: " + this.getRangoataque());

            if (ataquex < 0) {
                ataquex2 = -ataquex;
            } else {
                ataquex2 = ataquex;
            }
            int ataquey = Utils.leerEntero("\nDonde desea atacar en el eje Y desde su posicion? Dentro de su rango de ataque que es: " + (this.getRangoataque() - ataquex2));
            if (ataquey < 0) {
                ataquey2 = -ataquey;
            } else {
                ataquey2 = ataquey;
            }
            if (ataquex + this.posicion.getX() > escenario.getXmax()) {
                ataquex2 = this.posicion.getX() + ataquex;
                System.out.println("NO PUEDE ATACAR FUERA DEL MAPA. VUELVA A INTENTARLO");
                nounidad = true;
            }
            if (ataquex + this.posicion.getX() < 0) {
                ataquex2 = this.posicion.getX() + ataquex;
                System.out.println("NO PUEDE ATACAR FUERA DEL MAPA. VUELVA A INTENTARLO");
                nounidad = true;
            }
            if (ataquey + this.posicion.getY() > escenario.getYmax()) {
                ataquey2 = this.posicion.getY() + ataquey;
                System.out.println("NO PUEDE ATACAR FUERA DEL MAPA. VUELVA A INTENTARLO");
                nounidad = true;
            }
            if (ataquey + this.posicion.getY() < 0) {
                ataquey2 = this.posicion.getY() + ataquey;
                System.out.println("NO PUEDE ATACAR FUERA DEL MAPA. VUELVA A INTENTARLO");
                nounidad = true;
            }
            if (ataquex2 <= this.getRangoataque() && ataquey2 <= this.getRangoataque() - ataquex2 && ataquex2 >= 0 && ataquey2 >= 0) {
                Coordenadas posicioninvestigar = new Coordenadas(this, ataquex, ataquey, escenario, 1);
                if (!(escenario.checkMapa(this, posicioninvestigar, escenario))) {
                    Unidad unidadahi = escenario.getunidadMapa(posicioninvestigar);
                    jugador.recibirdaño(this, posicioninvestigar);
                    if (escenario.checkMapa(this, posicioninvestigar, escenario)) {
                        System.out.println("\nHA ACABADO CON LA UNIDAD QUE HABIA EN LA POSICION " + posicioninvestigar + " SE TRATABA DE UN " + unidadahi.getNombre() + "\n");
                        nounidad = false;
                    } else {
                        System.out.println("\nHA ACERTADO Y DISPARADO CON EXITO\n");
                        nounidad = false;
                        if (this.jugador.equals(unidadahi.jugador)) {
                            return "El jugador " + this.jugador.getNombre() + " ha atacado con su " + this.nombre + " a un " + unidadahi.getNombre() + " en la posicion " + unidadahi.posicion.toString() + ". Le quedan " + unidadahi.getEnergiadefensa() + " puntos de energia de defensa y esta trpulado por " + unidadahi.getTripulantes() + ". HA SIDO FUEGO AMIGO" + "\n";
                        } else {
                            return "El jugador " + this.jugador.getNombre() + " ha atacado con su " + this.nombre + " a un " + unidadahi.getNombre() + " en la posicion " + unidadahi.posicion.toString() + ". Le quedan " + unidadahi.getEnergiadefensa() + " puntos de energia de defensa y esta trpulado por " + unidadahi.getTripulantes() + ". NO HA SIDO FUEGO AMIGO" + "\n";
                        }
                    }
                } else {
                    nounidad = false;
                    System.out.println("\nNO HABIA NINGUNA UNIDAD EN LA POSICION " + posicioninvestigar + "\n");
                    return "";

                }
                nounidad = false;
            } else {
                nounidad = true;
                System.out.println("RESPETE EL RANGO DE ATAQUE DE LA UNIDAD. VUELVA A INTENTARLO");
                System.out.println("SU RANGO DE ATAQUE ES: " + this.getRangoataque());
            }

        }
        return "";
    }

    /**
     * Clase stataic para comparar que hace un override para llamar a la funcion
     * compare reescribiendola la cual recibe dos unidas y llama a utils que
     * compara enteros para comparar las energias de defensa de dos unidades
     *
     *
     */
    public static class comparadorEDEF implements Comparator<Unidad> {

        /**
         *
         * @param unidad1
         * @param unidad2
         * @return
         */
        @Override
        public int compare(Unidad unidad1, Unidad unidad2) {
            if (unidad1.getEnergiadefensa() > unidad2.getEnergiadefensa()) {
                return -1;
            } else if (unidad1.getEnergiadefensa() < unidad2.getEnergiadefensa()) {
                return 1;
            } else {
                return 0;
            }

        }
    }

    /**
     * toString que devuelve un string con toda la información y si la posicion
     * es null (que es al comprarla) imprime un string en vez del objeto
     *
     * @return
     */
    public String toStringInfo() {
        if (this.posicion == null) {
            return "\tNombre: " + this.nombre + "\n"
                    + "\tPosicion: Unidad no desplegada\n"
                    + "\tCoste: " + this.coste + "\n"
                    + "\tEnergia de defensa: " + this.energiadefensa + "\n"
                    + "\tEnergia de ataque: " + this.energiataque + "\n"
                    + "\tEnergia de movimiento: " + this.energiamov + "\n"
                    + "\tPotencia: " + this.potencia + "\n"
                    + "\tPuntos si se derrota: " + this.puntosderrota + " \n"
                    + "\tTripulantes actuales: " + this.tripulantes + "\n"
                    + "\tSeres necesarios para el funcionamiento: " + this.seresneed + "\n"
                    + "\tEnergia que gasta al moverse: " + this.energiagastadaxmov + "\n"
                    + "\tMaximas casillas que puede moverse por turno: " + this.maxmov + "\n"
                    + "\tRango de ataque: " + this.rangoataque + " casillas\n";
        } else {
            return "\tNombre: " + this.nombre + "\n"
                    + "\tPosicion: " + this.posicion.toString() + "\n"
                    + "\tCoste: " + this.coste + "\n"
                    + "\tEnergia de defensa: " + this.energiadefensa + "\n"
                    + "\tEnergia de ataque: " + this.energiataque + "\n"
                    + "\tEnergia de movimiento: " + this.energiamov + "\n"
                    + "\tPotencia: " + this.potencia + "\n"
                    + "\tPuntos si se derrota: " + this.puntosderrota + " \n"
                    + "\tTripulantes actuales: " + this.tripulantes + "\n"
                    + "\tSeres necesarios para el funcionamiento: " + this.seresneed + "\n"
                    + "\tEnergia que gasta al moverse: " + this.energiagastadaxmov + "\n"
                    + "\tMaximas casillas que puede moverse por turno: " + this.maxmov + "\n"
                    + "\tRango de ataque: " + this.rangoataque + " casillas\n";
        }
    }

    /**
     * Override de toString que devuelve el nombre de la unidad y sus
     * tripulantes
     */
    @Override
    public String toString() {
        return this.nombre + " tripulado por " + this.tripulantes;
    }

    /**
     * Override de la funcion equals para comparar si dos unidades son iguales
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof Unidad) {
            Unidad u = (Unidad) o;
            return (this.nombre.equals(u.nombre)
                    && this.coste == u.coste
                    && this.potencia == u.potencia
                    && this.energiadefensa == u.energiadefensa
                    && this.energiamov == u.energiamov
                    && this.energiataque == u.energiataque
                    && this.jugador == u.jugador
                    && this.posicion == u.posicion
                    && this.rangoataque == u.rangoataque
                    && this.tripulantes == u.tripulantes
                    && this.nombre.equals(u.nombre));
        } else {
            return false;
        }
    }

    /**
     * Override del hashcode para esta clase
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.nombre);
        hash = 37 * hash + Objects.hashCode(this.jugador);
        hash = 37 * hash + this.coste;
        hash = 37 * hash + this.energiadefensa;
        hash = 37 * hash + this.energiataque;
        hash = 37 * hash + this.energiamov;
        hash = 37 * hash + this.potencia;
        hash = 37 * hash + this.puntosderrota;
        hash = 37 * hash + Objects.hashCode(this.tripulantes);
        hash = 37 * hash + Objects.hashCode(this.posicion);
        hash = 37 * hash + this.energiagastadaxmov;
        hash = 37 * hash + Objects.hashCode(this.maxmov);
        hash = 37 * hash + this.rangoataque;
        hash = 37 * hash + this.seresneed;
        return hash;
    }
}
