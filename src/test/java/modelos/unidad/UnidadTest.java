package modelos.unidad;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolAlmas;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.jugador.Jugador;
import modelos.ser.humano.Comandante;
import modelos.unidad.bestia.Banshee;
import modelos.unidad.bestia.Thanator;
import modelos.unidad.bestia.Titanothere;
import modelos.unidad.maquina.AT99;
import modelos.unidad.maquina.Aerospatiale;
import modelos.unidad.maquina.MK6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de las unidades: caracteristicas de cada modelo, consumo de energia,
 * movimiento y contrato de igualdad.
 *
 * @author Arturo Fierro Pardo
 */
class UnidadTest {

    @Test
    @DisplayName("Cada unidad conserva sus caracteristicas de ficha")
    void cadaUnidadConservaSusCaracteristicas() {
        Unidad at99 = new AT99();
        assertEquals("AT-99 Escorpion", at99.getNombre());
        assertEquals(15000, at99.getCoste());
        assertEquals(7500, at99.getEnergiadefensa());
        assertEquals(500, at99.getPotencia());
        assertEquals(10, at99.getRangoataque());
        assertEquals(4, at99.getSeresneed());

        Unidad titanothere = new Titanothere();
        assertEquals(1000, titanothere.getCoste());
        assertEquals(11000, titanothere.getEnergiadefensa(),
                "el Titanothere es la unidad mas resistente");
        assertEquals(1, titanothere.getSeresneed());
    }

    @Test
    @DisplayName("Una unidad recien comprada no esta desplegada ni tripulada")
    void unaUnidadRecienComprada() {
        Unidad unidad = new AT99();
        assertTrue(unidad.getTripulantes().isEmpty());
        assertTrue(unidad.toStringInfo().contains("Unidad no desplegada"),
                "la ficha deberia avisar de que no esta en el mapa");
    }

    @Test
    @DisplayName("Consultar el alcance de la Banshee no lanza excepcion")
    void elAlcanceDeLaBansheeNoLanzaExcepcion() {
        // maxmov era un String con el texto "Casilla aleatoria" y el getter le
        // aplicaba Integer.parseInt, asi que esto lanzaba
        // NumberFormatException.
        Unidad banshee = new Banshee();
        assertDoesNotThrow(() -> banshee.getMaxmov());
        assertTrue(banshee.tieneMovimientoAleatorio());
        assertEquals("Casilla aleatoria", banshee.getMaxmovDescripcion());
        assertTrue(banshee.toStringInfo().contains("Casilla aleatoria"),
                "la ficha deberia mostrar el texto y no un numero interno");
    }

    @Test
    @DisplayName("Las unidades con alcance limitado devuelven su numero de casillas")
    void lasUnidadesNormalesTienenAlcanceNumerico() {
        assertEquals(20, new AT99().getMaxmov());
        assertEquals(10, new Aerospatiale().getMaxmov());
        assertEquals(2, new MK6().getMaxmov());
        assertEquals(50, new Thanator().getMaxmov());
        assertEquals(2, new Titanothere().getMaxmov());

        assertFalse(new AT99().tieneMovimientoAleatorio());
        assertEquals("20", new AT99().getMaxmovDescripcion());
    }

    @Test
    @DisplayName("Atacar consume energia de ataque, y se agota con un aviso")
    void atacarConsumeEnergiaHastaAgotarla() throws SinEnergiaAtaqueException {
        Unidad unidad = new AT99();
        int energiaInicial = unidad.getEnergiataque();

        unidad.restarEnergiaAtaque();
        assertEquals(energiaInicial - unidad.getPotencia(), unidad.getEnergiataque());

        unidad.setEnergiataque(unidad.getPotencia() - 1);
        assertThrows(SinEnergiaAtaqueException.class, () -> unidad.restarEnergiaAtaque());
    }

    @Test
    @DisplayName("Mover una unidad muchas veces no la saca del mapa ni la duplica")
    void moverMuchasVecesNoSacaLaUnidadDelMapa() throws Exception {
        Escenario escenario = new ArbolAlmas();
        Jugador jugador = new Jugador("Prueba", Equipo.RDA, escenario);
        jugador.setPuntos(100_000);
        Unidad unidad = new AT99();
        jugador.comprarUnidad(unidad);
        unidad.setJugador(jugador);

        for (int i = 0; i < 1000; i++) {
            unidad.moverUnidad(escenario);
            assertTrue(unidad.getPosicion().getX() >= 0
                    && unidad.getPosicion().getX() < escenario.getXmax(),
                    "x fuera del mapa: " + unidad.getPosicion());
            assertTrue(unidad.getPosicion().getY() >= 0
                    && unidad.getPosicion().getY() < escenario.getYmax(),
                    "y fuera del mapa: " + unidad.getPosicion());
        }

        int ocupadas = 0;
        for (int x = 0; x < escenario.getXmax(); x++) {
            for (int y = 0; y < escenario.getYmax(); y++) {
                if (escenario.getMapa()[x][y] != null) {
                    ocupadas++;
                }
            }
        }
        assertEquals(1, ocupadas, "la unidad deberia dejar libre la casilla de la que sale");
    }

