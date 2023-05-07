/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.menu;

import modelos.equipo.Equipo;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.jugador.Jugador;
import modelos.ser.Ser;
import modelos.ser.humano.Artillero;
import modelos.ser.humano.Comandante;
import modelos.ser.navi.Omaticaya;
import modelos.ser.navi.Tipani;
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
        int opcion = Utils.leerEntero("Elija que quiere hacer escribiendo el numero correspondiente: ");
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
        int nunidades = Utils.leerEntero("Cuantas unidades desea comprar?");
        while (bucle) {
            try {
                for (int j = 0; j < nunidades; j++) {
                    if (jugadorjuega.getEquipo() == Equipo.RDA) {
                        Unidad at99 = new AT99();
                        Unidad aerospatiale = new Aerospatiale();
                        Unidad mk6 = new MK6();
                        System.out.println("MERCADO DE UNIDADES: ");
                        System.out.println("1) Comprar AT99 - Escorpion: \n" + at99.toStringInfo());
                        System.out.println("2) Comprar Aerospatiale SA-2 Samson: \n" + aerospatiale.toStringInfo());
                        System.out.println("3) Comprar Plataforma de Movilidad Amplificada Mitsubishi MK-6: \n" + mk6.toStringInfo());
                        System.out.println("4) No comprar nada");
                        int eleccionunidades = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");

                        switch (eleccionunidades) {
                            case 1:
                                jugadorjuega.comprarUnidad(at99);
                                int eleccionserescompra1 = Utils.leerEntero("Desea comprar seres que tripulen esta unidad? Necesita " + at99.getSeresneed() + " para poder estar operativo \n1) SI \n2) NO");
                                switch (eleccionserescompra1) {
                                    case 1:
                                        int nseres = Utils.leerEntero("Cuantos seres desea comprar que no supere " + at99.getSeresneed() + "?");
                                        for (int i = 0; i < nseres; i++) {
                                            Ser comandante = new Comandante();
                                            Ser artillero = new Artillero();
                                            System.out.println("MERCADO DE SERES:");
                                            System.out.println("1) Comprar Comandante: " + comandante.toStringInfo());
                                            System.out.println("2) Comprar Artillero: " + artillero.toStringInfo());
                                            System.out.println("3) No comprar nada");
                                            int eleccionseres = Utils.leerEntero("Elija que quiere compra introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    jugadorjuega.comprarSeres(at99, comandante);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + comandante.getNombre());
                                                    break;
                                                case 2:
                                                    jugadorjuega.comprarSeres(at99, artillero);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + artillero.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(jugadorjuega.getEjercito());
                                break;
                            case 2:
                                jugadorjuega.comprarUnidad(aerospatiale);
                                int eleccionserescompra2 = Utils.leerEntero("Desea comprar seres que tripulen esta unidad? Necesita " + aerospatiale.getSeresneed() + " para poder estar operativo \n1) SI \n2) NO");
                                switch (eleccionserescompra2) {
                                    case 1:
                                        int nseres = Utils.leerEntero("Cuantos seres desea comprar que no supere " + aerospatiale.getSeresneed() + "?");
                                        for (int i = 0; i < nseres; i++) {
                                            Ser comandante = new Comandante();
                                            Ser artillero = new Artillero();
                                            System.out.println("MERCADO DE SERES:");
                                            System.out.println("1) Comprar Comandante: " + comandante.toStringInfo());
                                            System.out.println("2) Comprar Artillero: " + artillero.toStringInfo());
                                            System.out.println("3) No comprar nada");
                                            int eleccionseres = Utils.leerEntero("Elija que quiere compra introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    jugadorjuega.comprarSeres(aerospatiale, comandante);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + comandante.getNombre());
                                                    break;
                                                case 2:
                                                    jugadorjuega.comprarSeres(aerospatiale, artillero);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + artillero.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(jugadorjuega.getEjercito());
                                break;
                            case 3:
                                jugadorjuega.comprarUnidad(mk6);
                                int eleccionserescompra3 = Utils.leerEntero("Desea comprar seres que tripulen esta unidad? Necesita " + mk6.getSeresneed() + " para poder estar operativo \n1) SI \n2) NO");
                                switch (eleccionserescompra3) {
                                    case 1:
                                        int nseres = Utils.leerEntero("Cuantos seres desea comprar que no supere " + mk6.getSeresneed() + "?");
                                        for (int i = 0; i < nseres; i++) {
                                            Ser comandante = new Comandante();
                                            Ser artillero = new Artillero();
                                            System.out.println("MERCADO DE SERES:");
                                            System.out.println("1) Comprar Comandante: " + comandante.toStringInfo());
                                            System.out.println("2) Comprar Artillero: " + artillero.toStringInfo());
                                            System.out.println("3) No comprar nada");
                                            int eleccionseres = Utils.leerEntero("Elija que quiere compra introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    jugadorjuega.comprarSeres(mk6, comandante);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + comandante.getNombre());
                                                    break;
                                                case 2:
                                                    jugadorjuega.comprarSeres(mk6, artillero);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + artillero.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(jugadorjuega.getEjercito());
                                break;
                            case 4:
                                System.out.println(jugadorjuega.getNombre() + " no ha comprado ninguna unidad");
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
                        System.out.println("4) No comprar nada");
                        int eleccionunidades = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");

                        switch (eleccionunidades) {
                            case 1:
                                jugadorjuega.comprarUnidad(banshee);
                                int eleccionserescompra1 = Utils.leerEntero("Desea comprar seres que tripulen esta unidad? Necesita " + banshee.getSeresneed() + " para poder estar operativo \n1) SI \n2) NO");
                                switch (eleccionserescompra1) {
                                    case 1:
                                        int nseres = Utils.leerEntero("Cuantos seres desea comprar que no supere " + banshee.getSeresneed() + "?");
                                        for (int i = 0; i < nseres; i++) {
                                            Ser omaticaya = new Omaticaya();
                                            Ser tipani = new Tipani();
                                            System.out.println("MERCADO DE SERES:");
                                            System.out.println("1) Comprar Omaticaya: " + omaticaya.toStringInfo());
                                            System.out.println("2) Comprar Tipani: " + tipani.toStringInfo());
                                            System.out.println("3) No comprar nada");
                                            int eleccionseres = Utils.leerEntero("Elija que quiere compra introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    jugadorjuega.comprarSeres(banshee, omaticaya);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + omaticaya.getNombre());
                                                    break;
                                                case 2:
                                                    jugadorjuega.comprarSeres(banshee, tipani);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + tipani.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(jugadorjuega.getEjercito());
                                break;
                            case 2:
                                jugadorjuega.comprarUnidad(thanator);
                                int eleccionserescompra2 = Utils.leerEntero("Desea comprar seres que tripulen esta unidad? Necesita " + thanator.getSeresneed() + " para poder estar operativo \n1) SI \n2) NO");
                                switch (eleccionserescompra2) {
                                    case 1:
                                        int nseres = Utils.leerEntero("Cuantos seres desea comprar que no supere " + thanator.getSeresneed() + "?");
                                        for (int i = 0; i < nseres; i++) {
                                            Ser omaticaya = new Omaticaya();
                                            Ser tipani = new Tipani();
                                            System.out.println("MERCADO DE SERES:");
                                            System.out.println("1) Comprar Omaticaya: " + omaticaya.toStringInfo());
                                            System.out.println("2) Comprar Tipani: " + tipani.toStringInfo());
                                            System.out.println("3) No comprar nada");
                                            int eleccionseres = Utils.leerEntero("Elija que quiere compra introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    jugadorjuega.comprarSeres(thanator, omaticaya);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + omaticaya.getNombre());
                                                    break;
                                                case 2:
                                                    jugadorjuega.comprarSeres(thanator, tipani);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + tipani.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(jugadorjuega.getEjercito());
                                break;

                            case 3:
                                jugadorjuega.comprarUnidad(titanothere);
                                int eleccionserescompra3 = Utils.leerEntero("Desea comprar seres que tripulen esta unidad? Necesita " + titanothere.getSeresneed() + " para poder estar operativo \n1) SI \n2) NO");
                                switch (eleccionserescompra3) {
                                    case 1:
                                        int nseres = Utils.leerEntero("Cuantos seres desea comprar que no supere " + titanothere.getSeresneed() + "?");
                                        for (int i = 0; i < nseres; i++) {
                                            Ser omaticaya = new Omaticaya();
                                            Ser tipani = new Tipani();
                                            System.out.println("MERCADO DE SERES:");
                                            System.out.println("1) Comprar Omaticaya: " + omaticaya.toStringInfo());
                                            System.out.println("2) Comprar Tipani: " + tipani.toStringInfo());
                                            System.out.println("3) No comprar nada");
                                            int eleccionseres = Utils.leerEntero("Elija que quiere compra introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    jugadorjuega.comprarSeres(titanothere, omaticaya);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + omaticaya.getNombre());
                                                    break;
                                                case 2:
                                                    jugadorjuega.comprarSeres(titanothere, tipani);
                                                    System.out.println(jugadorjuega.getNombre() + " ha comprado un " + tipani.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(jugadorjuega.getEjercito());
                                break;

                            case 4:
                                System.out.println(jugadorjuega.getNombre() + " no ha comprado ninguna unidad");
                        }
                    }
                }
                break;
            } catch (PuntosdeCompraInsuficientesException pci) {
                System.out.println("NO TIENE SUFICIENTES PUNTOS PARA COMPRAR ESTA UNIDAD");
            }
        }
    }
}
