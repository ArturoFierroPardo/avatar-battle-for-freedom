package utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pruebas de la lectura de datos por la entrada estandar.
 *
 * @author Arturo Fierro Pardo
 */
class UtilsTest {

    /**
     * Devuelve la lectura a la entrada estandar al terminar cada prueba.
     */
    @AfterEach
    void restaurarEntrada() {
        Utils.usarEntrada(null);
    }

    @Test
    @DisplayName("No se pierden lineas entre lecturas consecutivas")
    void noSePierdenLineasEntreLecturas() {
        // Cuando cada lectura creaba su propio BufferedReader, la primera
        // llenaba su buffer interno con todo lo que podia y lo descartaba al
        // quedar fuera de uso, asi que las lineas siguientes desaparecian.
        Utils.usarEntrada(new StringReader("primera\nsegunda\ntercera\n"));

        assertEquals("primera", Utils.leerCadena());
        assertEquals("segunda", Utils.leerCadena());
        assertEquals("tercera", Utils.leerCadena());
    }

    @Test
    @DisplayName("Se leen enteros seguidos sin perder ninguno")
    void seLeenEnterosSeguidos() {
        Utils.usarEntrada(new StringReader("1\n2\n3\n42\n"));

        assertEquals(1, Utils.leerEntero("?"));
        assertEquals(2, Utils.leerEntero("?"));
        assertEquals(3, Utils.leerEntero("?"));
        assertEquals(42, Utils.leerEntero("?"));
    }

    @Test
    @DisplayName("Un texto que no es un numero hace repetir la pregunta")
    void anteUnTextoNoNumericoSeRepiteLaPregunta() {
        Utils.usarEntrada(new StringReader("hola\nqwerty\n7\n"));
        assertEquals(7, Utils.leerEntero("?"),
                "deberia seguir preguntando hasta recibir un numero");
    }

    @Test
    @DisplayName("Se admiten numeros negativos y con espacios alrededor")
    void seAdmitenNegativosYEspacios() {
        Utils.usarEntrada(new StringReader("-5\n  12  \n"));
        assertEquals(-5, Utils.leerEntero("?"));
        assertEquals(12, Utils.leerEntero("?"));
    }

    @Test
    @DisplayName("Al agotarse la entrada se avisa en lugar de preguntar sin fin")
    void alAgotarseLaEntradaSeLanzaExcepcion() {
        // readLine() devuelve null al final de la entrada. Tratarlo como un
        // dato invalido hacia que leerEntero repitiese la pregunta
        // indefinidamente: un minuto de ejecucion generaba 19 millones de
        // lineas de aviso.
        Utils.usarEntrada(new StringReader("1\n"));
        assertEquals(1, Utils.leerEntero("?"));
        assertThrows(EntradaCerradaException.class, () -> Utils.leerEntero("?"));
    }

    @Test
    @DisplayName("Una entrada vacia salta directamente al aviso de entrada cerrada")
    void unaEntradaVaciaAvisaDeInmediato() {
        Utils.usarEntrada(new StringReader(""));
        assertThrows(EntradaCerradaException.class, () -> Utils.leerCadena());
    }

    @Test
    @DisplayName("Una linea en blanco es un dato valido, no el final de la entrada")
    void unaLineaEnBlancoNoEsElFinalDeLaEntrada() {
        Utils.usarEntrada(new StringReader("\nsegunda\n"));
        assertEquals("", Utils.leerCadena());
        assertEquals("segunda", Utils.leerCadena());
    }

    @Test
    @DisplayName("Se leen decimales en doble y simple precision")
    void seLeenDecimales() {
        Utils.usarEntrada(new StringReader("3.5\n2.25\n"));
        assertTrue(Math.abs(Utils.leerDouble("?") - 3.5) < 0.0001);
        assertTrue(Math.abs(Utils.leerFloat("?") - 2.25f) < 0.0001);
    }

    @Test
    @DisplayName("La excepcion de entrada cerrada lleva un mensaje explicativo")
    void laExcepcionLlevaMensaje() {
        EntradaCerradaException ece = new EntradaCerradaException();
        assertTrue(ece.getMessage() != null && !ece.getMessage().isBlank(),
                "deberia explicar que ha pasado");
    }
}
