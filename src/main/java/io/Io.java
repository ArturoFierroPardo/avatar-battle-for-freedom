package io;

import interfaz.partida.Partida;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Persistencia de las partidas en disco mediante serializacion de objetos.
 *
 * <p>
 * Todas las partidas guardadas viven en un unico fichero binario. Por eso las
 * clases del modelo implementan {@link java.io.Serializable} y fijan su
 * {@code serialVersionUID}.
 *
 * @author Arturo Fierro Pardo
 */
public class Io {

    /**
     * Fichero donde se guardan todas las partidas.
     */
    private static final String ARCHIVO = "partidas.dat";

    /**
     * Lista de partidas cargadas del fichero.
     */
    private ArrayList<Partida> partidas;

    /**
     * Crea el gestor de ficheros con una lista de partidas vacia.
     */
    public Io() {
        this.partidas = new ArrayList<>();
    }

    /**
     * Devuelve las partidas que tiene cargadas este gestor.
     *
     * @return la lista de partidas
     */
    public ArrayList<Partida> getPartidas() {
        return this.partidas;
    }

    /**
     * Añade una partida a la lista en memoria, sin escribirla en disco.
     *
     * @param partida partida que se quiere guardar
     */
    public void guardarPartida(Partida partida) {
        this.partidas.add(partida);
    }

    /**
     * Escribe la lista de partidas en el fichero, sobrescribiendo su contenido
     * anterior.
     *
     * @param partidaslist partidas que se quieren persistir
     * @throws java.io.FileNotFoundException si el fichero no se puede crear o
     * abrir para escritura
     */
    public void guardarArchivo(ArrayList<Partida> partidaslist) throws FileNotFoundException {
        // try-with-resources cierra los dos flujos tambien si salta una
        // excepcion, lo que antes exigia dos bloques finally anidados.
        try (FileOutputStream fos = new FileOutputStream(ARCHIVO);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(partidaslist);
        } catch (IOException ex) {
            System.out.println("Error al guardar la partida: " + ex.getMessage());
        }
    }

    /**
     * Lee del fichero la lista de partidas guardadas.
     *
     * <p>
     * Si el fichero todavia no existe se devuelve una lista vacia: es lo normal
     * la primera vez que se ejecuta el juego, y no se trata como un error. El
     * resto de fallos de lectura si se informan por consola, en vez de
     * descartarse en silencio como ocurria antes.
     *
     * @return las partidas guardadas, o una lista vacia si no hay ninguna
     */
    public ArrayList<Partida> cargarArchivo() {
        try (FileInputStream fis = new FileInputStream(ARCHIVO);
             ObjectInputStream is = new ObjectInputStream(fis)) {
            this.partidas = leerPartidas(is);
        } catch (FileNotFoundException fnf) {
            // Primera ejecucion: todavia no hay partidas guardadas.
            this.partidas = new ArrayList<>();
        } catch (IOException ex) {
            System.out.println("No se han podido leer las partidas guardadas: " + ex.getMessage());
            this.partidas = new ArrayList<>();
        } catch (ClassNotFoundException ex) {
            System.out.println("El fichero de partidas no se corresponde con esta version del juego.");
            this.partidas = new ArrayList<>();
        }
        return this.partidas;
    }

    /**
     * Lee un objeto del flujo y comprueba que de verdad es una lista de
     * partidas antes de devolverlo.
     *
     * <p>
     * La serializacion de Java no conserva los tipos genericos, asi que el
     * casteo a {@code ArrayList<Partida>} no se puede verificar en tiempo de
     * compilacion. Se recorre la lista comprobando cada elemento para que un
     * fichero corrupto o de otra version falle aqui con un mensaje claro, en
     * lugar de provocar un ClassCastException mas adelante en mitad del juego.
     *
     * @param is flujo del que se lee
     * @return la lista de partidas leida
     * @throws IOException si el contenido del fichero no es una lista de
     * partidas
     * @throws ClassNotFoundException si el fichero referencia clases que no
     * existen en esta version
     */
    private ArrayList<Partida> leerPartidas(ObjectInputStream is)
            throws IOException, ClassNotFoundException {
        Object leido = is.readObject();
        if (!(leido instanceof List<?>)) {
            throw new IOException("el fichero no contiene una lista de partidas");
        }
        ArrayList<Partida> resultado = new ArrayList<>();
        for (Object elemento : (List<?>) leido) {
            if (!(elemento instanceof Partida)) {
                throw new IOException("el fichero contiene datos que no son partidas");
            }
            resultado.add((Partida) elemento);
        }
        return resultado;
    }
}
