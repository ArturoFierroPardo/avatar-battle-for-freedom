/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.centrodemandos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import modelos.excepciones.IndexOutofBounceException;
import modelos.jugador.Jugador;
import modelos.unidad.Unidad;
import utils.Utils;

/**
 *
 * @author artur
 */
public class CentroDeMandos implements Serializable {

    /**
     * Jugador que esta usando el centro de mandos
     */
    private Jugador jugador;

    /**
     * ArrayList de acciones que han pasado en la partida
     */
    private ArrayList<String> acciones;

    /**
     * Constructor de centro de mandos.Recibe un jugador por parametro e inicia
     * el ArrayList vacio.
     *
     * @param jugador
     */
    public CentroDeMandos(Jugador jugador) {
        this.acciones = new ArrayList<String>();
        this.jugador = jugador;
    }

    /**
     * Getters
     *
     * @return
     */
    public Jugador getJugador() {
        return this.jugador;
    }

    public ArrayList<String> getAcciones() {
        return this.acciones;
    }

    /**
     * Funcion añadirString que añade al arraylist de centro de mandos el string
     * que se le pasa por parametro
     *
     * @param accion
     */
    public void añadirString(String accion) {
        this.acciones.add(accion);
    }

    /**
     * Funcion que enseña el menu de centro de mandos.La primera opcion muestra
     * por pantalla el arraylist de acciones, si esta vacio muestra por pantalla
     * un mensaje diciendo que no hay nada. La segunda opcion enseña las
     * unidades al jugador, le pregunta de cual quiere ver su informacion y al
     * seleccionarla muestra toda su informacion. La tercera opcion enseña al
     * jugador toda su informacion (puntos, ejercito, nombre...). Y la cuarta
     * opcion sirve para salir del centro de mandos al menu normal, ya que cada
     * opcion usada aqui al terminar vuelve al menu de centro de mandos para no
     * gastar turnos en aqui
     *
     * @throws modelos.excepciones.IndexOutofBounceException
     */
    public void enseñarCM() throws IndexOutofBounceException {
        boolean bucle = true;
        do {
            System.out.println("-------------------CENTRO DE MANDOS-------------------\n");
            System.out.println("\t1) Sucesos de la partida");
            System.out.println("\t2) Informacion de su ejercito");
            System.out.println("\t3) Informacion del jugador");
            System.out.println("\t4) Volver al menu\n");
            int opcioncm = Utils.leerEntero("Elija que quiere hacer escribiendo el numero correspondiente: ");
            switch (opcioncm) {
                case 1:
                    if (this.acciones.isEmpty()) {
                        System.out.println("\tNo ha sucedido nada en la partida\n");
                    } else {
                        System.out.println("\n" + this.toString());
                    }
                    bucle = true;
                    break;
                case 2:
                    while (true) {
                        try {
                            if (this.jugador.getEjercito().getUnidades().isEmpty()) {
                                throw new IndexOutofBounceException();
                            } else {
                                Collections.sort(this.jugador.getEjercito().getUnidades(), new Unidad.comparadorEDEF());
                                for (int i = 0; i < this.jugador.getEjercito().getUnidades().size(); i++) {
                                    int j = 1 + i;
                                    System.out.println("\t" + j + ") Unidad " + j + ": " + this.jugador.getEjercito().getUnidades().get(i) + "\n");
                                }
                                boolean buclever = false;
                                while (true) {
                                    try {
                                        do {
                                            int eleccionver = Utils.leerEntero("\nDe que unidad de su ejercito dese su informacion? Pulse el 0 para volver") - 1;
                                            if (this.jugador.getEjercito().getUnidades().isEmpty() || this.jugador.getEjercito().getUnidades().size() < eleccionver + 1 || eleccionver < -1) {
                                                buclever = true;
                                                throw new IndexOutofBounceException();
                                            }
                                            if (eleccionver == -1) {
                                                System.out.println("\n" + this.jugador.getNombre() + " no ha querido ver ninguna informacion\n");
                                                buclever = false;
                                            }
                                            if (this.jugador.getEjercito().getUnidades().size() >= eleccionver && eleccionver != -1) {
                                                System.out.println("\n" + this.jugador.getEjercito().getUnidades().get(eleccionver).toStringInfo());
                                                buclever = false;
                                            }
                                        } while (buclever);
                                        break;
                                    } catch (IndexOutofBounceException iob) {
                                        System.out.println("\tOPCION INVALIDA. NO TIENE TANTAS UNIDADES. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                    }
                                }
                                break;
                            }
                        } catch (IndexOutofBounceException iob) {
                            System.out.println("\tOPCION INVALIDA. NO TIENE UNIDADES. VUELVA A INTENTARLO CORRECTAMENTE\n");
                            break;
                        }
                    }
                    break;
                case 3:
                    System.out.println(this.jugador.toString());
                    for (int i = 0; i < this.jugador.getEjercito().getUnidades().size(); i++) {
                        int j = 1 + i;
                        System.out.println("\t" + j + ") Unidad " + j + ": " + this.jugador.getEjercito().getUnidades().get(i) + "\n");
                    }
                    bucle = true;
                    break;
                case 4:
                    System.out.println("\tVolviendo al menu\n");
                    bucle = false;
                    break;
                default:
                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                    bucle = true;
                    break;
            }
        } while (bucle);
    }

    /**
     * Override de toString que muestra la lista de acciones de la partida
     */
    @Override
    public String toString() {
        String listaacciones = "";
        for (int i = 0; i < this.acciones.size(); i++) {
            int j = 1 + i;
            listaacciones += "\t" + j + ") Accion " + j + ": " + this.acciones.get(i) + "\n";
        }
        return listaacciones;
    }

    /**
     * Override de la funcion equals para comparar si dos centros de mandos son
     * iguales
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof CentroDeMandos) {
            CentroDeMandos cm = (CentroDeMandos) o;
            return (this.jugador == cm.getJugador() && this.acciones == cm.getAcciones());
        } else {
            return false;
        }
    }

    /**
     * Override del hashcode para esta clase
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.jugador);
        hash = 79 * hash + Objects.hashCode(this.acciones);
        return hash;
    }

}
