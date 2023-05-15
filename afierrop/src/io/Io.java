/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import interfaz.partida.Partida;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author artur
 */
public class Io {

    private static String archivo = "partidas.dat";
    private ArrayList<Partida> partidas;

    public Io() {
        this.partidas = new ArrayList<Partida>();
    }

    public ArrayList<Partida> getPartidas() {
        return this.partidas;
    }

    public void guardarPartida(Partida partida) {
        this.partidas.add(partida);
    }

    /**
     * Funcion guardar partida, que crea un fichero con una ruta y unos objetos
     * que guardan elmobjeto de salia que en este caso es partida que contiene
     * todos los demas objetos.Habia que poner todas las clases como
     * Serializables para poder pasarlo.Una vez escrito todo comprobamos
     * excepciones y comprobamos que el fichero y todo cierra
     *
     * @param partida
     * @throws java.io.FileNotFoundException
     */
    public void guardarArchivo(ArrayList<Partida> partidaslist) throws FileNotFoundException {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(archivo);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(partidaslist);

        } catch (IOException ex) {
            System.out.println("Error al guardar partida");
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero");
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero");
                }
            }
        }

    }

    /**
     * Funcion cargar partida, crea un fichero que es en donde se guardo la
     * anterior y con unos objetos de para sacar datos de ficheros los vamos
     * leyendo y cambiandole la clase a lo que sacamos del fichero a partida
     * creamos una partida nueva en funcion de los datos sacados del
     * fichero.Comprobamos excepciones y que todo se ha cerrado.
     *
     * @return
     */
    public ArrayList<Partida> cargarArchivo() {
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(archivo))) {
            this.partidas = (ArrayList<Partida>) is.readObject();
        } catch (IOException io) {
        } catch (ClassNotFoundException ex) {
        }
        return this.partidas;
    }
}
