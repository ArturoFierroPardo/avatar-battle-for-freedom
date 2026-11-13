package interfaz.menu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import interfaz.centrodemandos.CentroDeMandos;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.jugador.Jugador;
import modelos.unidad.Unidad;
import modelos.unidad.maquina.MK6;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.Utils;

/**
 * Pruebas del menu de un turno, ejecutandolo con partidas guionizadas.
 *
 * <p>
 * El menu lee del teclado y escribe por pantalla, asi que cada prueba le
 * suministra las pulsaciones por adelantado con
 * {@link utils.Utils#usarEntrada} y captura la salida para comprobar que el
 * jugador ve lo que debe.
 *
 * @author Arturo Fierro Pardo
 */
class MenuTest {

    /**
     * Escenario de la partida de prueba.
     */
    private Escenario escenario;

    /**
     * Jugador que tiene el turno.
     */
    private Jugador jugador;

    /**
     * Rival, que no juega pero recibe los avisos en su centro de mandos.
     */
    private Jugador rival;

    /**
     * Centro de mandos del jugador que juega.
     */
    private CentroDeMandos centroPropio;

    /**
     * Centro de mandos del rival.
     */
    private CentroDeMandos centroRival;

    /**
     * Menu bajo prueba.
     */
    private Menu menu;

    /**
     * Salida estandar original, para restaurarla al acabar.
     */
    private PrintStream salidaOriginal;

    /**
     * Prepara una partida nueva antes de cada prueba.
     */
    @BeforeEach
    void preparar() {
        this.escenario = new ArbolMadre();
        this.jugador = new Jugador("Arturo", Equipo.RDA, this.escenario);
        this.rival = new Jugador("Quaritch", Equipo.NAVI, this.escenario);
        this.centroPropio = new CentroDeMandos(this.jugador);
        this.centroRival = new CentroDeMandos(this.rival);
        this.menu = new Menu(this.jugador, this.escenario, this.centroPropio, this.centroRival);
        this.salidaOriginal = System.out;
    }

    /**
     * Devuelve la entrada y la salida estandar a su estado normal.
     */
    @AfterEach
    void limpiar() {
        System.setOut(this.salidaOriginal);
        Utils.usarEntrada(null);
    }

    @Test
    @DisplayName("El menu lista las seis acciones del turno")
    void elMenuListaLasSeisAcciones() throws FueradeMapaException {
        String pantalla = jugarTurno("5\n");

        assertTrue(pantalla.contains("1) Comprar una unidad"), pantalla);
        assertTrue(pantalla.contains("2) Mover una unidad"), pantalla);
        assertTrue(pantalla.contains("3) Atacar a una unidad enemiga"), pantalla);
        assertTrue(pantalla.contains("4) Ver el centro de mandos"), pantalla);
        assertTrue(pantalla.contains("5) Finalizar el turno"), pantalla);
        assertTrue(pantalla.contains("6) Finalizar y guardar partida"), pantalla);
        assertTrue(pantalla.contains("Turno de Arturo"), pantalla);
    }

    @Test
    @DisplayName("Finalizar el turno no cambia nada de la partida")
    void finalizarElTurnoNoCambiaNada() throws FueradeMapaException {
        int puntosAntes = this.jugador.getPuntos();

        jugarTurno("5\n");

        assertEquals(puntosAntes, this.jugador.getPuntos());
        assertTrue(this.jugador.getEjercito().getUnidades().isEmpty());
        assertFalse(this.jugador.getAcabarPartida());
    }

    @Test
    @DisplayName("Una opcion que no existe se rechaza y se vuelve a preguntar")
    void unaOpcionInvalidaSeRechaza() throws FueradeMapaException {
        String pantalla = jugarTurno("99\n5\n");

        assertTrue(pantalla.contains("OPCION INVALIDA"), pantalla);
        // Tras el aviso tiene que haber vuelto a pedir la opcion, y el "5"
        // posterior termina el turno con normalidad.
        assertFalse(this.jugador.getAcabarPartida());
    }

    @Test
    @DisplayName("Guardar y salir marca la partida como terminada por el jugador")
    void guardarYSalirMarcaElFinal() throws FueradeMapaException {
        jugarTurno("6\n");
        assertTrue(this.jugador.getAcabarPartida(),
                "el jugador deberia quedar marcado como que quiere acabar");
    }

    @Test
    @DisplayName("Comprar una unidad la despliega, cobra el coste y la tripula")
    void comprarUnaUnidadPorElMenu() throws FueradeMapaException {
        int puntosAntes = this.jugador.getPuntos();

        // 1 comprar · 1 unidad · opcion 1 (AT-99, necesita 4 tripulantes)
        // · cuatro veces la opcion 1 del mercado de seres
        jugarTurno("1\n1\n1\n1\n1\n1\n1\n");

        assertEquals(1, this.jugador.getEjercito().getUnidades().size());
        Unidad comprada = this.jugador.getEjercito().getUnidades().get(0);
        assertEquals("AT-99 Escorpion", comprada.getNombre());
        assertEquals(4, comprada.getTripulantes().size(), "deberia ir tripulada");
        assertTrue(this.jugador.getPuntos() < puntosAntes - comprada.getCoste(),
                "ademas de la unidad se han pagado los tripulantes");
        assertEquals(this.jugador, comprada.getJugador());
    }

    @Test
    @DisplayName("Elegir no comprar nada deja el ejercito como estaba")
    void sePuedeSalirDelMercadoSinComprar() throws FueradeMapaException {
        int puntosAntes = this.jugador.getPuntos();

        // 1 comprar · 1 unidad · opcion 4 = "No comprar nada"
        String pantalla = jugarTurno("1\n1\n4\n");

        assertTrue(this.jugador.getEjercito().getUnidades().isEmpty());
        assertEquals(puntosAntes, this.jugador.getPuntos());
        assertTrue(pantalla.contains("no ha comprado ninguna unidad"), pantalla);
    }

