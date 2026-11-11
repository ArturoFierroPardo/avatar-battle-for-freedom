/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.coordenadas;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.unidad.Unidad;

/**
 *
 * @author artur
 */
public class Coordenadas implements Serializable {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Coordenada en eje X
     */
    private int x;

    /**
     * Coordenada en eje Y
     */
    private int y;

    /**
     * Tipo de escenario. Sirve para dar un limite maximo de coordenada en eje x
     * e y en el constructor de coordenadas aleatorias.
     */
    private Escenario escenario;

    /**
     * Casillas movidas en x
     */
    private int casillasmovidasx;

    /**
     * Casillas movidas en y
     */
    private int casillasmovidasy;

    /**
     * Construye una coordenada concreta dentro de un escenario.
     *
     * @param x coordenada en el eje X
     * @param y coordenada en el eje Y
     * @param escenario escenario que fija los limites del mapa
     * @throws modelos.excepciones.FueradeMapaException si la coordenada cae
     * fuera del mapa del escenario
     */
    public Coordenadas(int x, int y, Escenario escenario) throws FueradeMapaException {
        // Antes este constructor no validaba nada, asi que aceptaba
        // coordenadas fuera del mapa y el error no aparecia hasta indexar el
        // array de unidades, con un ArrayIndexOutOfBoundsException.
        comprobarDentroDelMapa(x, y, escenario);
        this.x = x;
        this.y = y;
        this.escenario = escenario;
    }

    /**
     * Crea la coordenada que resulta de desplazarse un numero de casillas desde
     * la posicion actual de una unidad.
     *
     * <p>
     * Sustituye a un constructor que llevaba un quinto parametro {@code int z}
     * sin usar, cuyo unico cometido era distinguirlo de la otra sobrecarga con
     * la misma lista de tipos. Un metodo con nombre propio no necesita ese
     * parametro de relleno y deja claro en la llamada lo que se esta
     * construyendo.
     *
     * @param unidad unidad desde la que se mide el desplazamiento
     * @param dx casillas que se avanzan en el eje X, con signo
     * @param dy casillas que se avanzan en el eje Y, con signo
     * @param escenario escenario que fija los limites del mapa
     * @return la coordenada de destino, recortada a los limites del mapa
     */
    public static Coordenadas desplazada(Unidad unidad, int dx, int dy, Escenario escenario) {
        Coordenadas destino = new Coordenadas();
        destino.escenario = escenario;
        destino.x = recortar(unidad.getPosicion().getX() + dx, escenario.getXmax());
        destino.y = recortar(unidad.getPosicion().getY() + dy, escenario.getYmax());
        return destino;
    }

    /**
     * Constructor vacio de uso interno, para las factorias estaticas.
     */
    private Coordenadas() {
    }

    /**
     * Encaja un valor en el intervalo de indices validos del mapa.
     *
     * @param valor valor a recortar
     * @param limite numero de casillas del eje, de modo que el indice maximo
     * valido es {@code limite - 1}
     * @return el valor recortado al intervalo [0, limite - 1]
     */
    private static int recortar(int valor, int limite) {
        if (valor < 0) {
            return 0;
        }
        if (valor > limite - 1) {
            return limite - 1;
        }
        return valor;
    }

    /**
     * Constructor de coordenadas que recibe un escenario por parametro para
     * poder ver su limite maximo, el cual pone a los atributos x e y
     * aleatoriamente en funcion del escenario para que no se pase.
     *
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Coordenadas(Escenario escenario) throws FueradeMapaException {
        this.escenario = escenario;
        Random rand = new Random();
        this.x = rand.nextInt(this.escenario.getXmax());
        this.y = rand.nextInt(this.escenario.getYmax());
        checkCoordenadaX(this.x, this.escenario);
        checkCoordenadaY(this.y, this.escenario);
    }

    /**
     * Constructor de coordenadas que recibe un escenario por parametro para
     * poder ver su limite maximo, el cual pone a los atributos x e y
     * aleatoriamente en funcion de la unidad que se le pasa por parametro para
     * ponerlo dentro de sus limites para que no se pase más la coordenada en la
     * que estaba. Se gestiona cuantas casillas se mueve restando la posicion en
     * x y en y de la anterior coordenada con las de la nueva respectivamente.
     *
     * @param unidad
     * @param x
     * @param y
     * @param escenario
     * @throws modelos.excepciones.FueradeMapaException
     */
    public Coordenadas(Unidad unidad, int x, int y, Escenario escenario) throws FueradeMapaException {
        int restacasillas;
        this.escenario = escenario;
        Random rand = new Random();
        int xaleatoria = rand.nextInt(-1 * unidad.getMaxmov(), unidad.getMaxmov());
        if (xaleatoria < 0) {
            restacasillas = -xaleatoria;
        } else {
            restacasillas = xaleatoria;
        }
        if (restacasillas == unidad.getMaxmov()) {
            this.y = y;
        } else {
            int yaleatoria = rand.nextInt(-1 * (unidad.getMaxmov() - restacasillas), unidad.getMaxmov() - restacasillas);
            // La ultima casilla valida del eje es ymax - 1, no ymax: con la
            // comparacion estricta anterior una unidad podia acabar justo en
            // ymax y abortar la partida al indexar el mapa.
            if (y + yaleatoria >= escenario.getYmax()) {
                this.y = y;
            } else {
                this.y = y + yaleatoria;
            }
        }
        if (x + xaleatoria >= escenario.getXmax()) {
            this.x = x;
        } else {
            this.x = x + xaleatoria;
        }
        if (this.x < 0) {
            this.x = 0;
        }
        if (this.y < 0) {
            this.y = 0;
        }

        comprobarDentroDelMapa(this.x, this.y, this.escenario);
        // Las casillas recorridas se guardan en valor absoluto: solo se usan
        // para calcular el gasto de energia, que no depende del sentido del
        // movimiento.
        this.casillasmovidasx = Math.abs(this.x - x);
        this.casillasmovidasy = Math.abs(this.y - y);
    }

