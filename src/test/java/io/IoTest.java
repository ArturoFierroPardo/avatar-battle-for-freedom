package io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import interfaz.centrodemandos.CentroDeMandos;
import interfaz.partida.Partida;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.jugador.Jugador;
import modelos.ser.humano.Comandante;
import modelos.unidad.Unidad;
import modelos.unidad.maquina.AT99;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas del guardado y la carga de partidas en disco.
 *
 * <p>
 * Trabajan sobre un fichero temporal, nunca sobre el fichero de partidas real.
 *
 * @author Arturo Fierro Pardo
 */
class IoTest {

    /**
     * Fichero temporal donde se guardan las partidas de la prueba.
     */
    private Path temporal;

    /**
     * Crea el fichero temporal antes de cada prueba.
     *
     * @throws IOException si no se puede crear
     */
    @BeforeEach
    void preparar() throws IOException {
        this.temporal = Files.createTempFile("partidas-test", ".dat");
        Files.delete(this.temporal);
    }

    /**
     * Borra el fichero temporal despues de cada prueba.
     *
     * @throws IOException si no se puede borrar
     */
    @AfterEach
    void limpiar() throws IOException {
        Files.deleteIfExists(this.temporal);
    }

    @Test
    @DisplayName("Sin fichero de partidas la carga devuelve una lista vacia")
    void laPrimeraEjecucionNoEncuentraPartidas() {
        // Que el fichero no exista es lo normal la primera vez que se juega, y
        // no debe tratarse como un error.
        Io io = new Io(this.temporal.toString());
        assertTrue(io.cargarArchivo().isEmpty());
    }

    @Test
    @DisplayName("Una partida guardada se recupera con sus jugadores y su turno")
    void unaPartidaGuardadaSeRecupera() throws Exception {
        Partida original = partidaDeEjemplo();

        ArrayList<Partida> aGuardar = new ArrayList<>();
        aGuardar.add(original);

        Io escritor = new Io(this.temporal.toString());
        escritor.guardarArchivo(aGuardar);
        assertTrue(Files.size(this.temporal) > 0, "el fichero deberia tener contenido");

        Io lector = new Io(this.temporal.toString());
        ArrayList<Partida> recuperadas = lector.cargarArchivo();

        assertEquals(1, recuperadas.size());
        Partida leida = recuperadas.get(0);
        assertEquals("Atacante", leida.getJugador1().getNombre());
        assertEquals("Defensor", leida.getJugador2().getNombre());
        assertEquals(Equipo.RDA, leida.getJugador1().getEquipo());
        assertEquals(original.getTurnos(), leida.getTurnos());
        assertEquals("Arbol Madre", leida.getEscenario().getNombre());
    }

    @Test
    @DisplayName("Se recupera el ejercito desplegado con su tripulacion")
    void seRecuperaElEjercitoDesplegado() throws Exception {
        Partida original = partidaDeEjemplo();
        Unidad unidad = new AT99();
        original.getJugador1().comprarUnidad(unidad);
        unidad.setJugador(original.getJugador1());
        original.getJugador1().comprarSeres(unidad, new Comandante());

        ArrayList<Partida> aGuardar = new ArrayList<>();
        aGuardar.add(original);
        new Io(this.temporal.toString()).guardarArchivo(aGuardar);

        Partida leida = new Io(this.temporal.toString()).cargarArchivo().get(0);
        Unidad recuperada = leida.getJugador1().getEjercito().getUnidades().get(0);

        assertEquals("AT-99 Escorpion", recuperada.getNombre());
        assertEquals(1, recuperada.getTripulantes().size());
        assertEquals("Comandante de SecOps", recuperada.getTripulantes().get(0).getNombre());
        assertEquals(unidad.getPosicion(), recuperada.getPosicion(),
                "la unidad deberia volver a su casilla");
    }

    @Test
    @DisplayName("Se recupera el registro del centro de mandos")
    void seRecuperaElRegistroDelCentroDeMandos() throws Exception {
        Escenario escenario = new ArbolMadre();
        Jugador jugador1 = new Jugador("Atacante", Equipo.RDA, escenario);
        Jugador jugador2 = new Jugador("Defensor", Equipo.NAVI, escenario);
        CentroDeMandos centro = new CentroDeMandos(jugador1);
        centro.anadirAccion("una accion de prueba");

        ArrayList<Partida> aGuardar = new ArrayList<>();
        aGuardar.add(new Partida(jugador1, jugador2, escenario, centro,
                new CentroDeMandos(jugador2)));
        new Io(this.temporal.toString()).guardarArchivo(aGuardar);

        Partida leida = new Io(this.temporal.toString()).cargarArchivo().get(0);
        assertTrue(leida.toString().contains("Atacante"), leida.toString());
    }

    @Test
    @DisplayName("Se pueden guardar y recuperar varias partidas a la vez")
    void seGuardanVariasPartidas() throws Exception {
        ArrayList<Partida> aGuardar = new ArrayList<>();
        aGuardar.add(partidaDeEjemplo());
        aGuardar.add(partidaDeEjemplo());
        aGuardar.add(partidaDeEjemplo());

        new Io(this.temporal.toString()).guardarArchivo(aGuardar);
        assertEquals(3, new Io(this.temporal.toString()).cargarArchivo().size());
    }

    @Test
    @DisplayName("Un fichero con contenido que no son partidas se detecta y no rompe el juego")
    void unFicheroCorruptoNoRompeElJuego() throws Exception {
        Files.write(this.temporal, "esto no es una partida serializada".getBytes());

        Io io = new Io(this.temporal.toString());
        assertTrue(io.cargarArchivo().isEmpty(),
                "ante un fichero ilegible deberia devolver una lista vacia");
    }

    @Test
    @DisplayName("guardarPartida acumula partidas en la lista en memoria")
    void guardarPartidaAcumulaEnMemoria() {
        Io io = new Io(this.temporal.toString());
        assertTrue(io.getPartidas().isEmpty());

        io.guardarPartida(partidaDeEjemplo());
        assertEquals(1, io.getPartidas().size());
        assertFalse(new File(this.temporal.toString()).exists(),
                "guardarPartida no deberia escribir en disco por si solo");
    }

    /**
     * Crea una partida sencilla entre dos jugadores en el Arbol Madre.
     *
     * @return la partida recien creada
     */
    private Partida partidaDeEjemplo() {
        Escenario escenario = new ArbolMadre();
        Jugador jugador1 = new Jugador("Atacante", Equipo.RDA, escenario);
        Jugador jugador2 = new Jugador("Defensor", Equipo.NAVI, escenario);
        return new Partida(jugador1, jugador2, escenario,
                new CentroDeMandos(jugador1), new CentroDeMandos(jugador2));
    }
}
