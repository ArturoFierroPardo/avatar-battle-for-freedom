package interfaz.partida;

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
 * Pruebas del bucle de turnos: alternancia entre jugadores, final por victoria,
 * empate y guardado.
 *
 * <p>
 * Cada prueba juega una partida entera suministrando por adelantado las
 * pulsaciones de los dos jugadores con {@link utils.Utils#usarEntrada}.
 *
 * @author Arturo Fierro Pardo
 */
class PartidaTest {

    /**
     * Escenario de la partida de prueba.
     */
    private Escenario escenario;

    /**
     * Jugador 1, que abre los turnos impares.
     */
    private Jugador jugador1;

    /**
     * Jugador 2, que juega los turnos pares.
     */
    private Jugador jugador2;

    /**
     * Partida bajo prueba.
     */
    private Partida partida;

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
        this.jugador1 = new Jugador("Arturo", Equipo.RDA, this.escenario);
        this.jugador2 = new Jugador("Quaritch", Equipo.NAVI, this.escenario);
        this.partida = new Partida(this.jugador1, this.jugador2, this.escenario,
                new CentroDeMandos(this.jugador1), new CentroDeMandos(this.jugador2));
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
    @DisplayName("La partida arranca en el turno 1 y sin terminar")
    void laPartidaArrancaEnElTurnoUno() {
        assertEquals(1, this.partida.getTurnos());
        assertFalse(this.partida.getTerminada());
    }

    @Test
    @DisplayName("La presentacion dice el escenario y quien lucha contra quien")
    void laPresentacionDiceQuienLucha() {
        String texto = this.partida.toString();
        assertTrue(texto.contains("Arbol Madre"), texto);
        assertTrue(texto.contains("Arturo"), texto);
        assertTrue(texto.contains("Quaritch"), texto);
        assertTrue(texto.contains("RDA"), texto);
        assertTrue(texto.contains("NAVI"), texto);
    }

    @Test
    @DisplayName("Los turnos alternan entre los dos jugadores")
    void losTurnosAlternanEntreLosJugadores() throws FueradeMapaException {
        // Cuatro turnos pasando sin hacer nada: 1 y 3 son de Arturo, 2 y 4 de
        // Quaritch. Al cerrarse el cuarto los dos ejercitos siguen vacios y la
        // partida acaba en empate.
        String pantalla = jugar("5\n5\n5\n5\n");

        assertTrue(pantalla.contains("TURNO---------->  1"), pantalla);
        assertTrue(pantalla.contains("TURNO---------->  2"), pantalla);
        assertTrue(pantalla.contains("TURNO---------->  3"), pantalla);
        assertTrue(pantalla.contains("TURNO---------->  4"), pantalla);
        assertTrue(pantalla.contains("Turno de Arturo"), pantalla);
        assertTrue(pantalla.contains("Turno de Quaritch"), pantalla);
    }

    @Test
    @DisplayName("Si ninguno despliega nada, la partida acaba en empate")
    void sinEjercitosLaPartidaAcabaEnEmpate() throws FueradeMapaException {
        String pantalla = jugar("5\n5\n5\n5\n");

        assertTrue(pantalla.contains("EMPATE"), pantalla);
        assertTrue(this.partida.getTerminada());
        assertEquals(5, this.partida.getTurnos());
    }

    @Test
    @DisplayName("Gana quien conserva ejercito cuando el rival se queda sin nada")
    void ganaElQueConservaEjercito() throws Exception {
        Unidad unidad = new MK6();
        this.jugador1.comprarUnidad(unidad);
        unidad.setJugador(this.jugador1);

        String pantalla = jugar("5\n5\n5\n5\n");

        assertTrue(pantalla.contains("EL GANADOR DE LA PARTIDA ES Arturo"), pantalla);
        assertFalse(pantalla.contains("EMPATE"), "no deberia declarar empate");
        assertTrue(this.partida.getTerminada());
    }

    @Test
    @DisplayName("Gana el jugador 2 si el que se queda sin ejercito es el 1")
    void tambienPuedeGanarElSegundoJugador() throws Exception {
        Unidad unidad = new MK6();
        this.jugador2.comprarUnidad(unidad);
        unidad.setJugador(this.jugador2);

        String pantalla = jugar("5\n5\n5\n5\n");

        assertTrue(pantalla.contains("EL GANADOR DE LA PARTIDA ES Quaritch"), pantalla);
    }

    @Test
    @DisplayName("Con los dos ejercitos en pie la partida sigue, no se declara ganador")
    void conLosDosEjercitosEnPieLaPartidaSigue() throws Exception {
        Unidad unaRda = new MK6();
        this.jugador1.comprarUnidad(unaRda);
        unaRda.setJugador(this.jugador1);
        Unidad unaNavi = new MK6();
        this.jugador2.comprarUnidad(unaNavi);
        unaNavi.setJugador(this.jugador2);

        // Se pasan seis turnos y despues el jugador 1 guarda para poder salir:
        // sin esa salida la partida no terminaria nunca.
        String pantalla = jugar("5\n5\n5\n5\n5\n5\n6\n");

        assertFalse(pantalla.contains("EL GANADOR"), "no deberia haber ganador todavia");
        assertFalse(pantalla.contains("EMPATE"), "no deberia declarar empate");
        assertTrue(pantalla.contains("LA PARTIDA SE HA GUARDADO Y FINALIZADO"), pantalla);
    }

    @Test
    @DisplayName("Guardar y salir termina la partida sin declarar ganador")
    void guardarYSalirTerminaLaPartida() throws FueradeMapaException {
        String pantalla = jugar("6\n");

        assertTrue(pantalla.contains("LA PARTIDA SE HA GUARDADO Y FINALIZADO"), pantalla);
        assertFalse(pantalla.contains("EL GANADOR"), "salir no es ganar");
        assertFalse(this.partida.getTerminada(),
                "una partida guardada queda pendiente, no terminada");
    }

    @Test
    @DisplayName("Al guardar se retrocede el turno para poder retomarla donde iba")
    void alGuardarSeRetrocedeElTurno() throws FueradeMapaException {
        jugar("6\n");
        assertEquals(1, this.partida.getTurnos(),
                "deberia quedar guardada en el turno que se estaba jugando");
    }

    @Test
    @DisplayName("Tambien puede guardar y salir el segundo jugador")
    void elSegundoJugadorTambienPuedeGuardar() throws FueradeMapaException {
        String pantalla = jugar("5\n6\n");

        assertTrue(pantalla.contains("LA PARTIDA SE HA GUARDADO Y FINALIZADO"), pantalla);
        assertEquals(2, this.partida.getTurnos());
    }

    @Test
    @DisplayName("Una partida jugada entera se puede comparar consigo misma")
    void unaPartidaEsIgualASiMisma() {
        assertEquals(this.partida, this.partida);
        assertFalse(this.partida.equals(null));
        assertFalse(this.partida.equals("no soy una partida"));
    }

    /**
     * Juega la partida entera con las pulsaciones indicadas y devuelve todo lo
     * que se ha mostrado por pantalla.
     *
     * @param pulsaciones lineas que teclearian los dos jugadores, en orden
     * @return la salida que ha producido la partida
     * @throws FueradeMapaException si alguna accion sale del mapa
     */
    private String jugar(String pulsaciones) throws FueradeMapaException {
        Utils.usarEntrada(new StringReader(pulsaciones));
        ByteArrayOutputStream capturada = new ByteArrayOutputStream();
        System.setOut(new PrintStream(capturada, true, StandardCharsets.UTF_8));
        try {
            this.partida.comienzaPartida();
        } finally {
            System.setOut(this.salidaOriginal);
        }
        return capturada.toString(StandardCharsets.UTF_8);
    }
}
