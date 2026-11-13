package modelos.ejercito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.jugador.Jugador;
import modelos.unidad.Unidad;
import modelos.unidad.maquina.AT99;
import modelos.unidad.maquina.MK6;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas del ejercito de un jugador: contenido, orden y comparacion.
 *
 * @author Arturo Fierro Pardo
 */
class EjercitoTest {

    @Test
    @DisplayName("Un ejercito recien creado esta vacio")
    void unEjercitoNuevoEstaVacio() {
        assertTrue(new Ejercito().getUnidades().isEmpty());
    }

    @Test
    @DisplayName("Dos ejercitos con las mismas unidades son iguales")
    void dosEjercitosConLasMismasUnidadesSonIguales() {
        // Antes se comparaban las listas con ==, es decir se comprobaba si
        // ambos ejercitos compartian el mismo objeto lista, de modo que dos
        // ejercitos equivalentes nunca resultaban iguales.
        Ejercito a = new Ejercito();
        Ejercito b = new Ejercito();
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());

        a.getUnidades().add(new AT99());
        b.getUnidades().add(new AT99());
        assertEquals(a, b);
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    @DisplayName("Ejercitos con distintas unidades no son iguales")
    void ejercitosDistintosNoSonIguales() {
        Ejercito a = new Ejercito();
        Ejercito b = new Ejercito();
        a.getUnidades().add(new AT99());
        b.getUnidades().add(new MK6());

        assertFalse(a.equals(b));
        assertFalse(a.equals(null));
        assertFalse(a.equals("no soy un ejercito"));
    }

    @Test
    @DisplayName("Se puede sustituir la lista de unidades completa")
    void sePuedeSustituirLaListaDeUnidades() {
        Ejercito ejercito = new Ejercito();
        ArrayList<Unidad> unidades = new ArrayList<>();
        unidades.add(new AT99());
        unidades.add(new MK6());

        ejercito.setUnidades(unidades);

        assertEquals(2, ejercito.getUnidades().size());
    }

    @Test
    @DisplayName("Al comprar, el ejercito queda ordenado de mas a menos defensa")
    void alComprarElEjercitoQuedaOrdenadoPorDefensa() throws Exception {
        Escenario escenario = new ArbolMadre();
        Jugador jugador = new Jugador("Prueba", Equipo.RDA, escenario);
        jugador.setPuntos(200_000);

        // Se compran de menos a mas resistente para comprobar que el orden no
        // es el de compra.
        Unidad mk6 = new MK6();          // defensa 500
        Unidad at99 = new AT99();        // defensa 7500
        jugador.comprarUnidad(mk6);
        jugador.comprarUnidad(at99);

        assertEquals(2, jugador.getEjercito().getUnidades().size());
        assertTrue(jugador.getEjercito().getUnidades().get(0).getEnergiadefensa()
                >= jugador.getEjercito().getUnidades().get(1).getEnergiadefensa(),
                "la primera unidad deberia ser la mas resistente");
    }

    @Test
    @DisplayName("toString enumera las unidades del ejercito")
    void toStringEnumeraLasUnidades() {
        Ejercito ejercito = new Ejercito();
        ejercito.getUnidades().add(new AT99());
        assertTrue(ejercito.toString().contains("AT-99 Escorpion"), ejercito.toString());
    }
}
