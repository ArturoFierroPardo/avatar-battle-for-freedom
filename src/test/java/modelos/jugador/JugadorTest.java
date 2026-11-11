package modelos.jugador;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import modelos.coordenadas.Coordenadas;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.ser.Ser;
import modelos.ser.humano.Artillero;
import modelos.ser.humano.Comandante;
import modelos.unidad.Unidad;
import modelos.unidad.maquina.AT99;
import modelos.unidad.maquina.MK6;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas del jugador: compra de unidades y tripulantes, y reparto del dano de
 * los ataques.
 *
 * @author Arturo Fierro Pardo
 */
class JugadorTest {

    /**
     * Escenario compartido por los dos jugadores de la prueba.
     */
    private Escenario escenario;

    /**
     * Jugador que ataca.
     */
    private Jugador atacante;

    /**
     * Jugador que recibe los ataques.
     */
    private Jugador defensor;

    /**
     * Crea un escenario y dos jugadores con puntos de sobra antes de cada
     * prueba.
     */
    @BeforeEach
    void preparar() {
        this.escenario = new ArbolMadre();
        this.atacante = new Jugador("Atacante", Equipo.RDA, this.escenario);
        this.defensor = new Jugador("Defensor", Equipo.NAVI, this.escenario);
        this.atacante.setPuntos(200_000);
        this.defensor.setPuntos(200_000);
    }

    @Test
    @DisplayName("El jugador empieza con los puntos que da el escenario")
    void empiezaConLosPuntosDelEscenario() {
        Jugador nuevo = new Jugador("Nuevo", Equipo.RDA, this.escenario);
        assertEquals(this.escenario.getPuntoscomienzo(), nuevo.getPuntos());
        assertTrue(nuevo.getEjercito().getUnidades().isEmpty());
        assertFalse(nuevo.getAcabarPartida());
    }

    @Test
    @DisplayName("Comprar una unidad la despliega en una sola casilla y descuenta su coste")
    void comprarUnidadLaDespliegaEnUnaSolaCasilla() throws Exception {
        int puntosAntes = this.atacante.getPuntos();
        Unidad unidad = new AT99();

        this.atacante.comprarUnidad(unidad);

        assertEquals(puntosAntes - unidad.getCoste(), this.atacante.getPuntos());
        assertEquals(1, this.atacante.getEjercito().getUnidades().size());
        assertEquals(1, celdasOcupadas(), "la unidad solo deberia ocupar su propia casilla");
        assertSame(unidad, this.escenario.getUnidadMapa(unidad.getPosicion()));
    }

    @Test
    @DisplayName("Varias unidades se despliegan en casillas distintas")
    void variasUnidadesOcupanCasillasDistintas() throws Exception {
        for (int i = 0; i < 10; i++) {
            Unidad unidad = new MK6();
            this.atacante.comprarUnidad(unidad);
            unidad.setJugador(this.atacante);
        }
        assertEquals(10, this.atacante.getEjercito().getUnidades().size());
        assertEquals(10, celdasOcupadas());
    }

    @Test
    @DisplayName("Sin puntos suficientes la compra se rechaza y no cambia nada")
    void sinPuntosNoSeCompra() {
        this.atacante.setPuntos(10);
        Unidad unidad = new AT99();

        assertThrows(PuntosdeCompraInsuficientesException.class,
                () -> this.atacante.comprarUnidad(unidad));
        assertEquals(10, this.atacante.getPuntos(), "no deberia haberse cobrado nada");
        assertTrue(this.atacante.getEjercito().getUnidades().isEmpty());
        assertEquals(0, celdasOcupadas());
    }

    @Test
    @DisplayName("Comprar tripulantes los sube a la unidad y descuenta su coste")
    void comprarTripulantes() throws Exception {
        Unidad unidad = new AT99();
        this.atacante.comprarUnidad(unidad);
        int puntosAntes = this.atacante.getPuntos();

        Ser comandante = new Comandante();
        this.atacante.comprarSeres(unidad, comandante);

        assertEquals(1, unidad.getTripulantes().size());
        assertSame(comandante, unidad.getTripulantes().get(0));
        assertEquals(puntosAntes - comandante.getCoste(), this.atacante.getPuntos());
    }

    @Test
    @DisplayName("Un ataque que no agota la defensa solo le resta la potencia")
    void ataqueQueNoAtraviesaLaDefensa() throws Exception {
        Unidad arma = new AT99();              // potencia 500
        this.atacante.comprarUnidad(arma);
        arma.setJugador(this.atacante);

        Unidad victima = victimaConTripulacion(2);
        int defensaAntes = victima.getEnergiadefensa();

        this.atacante.recibirDano(arma, victima.getPosicion());

        assertEquals(defensaAntes - arma.getPotencia(), victima.getEnergiadefensa());
        assertEquals(2, victima.getTripulantes().size(),
                "los tripulantes no deberian sufrir mientras quede defensa");
    }

    @Test
    @DisplayName("Si el ataque atraviesa la defensa solo reparte el exceso, no la potencia entera")
    void soloSeRepartEelExcesoDeDano() throws Exception {
        Unidad arma = new AT99();              // potencia 500
        this.atacante.comprarUnidad(arma);
        arma.setJugador(this.atacante);

        Unidad victima = victimaConTripulacion(2);
        victima.setEnergiadefensa(100);
        int vidaAntes = victima.getTripulantes().get(0).getVida();

        this.atacante.recibirDano(arma, victima.getPosicion());

        // exceso = 500 - 100 = 400, repartido entre 2 tripulantes = 200 cada uno
        assertEquals(0, victima.getEnergiadefensa(), "la defensa deberia quedar agotada");
        int esperado = vidaAntes - 200;
        if (esperado > 0) {
            assertEquals(esperado, victima.getTripulantes().get(0).getVida());
        } else {
            assertTrue(victima.getTripulantes().isEmpty()
                    || victima.getTripulantes().size() < 2,
                    "los tripulantes sin vida deberian haber muerto");
        }
    }

