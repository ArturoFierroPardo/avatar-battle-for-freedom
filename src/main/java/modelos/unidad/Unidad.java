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
 * Pieza que un jugador despliega y maneja sobre el mapa. Define las
 * caracteristicas comunes a maquinas y bestias (coste, energias, potencia,
 * alcance y tripulacion) y como se mueven y atacan.
 *
 * @author Arturo Fierro Pardo
 */
public abstract class Unidad implements Serializable {

    /**
     * Valor de {@code maxmov} que indica que la unidad no tiene alcance
     * maximo de movimiento y se desplaza a una casilla cualquiera del mapa.
     */
    public static final int MOVIMIENTO_ALEATORIO = -1;

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

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
     * Maximas casillas que se puede mover en un turno, o
     * {@link #MOVIMIENTO_ALEATORIO} si la unidad no tiene alcance limitado y
     * salta a una casilla cualquiera del mapa.
     *
     * <p>
     * Antes era un {@code String} para poder guardar el texto "Casilla
     * aleatoria" en el caso de la Banshee, y el getter hacia un
     * {@code Integer.parseInt} sobre el, de forma que consultar el alcance de
     * una Banshee lanzaba NumberFormatException. Ahora el dato es numerico y el
     * texto solo se genera al mostrarlo.
     */
    private int maxmov;

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
    public Unidad(String nombre, Jugador jugador, int coste, int energiadefensa, int energiataque, int energiamov, int potencia, int puntosderrota, ArrayList<Ser> tripulantes, Coordenadas posicion, int energiagastadaxmov, int maxmov, int rangoataque, int seresneed) {
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
     * @return el nombre de la unidad
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
        return this.maxmov;
    }

    /**
     * Indica si la unidad se mueve a una casilla cualquiera del mapa en lugar
     * de tener un alcance limitado.
     *
     * @return {@code true} si su movimiento es aleatorio y sin alcance maximo
     */
    public boolean tieneMovimientoAleatorio() {
        return this.maxmov == MOVIMIENTO_ALEATORIO;
    }

    /**
     * Devuelve el alcance de movimiento en un texto apto para mostrar al
     * jugador.
     *
     * @return el numero de casillas, o "Casilla aleatoria" si no tiene alcance
     * limitado
     */
    public String getMaxmovDescripcion() {
        return tieneMovimientoAleatorio() ? "Casilla aleatoria" : String.valueOf(this.maxmov);
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
                    if (escenario.colocarUnidadSiLibre(this, coordenada)) {
                        escenario.getMapa()[this.posicion.getX()][this.posicion.getY()] = null;
                        this.setPosicion(coordenada);
                        escenario.colocarUnidadSiLibre(this, coordenada);
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
            // La casilla de destino tiene que ser un indice valido del mapa:
            // de 0 a xmax - 1 y de 0 a ymax - 1. Antes se comparaba con
            // "> xmax", de modo que un ataque justo al borde pasaba el filtro y
            // abortaba la partida al indexar el mapa. Ademas el aviso se
            // imprimia pero no se cortaba la iteracion, asi que el ataque podia
            // llegar a ejecutarse de todos modos.
            int destinox = this.posicion.getX() + ataquex;
            int destinoy = this.posicion.getY() + ataquey;
            if (destinox < 0 || destinox >= escenario.getXmax()
                    || destinoy < 0 || destinoy >= escenario.getYmax()) {
                System.out.println("NO PUEDE ATACAR FUERA DEL MAPA. VUELVA A INTENTARLO");
                nounidad = true;
                continue;
            }
            if (ataquex2 <= this.getRangoataque() && ataquey2 <= this.getRangoataque() - ataquex2 && ataquex2 >= 0 && ataquey2 >= 0) {
                Coordenadas posicioninvestigar = Coordenadas.desplazada(this, ataquex, ataquey, escenario);
                if (!(escenario.estaLibre(this, posicioninvestigar, escenario))) {
                    Unidad unidadahi = escenario.getUnidadMapa(posicioninvestigar);
                    jugador.recibirDano(this, posicioninvestigar);
                    if (escenario.estaLibre(this, posicioninvestigar, escenario)) {
                        System.out.println("\nHA ACABADO CON LA UNIDAD QUE HABIA EN LA POSICION " + posicioninvestigar + " SE TRATABA DE UN " + unidadahi.getNombre() + "\n");
                        nounidad = false;
                    } else {
                        System.out.println("\nHA ACERTADO Y DISPARADO CON EXITO\n");
                        nounidad = false;
                        if (this.jugador.equals(unidadahi.jugador)) {
                            return "El jugador " + this.jugador.getNombre() + " ha atacado con su " + this.nombre + " a un " + unidadahi.getNombre() + " en la posicion " + unidadahi.posicion.toString() + ". Le quedan " + unidadahi.getEnergiadefensa() + " puntos de energia de defensa y esta tripulado por " + unidadahi.getTripulantes() + ". HA SIDO FUEGO AMIGO" + "\n";
                        } else {
                            return "El jugador " + this.jugador.getNombre() + " ha atacado con su " + this.nombre + " a un " + unidadahi.getNombre() + " en la posicion " + unidadahi.posicion.toString() + ". Le quedan " + unidadahi.getEnergiadefensa() + " puntos de energia de defensa y esta tripulado por " + unidadahi.getTripulantes() + ". NO HA SIDO FUEGO AMIGO" + "\n";
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
                    + "\tMaximas casillas que puede moverse por turno: " + getMaxmovDescripcion() + "\n"
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
                    + "\tMaximas casillas que puede moverse por turno: " + getMaxmovDescripcion() + "\n"
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
     * Compara dos unidades por su estado: nombre, caracteristicas, posicion y
     * tripulacion.
     *
     * <p>
     * El jugador dueño de la unidad queda deliberadamente fuera de la
     * comparacion. Es una referencia hacia atras: el jugador contiene su
     * ejercito, el ejercito contiene sus unidades y la unidad volveria a
     * apuntar al jugador, de modo que incluirlo haria que la comparacion se
     * llamase a si misma hasta desbordar la pila.
     *
     * @param o objeto con el que comparar
     * @return {@code true} si representan la misma unidad en el mismo estado
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Unidad) {
            Unidad u = (Unidad) o;
            return Objects.equals(this.nombre, u.nombre)
                    && this.coste == u.coste
                    && this.energiadefensa == u.energiadefensa
                    && this.energiataque == u.energiataque
                    && this.energiamov == u.energiamov
                    && this.potencia == u.potencia
                    && this.puntosderrota == u.puntosderrota
                    && this.energiagastadaxmov == u.energiagastadaxmov
                    && this.rangoataque == u.rangoataque
                    && this.seresneed == u.seresneed
                    && this.maxmov == u.maxmov
                    && Objects.equals(this.posicion, u.posicion)
                    && Objects.equals(this.tripulantes, u.tripulantes);
        } else {
            return false;
        }
    }

    /**
     * Devuelve un codigo hash coherente con {@link #equals(Object)}, sobre los
     * mismos campos y excluyendo igualmente al jugador dueño.
     *
     * @return el codigo hash de la unidad
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.nombre, this.coste, this.energiadefensa,
                this.energiataque, this.energiamov, this.potencia,
                this.puntosderrota, this.energiagastadaxmov, this.rangoataque,
                this.seresneed, this.maxmov, this.posicion, this.tripulantes);
    }
}
