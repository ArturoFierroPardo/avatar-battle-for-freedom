/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import interfaz.centrodemandos.CentroDeMandos;
import interfaz.partida.Partida;
import io.Io;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import modelos.equipo.Equipo;
import modelos.escenario.ArbolAlmas;
import modelos.escenario.ArbolMadre;
import modelos.escenario.Escenario;
import modelos.escenario.Jardines;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.excepciones.SinTripulantesSuficientesException;
import modelos.jugador.Jugador;
import utils.Utils;

/**
 *
 * @author artur
 */
public class Main {

    // Hacer Javadoc, memoria y esperar a ver nota y comentarios de entrega, antes de entregar la 3º entrega.
    /**
     * Main para empezar el juego. Además selecciona un escenario el cual se le
     * pide al usuario. Y si quiere empezar una partida nueva o cargar alguna
     * anterior. Se construye el IO, y se añade a un arraylist partidas, todo lo
     * que haya en el archivo. Si decide cargar la partida anterior, de la nueva
     * lista de partidas creadas se sacan todas a elegir, puede pulsa rel 0 para
     * volver atras. Si elige alguna partida anterior, antes de comenzar se
     * elimina del arraylist, se inicia y una vez acabe se vuelve a guardar con
     * los nuevos datos de la partida. Si la partida tiene ganador que es un
     * atributo booleano de partida se elimina directamente del array list. Si
     * decide hacer nueva partida, una vez acabe la funcion comenzar partida, se
     * guarda en el array. Al acabar todo antes de cerra el main se guarda en el
     * archivo el nuevo arraylist, así siempre se guardaran todas. Si no
     * iniciasemos el arraylist al princpio, cada vez que hacemos run, al tener
     * un constructor nuevo por programa, solo se añadiria la anterior en el
     * archivo, es por ello que gracia a que añadimos a un arraylist todas las
     * partidas anteriores y la nueva, se va stackeando en ese arraylist y luego
     * se carga en el archivo. Todas las clases tiene implementadas la
     * serializacion para poder usar los archivos bien. Se permite al jugador
     * elegir su nombre y equipo.
     *
     * @param args the command line arguments
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     * @throws java.io.FileNotFoundException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String[] args) throws FueradeMapaException, PuntosdeCompraInsuficientesException, SinEnergiaMovimientoException, SinEnergiaAtaqueException, SinTripulantesSuficientesException, FileNotFoundException, ClassNotFoundException {
        Io io = new Io();
        Equipo equipo1 = null;
        Equipo equipo2 = null;
        ArrayList<Partida> listapartidas = io.cargarArchivo();
        boolean buclecarga = true;
        boolean buclepart = true;
        boolean bucleequip = true;
        System.out.println("\tAVATAR BATTLE FOR FREEDOM\n");
        do {
            System.out.println("\n-------------------SELECCION DE PARTIDA-------------------\n");
            System.out.println("\t1) Cargar Partida Anterior\n");
            System.out.println("\t2) Nueva Partida\n");
            int eleccionpartida = Utils.leerEntero("Quieres cargar otra partida o empezar una nueva? ");
            switch (eleccionpartida) {
                case 1:
                    while (buclecarga) {
                        if (!(listapartidas.isEmpty())) {
                            System.out.println("\nPARTIDAS GUARDADAS: ");
                            for (int i = 0; i < listapartidas.size(); i++) {
                                int j = 1 + i;
                                System.out.println("\t" + j + ") Partida " + j + " en " + listapartidas.get(i).getEscenario().getNombre() + " en el turno " + listapartidas.get(i).getTurnos() + "\n");
                            }
                            int opcion = Utils.leerEntero("Introduzca el numero de la partida: (Pulse 0 para volver atras)") - 1;
                            if (opcion == -1) {
                                System.out.println("\nNo ha querido cargar ninguna partida anterior. Volviendo al menu de seleccion de partida.");
                                buclecarga = false;
                            } else if (opcion >= 0 && opcion < listapartidas.size()) {
                                Partida partida = listapartidas.get(opcion);
                                listapartidas.remove(partida);
                                partida.comienzaPartida();
                                if (!(partida.getTerminada())) {
                                    listapartidas.add(partida);
                                }
                                buclecarga = false;
                                buclepart = false;
                            } else {
                                System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                buclecarga = true;
                            }
                        } else {
                            System.out.println("\tOPCION INVALIDA. NO TIENE PARTIDAS GUARDADAS ANTERIORMENTE. SELECCIONE NUEVA PARTIDA.\n");
                            buclecarga = false;
                        }
                    }
                    buclecarga = true;
                    break;
                case 2:
                    Escenario escenario = null;
                    boolean bucle = true;
                    Escenario arbolalmas = new ArbolAlmas();
                    Escenario jardines = new Jardines();
                    Escenario arbolmadre = new ArbolMadre();
                    System.out.println("\n-------------------ESCENARIOS-------------------\n");
                    System.out.println("1) Arbol de las Almas:\n" + arbolalmas.toString());
                    System.out.println("2) Jardines Colgantes:\n" + jardines.toString());
                    System.out.println("3) Arbol Madre:\n" + arbolmadre.toString());
                    do {
                        int eleccionesc = Utils.leerEntero("Elija en que escenario quiere disputar la batalla introduciendo el numero correspondiente ");
                        switch (eleccionesc) {
                            case 1:
                                escenario = arbolalmas;
                                bucle = false;
                                break;
                            case 2:
                                escenario = jardines;
                                bucle = false;
                                break;
                            case 3:
                                escenario = arbolmadre;
                                bucle = false;
                                break;
                            default:
                                System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                bucle = true;
                                break;
                        }
                    } while (bucle);
                    String nombre1 = Utils.leerCadena("Jugador 1, elija su nombre: ");
                    String nombre2 = Utils.leerCadena("Jugador2, elija su nombre: ");
                    do {
                        System.out.println("\n-------------------SELECCION DE EQUIPO-------------------\n");
                        System.out.println("\t1) Equipo RDA");
                        System.out.println("\t2) Equip NAVI\n");
                        int eleccionequipo = Utils.leerEntero("Jugador 1 seleccione su equipo: (El Jugador 2 será del equipo contrario) ");
                        switch (eleccionequipo) {
                            case 1:
                                equipo1 = Equipo.RDA;
                                equipo2 = Equipo.NAVI;
                                bucleequip = false;
                                break;
                            case 2:
                                equipo1 = Equipo.NAVI;
                                equipo2 = Equipo.RDA;
                                bucleequip = false;
                                break;
                            default:
                                System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                break;
                        }
                    } while (bucleequip);
                    Jugador jugador1 = new Jugador(nombre1, equipo1, escenario);
                    Jugador jugador2 = new Jugador(nombre2, equipo2, escenario);
                    CentroDeMandos CM1 = new CentroDeMandos(jugador1);
                    CentroDeMandos CM2 = new CentroDeMandos(jugador2);
                    Partida partida = new Partida(jugador1, jugador2, escenario, CM1, CM2);
                    partida.comienzaPartida();
                    listapartidas.add(partida);
                    buclepart = false;
                    break;
                default:
                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                    buclepart = true;
                    break;

            }

        } while (buclepart);
        io.guardarArchivo(listapartidas);
    }

}