    @Test
    @DisplayName("La Banshee salta por todo el mapa sin dejar copias detras")
    void laBansheeSaltaSinDejarCopias() throws Exception {
        Escenario escenario = new ArbolAlmas();
        Jugador jugador = new Jugador("Prueba", Equipo.NAVI, escenario);
        jugador.setPuntos(100_000);
        Unidad banshee = new Banshee();
        jugador.comprarUnidad(banshee);
        banshee.setJugador(jugador);

        for (int i = 0; i < 500; i++) {
            banshee.moverUnidad(escenario);
        }

        int ocupadas = 0;
        for (int x = 0; x < escenario.getXmax(); x++) {
            for (int y = 0; y < escenario.getYmax(); y++) {
                if (escenario.getMapa()[x][y] != null) {
                    ocupadas++;
                }
            }
        }
        assertEquals(1, ocupadas);
    }

    @Test
    @DisplayName("El comparador ordena las unidades de mas a menos energia de defensa")
    void elComparadorOrdenaDeMasAMenosDefensa() {
        List<Unidad> unidades = new ArrayList<>();
        unidades.add(new MK6());          // defensa 0
        unidades.add(new Titanothere());  // defensa 11000
        unidades.add(new AT99());         // defensa 7500

        Collections.sort(unidades, new Unidad.comparadorEDEF());

        assertEquals(11000, unidades.get(0).getEnergiadefensa());
        assertEquals(7500, unidades.get(1).getEnergiadefensa());
        assertEquals(0, unidades.get(2).getEnergiadefensa());
    }

    @Test
    @DisplayName("Dos unidades del mismo modelo recien creadas son iguales")
    void dosUnidadesDelMismoModeloSonIguales() {
        Unidad a = new AT99();
        Unidad b = new AT99();

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertEquals(a, a, "equals tiene que ser reflexivo");
        assertEquals(a.equals(b), b.equals(a), "equals tiene que ser simetrico");

        assertFalse(a.equals(new MK6()));
        assertFalse(a.equals(null));
        assertFalse(a.equals("no soy una unidad"));
    }

    @Test
    @DisplayName("El codigo hash de una unidad con dueno no desborda la pila")
    void elHashCodeDeUnaUnidadConDuenoNoDesbordaLaPila() throws Exception {
        // unidad -> jugador -> ejercito -> unidad era un ciclo que hacia que
        // hashCode() y equals() se llamasen a si mismos indefinidamente.
        Escenario escenario = new ArbolMadre();
        Jugador jugador = new Jugador("Prueba", Equipo.RDA, escenario);
        Unidad unidad = new AT99();
        jugador.comprarUnidad(unidad);
        unidad.setJugador(jugador);
        jugador.comprarSeres(unidad, new Comandante());

        assertDoesNotThrow(() -> unidad.hashCode());
        assertDoesNotThrow(() -> unidad.equals(new AT99()));

        Set<Unidad> conjunto = new HashSet<>();
        conjunto.add(unidad);
        assertTrue(conjunto.contains(unidad),
                "una unidad deberia poder guardarse y recuperarse de un HashSet");
    }

    @Test
    @DisplayName("Una unidad cambia al cambiar su tripulacion")
    void unaUnidadCambiaAlCambiarSuTripulacion() throws Exception {
        Escenario escenario = new ArbolMadre();
        Jugador jugador = new Jugador("Prueba", Equipo.RDA, escenario);
        Unidad unidad = new AT99();
        jugador.comprarUnidad(unidad);
        jugador.comprarSeres(unidad, new Comandante());

        assertFalse(unidad.equals(new AT99()),
                "una unidad tripulada no deberia ser igual a una sin tripular");
    }

    @Test
    @DisplayName("toString identifica la unidad y su tripulacion")
    void toStringIdentificaLaUnidadYSuTripulacion() throws Exception {
        Escenario escenario = new ArbolMadre();
        Jugador jugador = new Jugador("Prueba", Equipo.RDA, escenario);
        Unidad unidad = new AT99();
        jugador.comprarUnidad(unidad);
        jugador.comprarSeres(unidad, new Comandante());

        String texto = unidad.toString();
        assertTrue(texto.contains("AT-99 Escorpion"), texto);
        assertTrue(texto.contains("Comandante de SecOps"), texto);
    }
}
