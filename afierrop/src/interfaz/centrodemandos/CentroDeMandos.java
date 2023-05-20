/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.centrodemandos;

import java.util.ArrayList;

/**
 *
 * @author artur
 */
public class CentroDeMandos {

    private ArrayList<String> acciones;

    public CentroDeMandos() {
        this.acciones = new ArrayList<String>();
    }

    public void añadirString(String accion) {
        this.acciones.add(accion);
    }

    @Override
    public String toString() {
        String listaaccio = "";
        for (String accion : this.acciones) {
            listaaccio += accion + "\n";
        }
        return listaaccio;
    }

}