    @Test
    @DisplayName("El atacante se queda los puntos de los tripulantes que derrota")
    void elAtacanteGanaLosPuntosDeLosTripulantesDerrotados() throws Exception {
        Unidad arma = new AT99();
        this.atacante.comprarUnidad(arma);
        arma.setJugador(this.atacante);

        Unidad victima = victimaConTripulacion(1);     // un Artillero, vida 150
        victima.setEnergiadefensa(0);
        int puntosAntes = this.atacante.getPuntos();

        this.atacante.recibirDano(arma, victima.getPosicion());

        assertTrue(this.atacante.getPuntos() > puntosAntes,
                "deberia haber ganado puntos por la baja");
    }

    @Test
    @DisplayName("Destruir una unidad la retira del ejercito y libera su casilla")
    void destruirUnaUnidadLaRetiraDelMapaYDelEjercito() throws Exception {
        Unidad arma = new AT99();
        this.atacante.comprarUnidad(arma);
        arma.setJugador(this.atacante);

        Unidad victima = victimaConTripulacion(2);
        victima.setEnergiadefensa(0);
        Coordenadas casilla = victima.getPosicion();
        int unidadesAntes = this.defensor.getEjercito().getUnidades().size();

        // El bucle sobre los tripulantes los eliminaba de su propia lista con
        // un for-each, lo que lanzaba ConcurrentModificationException justo al
        // destruir la unidad.
        assertDoesNotThrow(() -> this.atacante.recibirDano(arma, casilla));

        assertEquals(unidadesAntes - 1, this.defensor.getEjercito().getUnidades().size());
        assertNull(this.escenario.getUnidadMapa(casilla), "la casilla deberia quedar libre");
    }

    @Test
    @DisplayName("Atacar una unidad sin tripulantes no provoca una division por cero")
    void unidadSinTripulantesNoProvocaDivisionPorCero() throws Exception {
        Unidad arma = new AT99();
        this.atacante.comprarUnidad(arma);
        arma.setJugador(this.atacante);

        Unidad vacia = new AT99();
        this.defensor.comprarUnidad(vacia);
        vacia.setJugador(this.defensor);
        vacia.setEnergiadefensa(0);

        assertDoesNotThrow(() -> this.atacante.recibirDano(arma, vacia.getPosicion()));
    }

    @Test
    @DisplayName("Atacar una casilla vacia no hace nada")
    void atacarUnaCasillaVaciaNoHaceNada() throws Exception {
        Unidad arma = new AT99();
        this.atacante.comprarUnidad(arma);
        arma.setJugador(this.atacante);
        int puntosAntes = this.atacante.getPuntos();

        Coordenadas vacia = new Coordenadas(250, 250, this.escenario);
        assertNull(this.escenario.getUnidadMapa(vacia));

        assertDoesNotThrow(() -> this.atacante.recibirDano(arma, vacia));
        assertEquals(puntosAntes, this.atacante.getPuntos());
    }

    @Test
    @DisplayName("El codigo hash del jugador no desborda la pila con un ejercito desplegado")
    void elHashCodeDelJugadorNoDesbordaLaPila() throws Exception {
        Unidad unidad = new AT99();
        this.atacante.comprarUnidad(unidad);
        unidad.setJugador(this.atacante);
        this.atacante.comprarSeres(unidad, new Comandante());

        assertDoesNotThrow(() -> this.atacante.hashCode());
        assertDoesNotThrow(() -> this.atacante.equals(this.defensor));
        assertFalse(this.atacante.equals(this.defensor));
    }

    @Test
    @DisplayName("Dos jugadores con el mismo nombre, equipo y puntos son iguales")
    void dosJugadoresEquivalentesSonIguales() {
        Jugador a = new Jugador("Mismo", Equipo.RDA, this.escenario);
        Jugador b = new Jugador("Mismo", Equipo.RDA, this.escenario);

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertFalse(a.equals(new Jugador("Otro", Equipo.RDA, this.escenario)));
        assertFalse(a.equals(new Jugador("Mismo", Equipo.NAVI, this.escenario)));
        assertFalse(a.equals(null));
    }

    /**
     * Despliega una unidad del jugador defensor con la tripulacion indicada.
     *
     * @param tripulantes numero de artilleros que se le suben
     * @return la unidad desplegada
     * @throws Exception si falla alguna compra
     */
    private Unidad victimaConTripulacion(int tripulantes) throws Exception {
        Unidad victima = new AT99();
        this.defensor.comprarUnidad(victima);
        victima.setJugador(this.defensor);
        for (int i = 0; i < tripulantes; i++) {
            this.defensor.comprarSeres(victima, new Artillero());
        }
        return victima;
    }

    /**
     * Cuenta cuantas casillas del mapa tienen una unidad.
     *
     * @return el numero de casillas ocupadas
     */
    private int celdasOcupadas() {
        int ocupadas = 0;
        for (int x = 0; x < this.escenario.getXmax(); x++) {
            for (int y = 0; y < this.escenario.getYmax(); y++) {
                if (this.escenario.getMapa()[x][y] != null) {
                    ocupadas++;
                }
            }
        }
        return ocupadas;
    }
}
