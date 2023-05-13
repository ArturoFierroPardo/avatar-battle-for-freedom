/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io;

import interfaz.partida.Partida;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.excepciones.SinTripulantesSuficientesException;

/**
 *
 * @author artur
 */
public class Io {

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
    public static void guardarPartida(Partida partida) throws FileNotFoundException {
        File fichero = new File("C:\\Users\\artur\\Desktop\\afierrop\\fichero.txt");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fichero);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(partida);
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
     * fichero.Comprovamos excepciones y que todo se ha cerrado.
     *
     * @return
     * @throws java.lang.ClassNotFoundException
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     */
    public static Partida cargarPartida() throws ClassNotFoundException, PuntosdeCompraInsuficientesException, FueradeMapaException, SinEnergiaMovimientoException, SinEnergiaAtaqueException, SinTripulantesSuficientesException {
        File fichero = new File("C:\\Users\\artur\\Desktop\\afierrop\\fichero.txt");
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        Partida partida = null;
        try {
            fis = new FileInputStream(fichero);
            ois = new ObjectInputStream(fis);
            partida = (Partida) ois.readObject();

        } catch (IOException ex) {
            System.out.println("Error al cargar partida");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
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
        return partida;
    }

}