    @Test
    @DisplayName("El mercado muestra solo las unidades del bando que juega")
    void elMercadoMuestraSoloElCatalogoDelBando() throws FueradeMapaException {
        String pantalla = jugarTurno("1\n1\n4\n");

        assertTrue(pantalla.contains("AT-99 Escorpion"), "falta una unidad de la RDA");
        assertTrue(pantalla.contains("Mitsubishi MK-6"), "falta una unidad de la RDA");
        assertFalse(pantalla.contains("Thanator"), "no deberia ofrecer unidades Navi");
        assertFalse(pantalla.contains("Titanothere"), "no deberia ofrecer unidades Navi");
    }

    @Test
    @DisplayName("Sin puntos suficientes se avisa y no se compra")
    void sinPuntosSuficientesSeAvisa() throws FueradeMapaException {
        this.jugador.setPuntos(10);

        // Intenta el AT-99 (15000), se le avisa, y entonces sale sin comprar
        String pantalla = jugarTurno("1\n1\n1\n4\n");

        assertTrue(pantalla.contains("NO TIENE SUFICIENTES PUNTOS"), pantalla);
        assertTrue(this.jugador.getEjercito().getUnidades().isEmpty());
        assertEquals(10, this.jugador.getPuntos());
    }

    @Test
    @DisplayName("Mover sin tener ejercito avisa en vez de romperse")
    void moverSinEjercitoAvisa() throws FueradeMapaException {
        String pantalla = jugarTurno("2\n5\n");
        assertTrue(pantalla.contains("NO TIENE NINGUNA UNIDAD")
                || pantalla.contains("OPCION INVALIDA")
                || pantalla.contains("sin exito"),
                "deberia avisar de que no hay nada que mover: " + pantalla);
    }

    @Test
    @DisplayName("La compra queda anotada en los dos centros de mandos")
    void laCompraSeAnotaEnLosDosCentrosDeMandos() throws FueradeMapaException {
        jugarTurno("1\n1\n1\n1\n1\n1\n1\n");

        assertEquals(1, this.centroPropio.getAcciones().size());
        assertEquals(1, this.centroRival.getAcciones().size());
        assertTrue(this.centroPropio.getAcciones().get(0).contains("ha comprado"),
                this.centroPropio.getAcciones().get(0));
        assertTrue(this.centroRival.getAcciones().get(0).contains("ha comprado"),
                this.centroRival.getAcciones().get(0));
    }

    @Test
    @DisplayName("El rival se entera de la compra pero no de donde se ha desplegado")
    void elRivalNoVeLaPosicionDeLaUnidadComprada() throws FueradeMapaException {
        jugarTurno("1\n1\n1\n1\n1\n1\n1\n");

        String propia = this.centroPropio.getAcciones().get(0);
        String ajena = this.centroRival.getAcciones().get(0);

        assertTrue(propia.contains("en la posicion"),
                "el dueno si debe ver donde ha desplegado: " + propia);
        assertFalse(ajena.contains("en la posicion"),
                "el rival no debe ver la posicion: " + ajena);
    }

    @Test
    @DisplayName("Un intento de compra fallido tambien queda registrado")
    void elIntentoFallidoQuedaRegistrado() throws FueradeMapaException {
        jugarTurno("1\n1\n4\n");

        assertEquals(1, this.centroPropio.getAcciones().size());
        assertTrue(this.centroPropio.getAcciones().get(0).contains("sin exito"),
                this.centroPropio.getAcciones().get(0));
    }

    @Test
    @DisplayName("El centro de mandos muestra el ejercito del jugador")
    void elCentroDeMandosMuestraElEjercito() throws Exception {
        Unidad unidad = new MK6();
        this.jugador.comprarUnidad(unidad);
        unidad.setJugador(this.jugador);

        // 4 centro de mandos · 2 informacion del ejercito · 0 volver
        // · 4 volver al menu · 5 finalizar turno
        String pantalla = jugarTurno("4\n2\n0\n4\n5\n");

        assertTrue(pantalla.contains("CENTRO DE MANDOS"), pantalla);
        assertTrue(pantalla.contains("Mitsubishi MK-6"), "deberia listar su unidad");
    }

    @Test
    @DisplayName("El centro de mandos avisa cuando todavia no ha pasado nada")
    void elCentroDeMandosAvisaSiNoHaPasadoNada() throws FueradeMapaException {
        // 4 centro de mandos · 1 sucesos · 4 volver · 5 finalizar turno
        String pantalla = jugarTurno("4\n1\n4\n5\n");
        assertTrue(pantalla.contains("No ha sucedido nada en la partida"), pantalla);
    }

    /**
     * Ejecuta un turno completo del menu con las pulsaciones indicadas y
     * devuelve todo lo que se ha mostrado por pantalla.
     *
     * @param pulsaciones lineas que teclearia el jugador, separadas por saltos
     * de linea
     * @return la salida que ha producido el turno
     * @throws FueradeMapaException si alguna accion sale del mapa
     */
    private String jugarTurno(String pulsaciones) throws FueradeMapaException {
        Utils.usarEntrada(new StringReader(pulsaciones));
        ByteArrayOutputStream capturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturada, true, StandardCharsets.UTF_8));
        try {
            this.menu.mostrarMenu();
        } finally {
            System.setOut(this.salidaOriginal);
        }
        return capturada.toString(StandardCharsets.UTF_8);
    }
}
