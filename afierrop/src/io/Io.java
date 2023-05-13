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
