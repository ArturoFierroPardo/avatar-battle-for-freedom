package modelos.coordenadas;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolAlmas;
import modelos.escenario.Escenario;
import modelos.escenario.Jardines;
import modelos.excepciones.FueradeMapaException;
import modelos.jugador.Jugador;
import modelos.unidad.Unidad;
import modelos.unidad.maquina.AT99;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de las coordenadas del mapa: limites, contrato de igualdad y
 * desplazamientos.
 *
 * @author Arturo Fierro Pardo
 */
class CoordenadasTest {

    /**
     * Escenario de 100x100 sobre el que se comprueban los limites.
     */
    private Escenario escenario;

    /**
     * Prepara un escenario limpio antes de cada prueba.
     */
    @BeforeEach
    void preparar() {
        this.escenario = new ArbolAlmas();
    }

    @Test
    @DisplayName("Acepta la primera y la ultima casilla validas del mapa")
    void aceptaLasCasillasDeLosExtremos() throws FueradeMapaException {
        Coordenadas origen = new Coordenadas(0, 0, this.escenario);
        assertEquals(0, origen.getX());
        assertEquals(0, origen.getY());

        int ultima = this.escenario.getXmax() - 1;
        Coordenadas esquina = new Coordenadas(ultima, ultima, this.escenario);
        assertEquals(ultima, esquina.getX());
        assertEquals(ultima, esquina.getY());
    }

    @Test
    @DisplayName("Rechaza una coordenada igual al tamano del mapa, que no es un indice valido")
    void rechazaElLimiteSuperior() {
        // El mapa es new Unidad[xmax][ymax], luego el ultimo indice valido es
        // xmax - 1. Aceptar xmax provocaba un ArrayIndexOutOfBoundsException
        // al colocar la unidad.
        assertThrows(FueradeMapaException.class,
                () -> new Coordenadas(this.escenario.getXmax(), 0, this.escenario));
        assertThrows(FueradeMapaException.class,
                () -> new Coordenadas(0, this.escenario.getYmax(), this.escenario));
    }

    @Test
    @DisplayName("Rechaza coordenadas negativas y muy por encima del mapa")
    void rechazaCoordenadasFueraDeRango() {
        assertThrows(FueradeMapaException.class, () -> new Coordenadas(-1, 0, this.escenario));
        assertThrows(FueradeMapaException.class, () -> new Coordenadas(0, -1, this.escenario));
        assertThrows(FueradeMapaException.class, () -> new Coordenadas(5000, 5000, this.escenario));
    }

    @Test
    @DisplayName("El constructor aleatorio nunca sale del mapa")
    void elConstructorAleatorioSeQuedaDentroDelMapa() throws FueradeMapaException {
        for (int i = 0; i < 2000; i++) {
            Coordenadas c = new Coordenadas(this.escenario);
            assertTrue(c.getX() >= 0 && c.getX() < this.escenario.getXmax(),
                    "x fuera de rango: " + c.getX());
            assertTrue(c.getY() >= 0 && c.getY() < this.escenario.getYmax(),
                    "y fuera de rango: " + c.getY());
        }
    }

    @Test
    @DisplayName("Un setX rechazado no deja la coordenada modificada")
    void unSetRechazadoNoModificaLaCoordenada() throws FueradeMapaException {
        Coordenadas c = new Coordenadas(10, 20, this.escenario);

        assertThrows(FueradeMapaException.class, () -> c.setX(this.escenario.getXmax()));
        assertEquals(10, c.getX(), "la x no deberia haber cambiado");

        assertThrows(FueradeMapaException.class, () -> c.setY(-3));
        assertEquals(20, c.getY(), "la y no deberia haber cambiado");
    }

    @Test
    @DisplayName("Dos coordenadas con la misma x e y son iguales y comparten codigo hash")
    void cumpleElContratoEntreEqualsYHashCode() throws FueradeMapaException {
        Coordenadas a = new Coordenadas(5, 7, this.escenario);
        // Mismo par de coordenadas pero en otro escenario: equals solo mira x e
        // y, asi que el codigo hash tampoco puede depender del escenario.
        Coordenadas b = new Coordenadas(5, 7, new Jardines());

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode(),
                "equals y hashCode tienen que mirar los mismos campos");

        Set<Coordenadas> conjunto = new HashSet<>();
        conjunto.add(a);
        assertTrue(conjunto.contains(b), "deberia encontrarse dentro de un HashSet");
    }

    @Test
    @DisplayName("Coordenadas distintas no son iguales")
    void coordenadasDistintasNoSonIguales() throws FueradeMapaException {
        Coordenadas a = new Coordenadas(5, 7, this.escenario);
        assertNotEqualsSimetrico(a, new Coordenadas(5, 8, this.escenario));
        assertNotEqualsSimetrico(a, new Coordenadas(6, 7, this.escenario));
        assertFalse(a.equals(null));
        assertFalse(a.equals("no soy una coordenada"));
    }

    @Test
    @DisplayName("desplazada() recorta el destino a los limites del mapa")
    void desplazadaRecortaALosLimitesDelMapa() throws Exception {
        Unidad unidad = unidadDesplegada();
        int ultimaX = this.escenario.getXmax() - 1;
        int ultimaY = this.escenario.getYmax() - 1;

        Coordenadas lejos = Coordenadas.desplazada(unidad, 10_000, 10_000, this.escenario);
        assertEquals(ultimaX, lejos.getX());
        assertEquals(ultimaY, lejos.getY());

        Coordenadas atras = Coordenadas.desplazada(unidad, -10_000, -10_000, this.escenario);
        assertEquals(0, atras.getX());
        assertEquals(0, atras.getY());
    }

    @Test
    @DisplayName("desplazada() suma el desplazamiento a la posicion de la unidad")
    void desplazadaSumaElDesplazamiento() throws Exception {
        Unidad unidad = unidadDesplegada();
        Coordenadas destino = Coordenadas.desplazada(unidad, 0, 0, this.escenario);
        assertEquals(unidad.getPosicion().getX(), destino.getX());
        assertEquals(unidad.getPosicion().getY(), destino.getY());
    }

    @Test
    @DisplayName("toString muestra el par de coordenadas")
    void toStringMuestraElParDeCoordenadas() throws FueradeMapaException {
        assertEquals("(3, 9)", new Coordenadas(3, 9, this.escenario).toString());
    }

    /**
     * Comprueba que dos coordenadas no son iguales en los dos sentidos.
     *
     * @param a primera coordenada
     * @param b segunda coordenada
     */
    private void assertNotEqualsSimetrico(Coordenadas a, Coordenadas b) {
        assertFalse(a.equals(b), a + " no deberia ser igual a " + b);
        assertFalse(b.equals(a), "equals tiene que ser simetrico");
    }

    /**
     * Crea un jugador con puntos de sobra y le despliega una unidad en el
     * escenario de la prueba.
     *
     * @return la unidad desplegada
     * @throws Exception si falla la compra
     */
    private Unidad unidadDesplegada() throws Exception {
        Jugador jugador = new Jugador("Prueba", Equipo.RDA, this.escenario);
        jugador.setPuntos(100_000);
        Unidad unidad = new AT99();
        jugador.comprarUnidad(unidad);
        unidad.setJugador(jugador);
        return unidad;
    }
}
