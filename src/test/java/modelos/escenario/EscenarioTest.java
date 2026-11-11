package modelos.escenario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import modelos.coordenadas.Coordenadas;
import modelos.excepciones.FueradeMapaException;
import modelos.unidad.Unidad;
import modelos.unidad.maquina.AT99;
import modelos.unidad.maquina.MK6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de los escenarios: dimensiones, puntos iniciales y gestion del mapa
 * de unidades.
 *
 * @author Arturo Fierro Pardo
 */
class EscenarioTest {

    @Test
    @DisplayName("Cada escenario tiene sus dimensiones y puntos iniciales")
    void cadaEscenarioTieneSusDatos() {
        Escenario arbolAlmas = new ArbolAlmas();
        assertEquals("Arbol de las Almas", arbolAlmas.getNombre());
        assertEquals(5000, arbolAlmas.getPuntoscomienzo());
        assertEquals(100, arbolAlmas.getXmax());
        assertEquals(100, arbolAlmas.getYmax());

        Escenario jardines = new Jardines();
        assertEquals("Jardines Colgantes", jardines.getNombre());
        assertEquals(20000, jardines.getPuntoscomienzo());
        assertEquals(200, jardines.getXmax());

        Escenario arbolMadre = new ArbolMadre();
        assertEquals("Arbol Madre", arbolMadre.getNombre());
        assertEquals(45000, arbolMadre.getPuntoscomienzo());
        assertEquals(300, arbolMadre.getXmax());
    }

    @Test
    @DisplayName("El mapa se crea con el tamano que declara el escenario")
    void elMapaTieneElTamanoDeclarado() {
        Escenario escenario = new ArbolAlmas();
        assertEquals(escenario.getXmax(), escenario.getMapa().length);
        assertEquals(escenario.getYmax(), escenario.getMapa()[0].length);
    }

    @Test
    @DisplayName("El mapa empieza vacio")
    void elMapaEmpiezaVacio() {
        Escenario escenario = new ArbolAlmas();
        assertEquals(0, celdasOcupadas(escenario));
    }

    @Test
    @DisplayName("colocarUnidadSiLibre ocupa la casilla y avisa de que lo ha hecho")
    void colocarUnidadEnCasillaLibre() throws FueradeMapaException {
        Escenario escenario = new ArbolAlmas();
        Coordenadas casilla = new Coordenadas(4, 6, escenario);
        Unidad unidad = new AT99();

        assertTrue(escenario.colocarUnidadSiLibre(unidad, casilla),
                "deberia devolver true al colocarla");
        assertSame(unidad, escenario.getUnidadMapa(casilla));
        assertEquals(1, celdasOcupadas(escenario));
    }

    @Test
    @DisplayName("colocarUnidadSiLibre no pisa una casilla ya ocupada")
    void noPisaUnaCasillaOcupada() throws FueradeMapaException {
        Escenario escenario = new ArbolAlmas();
        Coordenadas casilla = new Coordenadas(4, 6, escenario);
        Unidad primera = new AT99();
        Unidad segunda = new MK6();

        escenario.colocarUnidadSiLibre(primera, casilla);
        assertFalse(escenario.colocarUnidadSiLibre(segunda, casilla),
                "deberia devolver false si la casilla esta ocupada");
        assertSame(primera, escenario.getUnidadMapa(casilla),
                "la unidad original no deberia haberse sustituido");
        assertEquals(1, celdasOcupadas(escenario));
    }

    @Test
    @DisplayName("estaLibre distingue las casillas vacias de las ocupadas")
    void estaLibreDistingueCasillasVaciasYOcupadas() throws FueradeMapaException {
        Escenario escenario = new ArbolAlmas();
        Coordenadas ocupada = new Coordenadas(1, 1, escenario);
        Coordenadas vacia = new Coordenadas(2, 2, escenario);
        Unidad unidad = new AT99();

        escenario.colocarUnidadSiLibre(unidad, ocupada);

        assertFalse(escenario.estaLibre(unidad, ocupada, escenario));
        assertTrue(escenario.estaLibre(unidad, vacia, escenario));
        assertNull(escenario.getUnidadMapa(vacia));
    }

    @Test
    @DisplayName("Dos escenarios del mismo tipo son iguales y comparten codigo hash")
    void escenariosDelMismoTipoSonIguales() {
        assertEquals(new ArbolMadre(), new ArbolMadre());
        assertEquals(new ArbolMadre().hashCode(), new ArbolMadre().hashCode());
    }

    @Test
    @DisplayName("Escenarios de distinto tipo no son iguales")
    void escenariosDeDistintoTipoNoSonIguales() {
        assertFalse(new ArbolMadre().equals(new Jardines()));
        assertFalse(new ArbolAlmas().equals(null));
        assertFalse(new ArbolAlmas().equals("no soy un escenario"));
    }

    @Test
    @DisplayName("El codigo hash del escenario no recorre el mapa de unidades")
    void elHashCodeNoRecorreElMapa() throws FueradeMapaException {
        // El mapa guarda unidades que apuntan a su jugador, el jugador a su
        // ejercito y el ejercito de vuelta a sus unidades. Incluirlo en el
        // calculo desbordaba la pila.
        Escenario escenario = new ArbolAlmas();
        int antes = escenario.hashCode();
        escenario.colocarUnidadSiLibre(new AT99(), new Coordenadas(1, 1, escenario));
        assertEquals(antes, escenario.hashCode(),
                "el codigo hash no deberia depender del estado del mapa");
    }

    @Test
    @DisplayName("toString recoge nombre, puntos y dimensiones")
    void toStringRecogeLosDatosDelEscenario() {
        String texto = new Jardines().toString();
        assertTrue(texto.contains("Jardines Colgantes"), texto);
        assertTrue(texto.contains("20000"), texto);
        assertTrue(texto.contains("200X200"), texto);
    }

    /**
     * Cuenta cuantas casillas del mapa tienen una unidad.
     *
     * @param escenario escenario a recorrer
     * @return el numero de casillas ocupadas
     */
    private int celdasOcupadas(Escenario escenario) {
        int ocupadas = 0;
        for (int x = 0; x < escenario.getXmax(); x++) {
            for (int y = 0; y < escenario.getYmax(); y++) {
                if (escenario.getMapa()[x][y] != null) {
                    ocupadas++;
                }
            }
        }
        return ocupadas;
    }
}
