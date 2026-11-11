package modelos.ser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import modelos.ser.humano.Artillero;
import modelos.ser.humano.Comandante;
import modelos.ser.navi.Omaticaya;
import modelos.ser.navi.Tipani;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de los tripulantes de cada bando: caracteristicas, raza y cargo.
 *
 * @author Arturo Fierro Pardo
 */
class SerTest {

    @Test
    @DisplayName("Los tripulantes de la RDA son humanos")
    void losTripulantesDeLaRdaSonHumanos() {
        assertEquals(TipoRaza.HUMANO, new Comandante().getRaza());
        assertEquals(TipoRaza.HUMANO, new Artillero().getRaza());
    }

    @Test
    @DisplayName("Los tripulantes Navi son de raza navi")
    void losTripulantesNaviSonDeRazaNavi() {
        assertEquals(TipoRaza.NAVI, new Omaticaya().getRaza());
        assertEquals(TipoRaza.NAVI, new Tipani().getRaza());
    }

    @Test
    @DisplayName("Cada bando tiene un oficial y un guerrero")
    void cadaBandoTieneUnOficialYUnGuerrero() {
        assertEquals(TipoCargo.OFICIAL, new Comandante().getCargo());
        assertEquals(TipoCargo.GUERRERO, new Artillero().getCargo());
        assertEquals(TipoCargo.OFICIAL, new Omaticaya().getCargo());
        assertEquals(TipoCargo.GUERRERO, new Tipani().getCargo());
    }

    @Test
    @DisplayName("Los oficiales cuestan mas y dan mas puntos al derrotarlos")
    void losOficialesCuestanMasYDanMasPuntos() {
        Ser oficial = new Comandante();
        Ser guerrero = new Artillero();

        assertTrue(oficial.getCoste() > guerrero.getCoste(),
                "el oficial deberia ser mas caro");
        assertTrue(oficial.getPuntosderrota() > guerrero.getPuntosderrota(),
                "derrotar a un oficial deberia dar mas puntos");
    }

    @Test
    @DisplayName("Los Navi aguantan mas que los humanos")
    void losNaviAguantanMasQueLosHumanos() {
        assertEquals(300, new Omaticaya().getVida());
        assertEquals(300, new Tipani().getVida());
        assertEquals(150, new Comandante().getVida());
        assertEquals(150, new Artillero().getVida());
    }

    @Test
    @DisplayName("Un ser pierde vida al recibir dano")
    void unSerPierdeVidaAlRecibirDano() {
        Ser ser = new Omaticaya();
        ser.setVida(ser.getVida() - 100);
        assertEquals(200, ser.getVida());
    }

    @Test
    @DisplayName("Dos tripulantes del mismo tipo son iguales")
    void dosTripulantesDelMismoTipoSonIguales() {
        Ser a = new Comandante();
        Ser b = new Comandante();

        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
        assertFalse(a.equals(new Artillero()));
        assertFalse(a.equals(new Omaticaya()));
        assertFalse(a.equals(null));
        assertFalse(a.equals("no soy un ser"));
    }

    @Test
    @DisplayName("Un ser herido deja de ser igual a uno sano del mismo tipo")
    void unSerHeridoNoEsIgualAUnoSano() {
        Ser herido = new Comandante();
        herido.setVida(10);
        assertFalse(herido.equals(new Comandante()));
    }

    @Test
    @DisplayName("toString devuelve solo el nombre del ser")
    void toStringDevuelveElNombre() {
        assertEquals("Comandante de SecOps", new Comandante().toString());
        assertEquals("Tipani", new Tipani().toString());
    }

    @Test
    @DisplayName("La ficha del ser recoge todas sus caracteristicas")
    void laFichaRecogeTodasLasCaracteristicas() {
        String ficha = new Omaticaya().toStringInfo();
        assertTrue(ficha.contains("Omaticaya"), ficha);
        assertTrue(ficha.contains("OFICIAL"), ficha);
        assertTrue(ficha.contains("NAVI"), ficha);
        assertTrue(ficha.contains("300"), ficha);
    }
}
