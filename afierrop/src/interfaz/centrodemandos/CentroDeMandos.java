/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.centrodemandos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import modelos.excepciones.IndexOutofBounceException;
import modelos.jugador.Jugador;
import modelos.unidad.Unidad;
import utils.Utils;

/**
 *
 * @author artur
 */
public class CentroDeMandos implements Serializable {

    private Jugador jugador;

    private ArrayList<String> acciones;

    public CentroDeMandos(Jugador jugador) {
        this.acciones = new ArrayList<String>();
        this.jugador = jugador;
    }

    public void añadirString(String accion) {
        this.acciones.add(accion);
    }

    public void enseñarCM() throws IndexOutofBounceException {
        boolean bucle = true;
        do {
            System.out.println("-------------------CENTRO DE MANDOS-------------------\n");
            System.out.println("\t1) Sucesos de la partida");
            System.out.println("\t2) Información de su ejercito");
            System.out.println("\t3) Volver al menu\n");
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
                                                System.out.println("\n\t" + this.jugador.getEjercito().getUnidades().get(eleccionver).toStringInfo());
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

    @Override
    public String toString() {
        String listaacciones = "";
        for (String accion : this.acciones) {
            listaacciones += accion + "\n";
        }
        return listaacciones;
    }

}
