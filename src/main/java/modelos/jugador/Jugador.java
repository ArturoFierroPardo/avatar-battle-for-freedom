/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.jugador;

import java.io.Serializable;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
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
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

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
     */
    public void setEjercito(Ejercito ejercito) {
        this.ejercito = ejercito;
    }

    /**
     *
     * @param puntos
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    /**
     *
     * @param acabarpartida
     */
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

    /**
     *
     * @return
     */
    public Equipo getEquipo() {
        return this.equipo;
    }

    /**
     *
     * @return
     */
    public int getPuntos() {
        return this.puntos;
    }

    /**
     *
     * @return
     */
    public Ejercito getEjercito() {
        return this.ejercito;
    }

    /**
     *
     * @return
     */
    public boolean getAcabarPartida() {
        return this.acabarpartida;
    }

    /**
     * Función de compra de Unidades.Si el jugador tiene los puntos necesarios
     * para la compra de la unidad, se compra y se restan los puntos gastados.Si
     * no, se lanza la excepcion que sera catcheada en el menu para repetirlo
     * con un bucle.Al comprar la unidad se busca una casilla libre del mapa
     * sorteando coordenadas hasta encontrarla, y se despliega ahi.
     *
     * @param unidad unidad que se quiere comprar
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException si el
     * jugador no tiene puntos suficientes
     * @throws modelos.excepciones.FueradeMapaException si la coordenada
     * sorteada cae fuera de los limites del escenario
     */
    public void comprarUnidad(Unidad unidad) throws PuntosdeCompraInsuficientesException, FueradeMapaException {
        if (this.puntos < unidad.getCoste()) {
            throw new PuntosdeCompraInsuficientesException();
        }
        // colocarUnidadSiLibre devuelve true cuando la casilla estaba vacia y
        // ha colocado ahi la unidad, asi que hay que seguir sorteando mientras
        // devuelva false.
        Coordenadas posicion;
        do {
            posicion = new Coordenadas(this.escenario);
        } while (!this.escenario.colocarUnidadSiLibre(unidad, posicion));
        unidad.setPosicion(posicion);
        this.ejercito.getUnidades().add(unidad);
        Collections.sort(this.ejercito.getUnidades(), new Unidad.comparadorEDEF());
        this.puntos = this.puntos - unidad.getCoste();
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
     * es distinta para algunas unidades y no general.Pide la unidad que hay que
     * mover por parametro. Devuelve una unidad.
     *
     * @param unidad
     * @return
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Unidad moverUnidad(Unidad unidad) throws FueradeMapaException {
        unidad.moverUnidad(this.escenario);
        return unidad;
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
     * que es distinta para algunas unidades y no general.Pide por parametro una
     * unidad que es la que va a atacar.
     *
     * @param unidad
     * @return
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     */
    public String atacarUnidad(Unidad unidad) throws FueradeMapaException, SinEnergiaAtaqueException {
        String ataque = unidad.atacarUnidad(this, this.escenario);
        return ataque;
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
     * Aplica el daño de un ataque sobre la unidad que ocupa la posicion
     * indicada.
     *
     * <p>
     * La potencia del atacante se descuenta primero de la energia de defensa de
     * la unidad atacada. Si la potencia no llega a agotarla, ahi acaba el
     * ataque. Si la supera, la energia de defensa queda a cero y el exceso se
     * reparte a partes iguales entre los tripulantes: los que se quedan sin
     * vida mueren y dan sus puntos al atacante, y si la unidad se queda sin
     * tripulantes queda destruida, se retira del ejercito de su dueño y se
     * libera su casilla del mapa.
     *
     * @param unidad unidad atacante, de la que se toma la potencia
     * @param posicion casilla del mapa donde esta la unidad atacada
     * @throws modelos.excepciones.FueradeMapaException si la posicion queda
     * fuera de los limites del escenario
     */
    public void recibirDano(Unidad unidad, Coordenadas posicion) throws FueradeMapaException {
        Unidad atacada = this.escenario.getUnidadMapa(posicion);
        if (atacada == null) {
            return;
        }

        if (unidad.getPotencia() <= atacada.getEnergiadefensa()) {
            atacada.setEnergiadefensa(atacada.getEnergiadefensa() - unidad.getPotencia());
            Collections.sort(this.ejercito.getUnidades(), new Unidad.comparadorEDEF());
            return;
        }

        // El exceso se calcula ANTES de poner la energia de defensa a cero: de
        // lo contrario se repartiria la potencia entera del atacante en vez de
        // solo la parte que ha atravesado la defensa.
        int exceso = unidad.getPotencia() - atacada.getEnergiadefensa();
        atacada.setEnergiadefensa(0);

        List<Ser> tripulantes = atacada.getTripulantes();
        if (tripulantes.isEmpty()) {
            // Sin tripulantes no hay entre quien repartir el exceso, y dividir
            // por su numero seria una division por cero.
            destruirUnidad(atacada, posicion);
            Collections.sort(this.ejercito.getUnidades(), new Unidad.comparadorEDEF());
            return;
        }

        int danoPorTripulante = exceso / tripulantes.size();
        // Se recorre con un Iterator porque los tripulantes que mueren se
        // eliminan de la lista: hacerlo dentro de un for-each lanzaria una
        // ConcurrentModificationException.
        Iterator<Ser> it = tripulantes.iterator();
        while (it.hasNext()) {
            Ser ser = it.next();
            ser.setVida(ser.getVida() - danoPorTripulante);
            if (ser.getVida() <= 0) {
                this.puntos = this.puntos + ser.getPuntosderrota();
                it.remove();
            }
        }

        if (tripulantes.isEmpty()) {
            destruirUnidad(atacada, posicion);
        }

        Collections.sort(this.ejercito.getUnidades(), new Unidad.comparadorEDEF());
    }

    /**
     * Retira una unidad destruida del ejercito de su dueño y libera su casilla
     * del mapa, sumando sus puntos de derrota a este jugador.
     *
     * @param destruida unidad que queda fuera de combate
     * @param posicion casilla del mapa que ocupaba
     */
    private void destruirUnidad(Unidad destruida, Coordenadas posicion) {
        this.puntos = this.puntos + destruida.getPuntosderrota();
        if (destruida.getJugador() != null) {
            destruida.getJugador().getEjercito().getUnidades().remove(destruida);
        }
        this.escenario.getMapa()[posicion.getX()][posicion.getY()] = null;
    }

    /**
     * Función toString que devuelve un string con el nombre, el equipo, los
     * puntos y el ejercito de jugador
     */
    @Override
    public String toString() {
        return "\nEl jugador " + this.nombre + " es del equipo " + this.equipo + ", consta de " + this.puntos + " puntos y su ejercito esta formado por: \n";
    }

    /**
     * Override de la funcion equals para comparar si dos jugadores son iguales
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Jugador) {
            Jugador j = (Jugador) o;
            return Objects.equals(this.nombre, j.nombre)
                    && this.equipo == j.equipo
                    && this.puntos == j.puntos
                    && this.acabarpartida == j.acabarpartida
                    && Objects.equals(this.ejercito, j.ejercito);
        } else {
            return false;
        }
    }

    /**
     * Devuelve un codigo hash coherente con {@link #equals(Object)}.
     *
     * <p>
     * El escenario queda fuera a proposito: lo comparten los dos jugadores de
     * la partida y su mapa contiene unidades que apuntan de vuelta a sus
     * jugadores, asi que incluirlo haria que el calculo se llamase a si mismo
     * hasta desbordar la pila.
     *
     * @return el codigo hash del jugador
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.nombre, this.equipo, this.puntos,
                this.acabarpartida, this.ejercito);
    }

}
