/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.menu;

import modelos.equipo.Equipo;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.jugador.Jugador;
import modelos.unidad.Unidad;
import modelos.unidad.bestia.Banshee;
import modelos.unidad.bestia.Thanator;
import modelos.unidad.bestia.Titanothere;
import modelos.unidad.maquina.AT99;
import modelos.unidad.maquina.Aerospatiale;
import modelos.unidad.maquina.MK6;
import utils.Utils;

/**
 *
 * @author artur
 */
public class Menu {

    private Jugador jugadorjuega;

    public Menu(Jugador jugadorjuega) {
        this.jugadorjuega = jugadorjuega;
    }

    public void enseñarMenu() throws PuntosdeCompraInsuficientesException {
        System.out.println("MENU:");
        System.out.println("1) Comprar una unidad");
        System.out.println("2) Mover una unidad");
        System.out.println("3) Atacar a una unidad enemiga");
        System.out.println("4) Finalizar el turno");
        int opcion = Utils.leerEntero("Elija que quiere hacer escribiendo el nº correspondiente: ");
        switch (opcion) {
            case 1:
                compraUnidad();
            case 2:
            /*Mover unidad*/
            case 3:
            /*Atacar a una unidad*/
            case 4:
            /*Cambiar turno*/

        }
    }

    private void compraUnidad() throws PuntosdeCompraInsuficientesException {
        boolean bucle = true;
        while (bucle) {
            try {
                if (jugadorjuega.getEquipo() == Equipo.RDA) {
                    Unidad at99 = new AT99();
                    Unidad aerospatiale = new Aerospatiale();
                    Unidad mk6 = new MK6();
                    System.out.println("MERCADO DE UNIDADES: ");
                    System.out.println("1) Comprar AT99 - Escorpion: \n" + at99.toStringInfo());
                    System.out.println("2) Comprar Aerospatiale SA-2 Samson: \n" + aerospatiale.toStringInfo());
                    System.out.println("3) Comprar Plataforma de Movilidad Amplificada Mitsubishi MK-6: \n" + mk6.toStringInfo());
                    int eleccion = Utils.leerEntero("Elija que quiere comprar introduciendo un nº: ");

                    switch (eleccion) {
                        case 1:
                            jugadorjuega.comprarUnidad(at99);
                            System.out.println(jugadorjuega.getNombre() + " ha comprado un " + at99.getNombre());
                            System.out.println(jugadorjuega.getEjercito());
                            break;
                        case 2:
                            jugadorjuega.comprarUnidad(aerospatiale);
                            System.out.println(jugadorjuega.getNombre() + " ha comprado un " + aerospatiale.getNombre());
                            System.out.println(jugadorjuega.getEjercito());
                            break;
                        case 3:
                            jugadorjuega.comprarUnidad(mk6);
                            System.out.println(jugadorjuega.getNombre() + " ha comprado un " + mk6.getNombre());
                            System.out.println(jugadorjuega.getEjercito());
                            break;
                    }
                }
                if (jugadorjuega.getEquipo() == Equipo.Navi) {
                    Unidad banshee = new Banshee();
                    Unidad thanator = new Thanator();
                    Unidad titanothere = new Titanothere();
                    System.out.println("MERCADO DE UNIDADES: ");
                    System.out.println("1) Comprar Banshee de Montaña: \n" + banshee.toStringInfo());
                    System.out.println("2) Comprar Thanator: \n" + thanator.toStringInfo());
                    System.out.println("3) Comprar Titanothere Cabezamartillo: \n" + titanothere.toStringInfo());
                    int eleccion = Utils.leerEntero("Elija que quiere comprar introduciendo un nº: ");

                    switch (eleccion) {
                        case 1:
                            jugadorjuega.comprarUnidad(banshee);
                            System.out.println(jugadorjuega.getNombre() + " ha comprado un " + banshee.getNombre());
                            System.out.println(jugadorjuega.getEjercito());
                            break;
                        case 2:
                            jugadorjuega.comprarUnidad(thanator);
                            System.out.println(jugadorjuega.getNombre() + " ha comprado un " + thanator.getNombre());
                            System.out.println(jugadorjuega.getEjercito());
                            break;
                        case 3:
                            jugadorjuega.comprarUnidad(titanothere);
                            System.out.println(jugadorjuega.getNombre() + " ha comprado un " + titanothere.getNombre());
                            System.out.println(jugadorjuega.getEjercito());
                            break;
                    }
                }
                break;
            } catch (PuntosdeCompraInsuficientesException pci) {
                System.out.println("NO TIENE SUFICIENTES PUNTOS PARA COMPRAR ESTA UNIDAD");
            }
        }
    }
}
