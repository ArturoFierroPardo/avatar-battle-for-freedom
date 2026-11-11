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

    /**
     * Archivo donde se guarda todo
     */
    private static String archivo = "partidas.dat";

    /**
     * Lista de partidas guardadas en el archivo
     */
    private ArrayList<Partida> partidas;

    /**
     * Constructor de IO para usar las funciones, solo tiene un arraylist de
     * partidas como atributo
     */
    public Io() {
        this.partidas = new ArrayList<Partida>();
    }

    /**
     * Getters
     *
     * @return
     */
    public ArrayList<Partida> getPartidas() {
        return this.partidas;
    }

    /**
     * Funcion guardarpartida, mete la partida que se le pasa por parametro en
     * el constructor de IO
     *
     * @param partida
     */
    public void guardarPartida(Partida partida) {
        this.partidas.add(partida);
    }

    /**
     * Funcion guardar en archivo, que mete en un fichero predeterminado un
     * ArrayList de partidas.Habia que poner todas las clases como Serializables
     * para poder pasarlo.Una vez escrito todo comprobamos excepciones y
     * comprobamos que el fichero y todo cierra.
     *
     * @param partidaslist
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
     * Funcion cargar archivo, en un fichero predeterminado que es en donde se
     * guardo la anterior sacamos el arraylist de partidas y lo returneamos.
     * Comprobamos excepciones y que todo se ha cerrado.
     *
     * @return
     */
    public ArrayList<Partida> cargarArchivo() {
        FileInputStream fis = null;
        ObjectInputStream is = null;
        try {
            fis = new FileInputStream(archivo);
            is = new ObjectInputStream(fis);
            this.partidas = (ArrayList<Partida>) is.readObject();
        } catch (IOException io) {
        } catch (ClassNotFoundException ex) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero");
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero");
                }
            }
        }
        return this.partidas;
    }
}