    /**
     * Setters
     *
     * @param x
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void setX(int x) throws FueradeMapaException {
        // Se valida antes de asignar: al hacerlo al reves, una coordenada
        // rechazada quedaba igualmente guardada en el objeto.
        checkCoordenadaX(x, this.escenario);
        this.x = x;
    }

    /**
     *
     * @param y
     * @throws FueradeMapaException
     */
    public void setY(int y) throws FueradeMapaException {
        checkCoordenadaY(y, this.escenario);
        this.y = y;
    }

    /**
     * Getters
     *
     * @return
     */
    public int getX() {
        return this.x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return this.y;
    }

    /**
     *
     * @return
     */
    public int getCasillasMovidasY() {
        return this.casillasmovidasy;
    }

    /**
     *
     * @return
     */
    public int getCasillasMovidasX() {
        return this.casillasmovidasx;
    }

    /**
     * Comprueba que una coordenada del eje X cabe en el mapa del escenario.
     *
     * <p>
     * El mapa se crea como {@code new Unidad[xmax][ymax]}, asi que los indices
     * validos van de 0 a {@code xmax - 1}. La comprobacion original usaba
     * {@code X > xmax} y por tanto dejaba pasar {@code X == xmax}, que luego
     * reventaba al indexar el array: bastaba que una unidad se moviera al borde
     * del mapa para abortar la partida con un
     * ArrayIndexOutOfBoundsException.
     *
     * @param x coordenada a comprobar
     * @param escenario escenario que fija los limites del mapa
     * @throws modelos.excepciones.FueradeMapaException si la coordenada no es
     * un indice valido del mapa
     */
    public static void checkCoordenadaX(int x, Escenario escenario) throws FueradeMapaException {
        if (x < 0 || x >= escenario.getXmax()) {
            throw new FueradeMapaException();
        }
    }

    /**
     * Comprueba que una coordenada del eje Y cabe en el mapa del escenario.
     *
     * @param y coordenada a comprobar
     * @param escenario escenario que fija los limites del mapa
     * @throws modelos.excepciones.FueradeMapaException si la coordenada no es
     * un indice valido del mapa
     */
    public static void checkCoordenadaY(int y, Escenario escenario) throws FueradeMapaException {
        if (y < 0 || y >= escenario.getYmax()) {
            throw new FueradeMapaException();
        }
    }

    /**
     * Comprueba de una vez las dos coordenadas de una casilla.
     *
     * @param x coordenada en el eje X
     * @param y coordenada en el eje Y
     * @param escenario escenario que fija los limites del mapa
     * @throws modelos.excepciones.FueradeMapaException si la casilla cae fuera
     * del mapa
     */
    public static void comprobarDentroDelMapa(int x, int y, Escenario escenario) throws FueradeMapaException {
        checkCoordenadaX(x, escenario);
        checkCoordenadaY(y, escenario);
    }

    /**
     * Función toString que devuelve un String con las coordenadas.
     */
    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    /**
     * Override de la funcion equals para comparar que dos unidades son iguales
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordenadas) {
            Coordenadas c = (Coordenadas) o;
            return (this.x == c.x && this.y == c.y);
        } else {
            return false;
        }
    }

    /**
     * Override del hashcode para esta clase
     */
    @Override
    public int hashCode() {
        // Solo x e y, los dos unicos campos que compara equals(). Incluir el
        // escenario o las casillas movidas rompia el contrato entre ambos
        // metodos: dos coordenadas iguales podian devolver codigos hash
        // distintos y no encontrarse dentro de un HashSet o un HashMap.
        return Objects.hash(this.x, this.y);
    }

}
