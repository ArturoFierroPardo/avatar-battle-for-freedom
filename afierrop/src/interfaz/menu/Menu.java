/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.menu;

import modelos.coordenadas.Coordenadas;
import modelos.equipo.Equipo;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
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

    /**
     * Indica el jugador que esta en el turno jugando
     */
    private Jugador jugadorjuega;

    /**
     * Indica el escenario en el que se esta jugando
     */
    private Escenario escenario;

    /**
     * Constructor de menu que necesita el jugador que este jugando actualmente
     * en el turno y el escenario
     *
     * @param jugadorjuega
     */
    public Menu(Jugador jugadorjuega, Escenario escenario) {
        this.jugadorjuega = jugadorjuega;
        this.escenario = escenario;
    }

    /**
     * * Funcion enseñarMenu() que al llamarse enseña por pantala las distintas
     * opciones que tiene el jugador.Dependiendo de cual escoja se llamara a una
     * funcion especifica
     *
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void enseñarMenu() throws PuntosdeCompraInsuficientesException, FueradeMapaException {
        System.out.println("\\\\\\\\\\\\\\\\\\\\MENU/////////////////////");
        System.out.println("1) Comprar una unidad");
        System.out.println("2) Mover una unidad");
        System.out.println("3) Atacar a una unidad enemiga");
        System.out.println("4) Finalizar el turno");
        int opcion = Utils.leerEntero("Elija que quiere hacer escribiendo el numero correspondiente: ");
        switch (opcion) {
            case 1:
                compraUnidad();
            /*Cambiar turno */
            case 2:
            /*Mover unidad*/
 /*Cambiar turno */
            case 3:
            /*Atacar a una unidad*/
 /*Cambiar turno */
            case 4:
            /*Cambiar turno*/

        }
    }

    /**
     * Funcion comprarUnidad(). Primero pide cuantas unidades desea comprar el
     * jugador y en funcion de eso el mercado se repetira x veces. Después ve si
     * el jugador es de un equipo o de otro, en funcion de ello se crearan unas
     * unidades u otras y se le dara a elegir entre unas unidad u otras al
     * jugador. Se le pedira que elija si quiere comprar alguna o si no quiere;
     * si quiere se añade al ejercito del jugador; se compran llamando a una
     * funcion de compra en la clase jugador que si no tiene los puntos
     * necesarios lanza una excepcion. También, al comprar la unidad, se le
     * asigna una posicion aleatoria del mapa y un dueño que es el jugador que
     * lo compra. Una vez comprada la unidad, se le pregunta al usuario si
     * quiere comprarle seres o no pero siempre que quiera comprar se le pedira
     * como maximo el numero de seres que necesite la maquina. Una vez comprados
     * los seres, se añaden a la tripulacion de dicha unidad; se compran
     * mediante una funcion de compra en la clase usuario que si no tiene los
     * puntos necesarios se lanza una excepcion. Una vez haya efectuado toda su
     * compra se acaba su turno. Si el jugador no tiene los puntos necesarios
     * para comprar una unidad o ser se lanzara una excepcion desde la funcion
     * compra tanto de seres o de unidades en la clase jugador, que sera
     * cacheada en esta funcion (por ello el try catch). Si se atrapa la
     * excepcion saldra un mensaje por pantalla de que no puede comprarlo,
     * además, de que gracias al bucle while(true) se repetira el proceso de
     * compra. Si no se lanza ninguna excepcion el bucle while(true) se acaba
     * gracias a un break efectuado al acabar todas las funciones anteriores de
     * compra.
     */
    private void compraUnidad() throws PuntosdeCompraInsuficientesException, FueradeMapaException {
        boolean bucle = true;
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\MERCADO GENERAL//////////////////////");
        int nunidades = Utils.leerEntero("Cuantas unidades desea comprar?");
        for (int j = 0; j < nunidades; j++) {
            while (bucle) {
                try {

                    if (this.jugadorjuega.getEquipo() == Equipo.RDA) {
                        Unidad at99 = new AT99();
                        Unidad aerospatiale = new Aerospatiale();
                        Unidad mk6 = new MK6();
                        Coordenadas posicion = new Coordenadas(this.escenario);
                        System.out.println("\\\\\\\\\\\\\\\\\\\\\\MERCADO DE UNIDADES//////////////////////: ");
                        System.out.println("1) Comprar AT99 - Escorpion: \n" + at99.toStringInfo());
                        System.out.println("2) Comprar Aerospatiale SA-2 Samson: \n" + aerospatiale.toStringInfo());
                        System.out.println("3) Comprar Plataforma de Movilidad Amplificada Mitsubishi MK-6: \n" + mk6.toStringInfo());
                        System.out.println("4) No comprar nada");
                        int eleccionunidades = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");

                        switch (eleccionunidades) {
                            case 1:
                                this.jugadorjuega.comprarUnidad(at99);
                                at99.setJugador(this.jugadorjuega);
                                at99.setPosicion(posicion);
                                int eleccionserescompra1 = Utils.leerEntero("Desea comprar seres que tripulen esta unidad? Necesita " + at99.getSeresneed() + " para poder estar operativo \n1) SI \n2) NO");
                                switch (eleccionserescompra1) {
                                    case 1:
                                        int nseres = Utils.leerEntero("Cuantos seres desea comprar que no supere " + at99.getSeresneed() + "?");
                                        for (int i = 0; i < nseres; i++) {
                                            Ser comandante = new Comandante();
                                            Ser artillero = new Artillero();
                                            System.out.println("\\\\\\\\\\\\\\\\\\\\\\MERCADO DE SERES://////////////////////");
                                            System.out.println("1) Comprar Comandante: " + comandante.toStringInfo());
                                            System.out.println("2) Comprar Artillero: " + artillero.toStringInfo());
                                            System.out.println("3) No comprar nada");
                                            int eleccionseres = Utils.leerEntero("Elija que quiere compra introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    this.jugadorjuega.comprarSeres(at99, comandante);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + comandante.getNombre());
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(at99, artillero);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + artillero.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(this.jugadorjuega.getEjercito());
                                break;
                            case 2:
                                this.jugadorjuega.comprarUnidad(aerospatiale);
                                aerospatiale.setJugador(this.jugadorjuega);
                                aerospatiale.setPosicion(posicion);
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
                                                    this.jugadorjuega.comprarSeres(aerospatiale, comandante);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + comandante.getNombre());
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(aerospatiale, artillero);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + artillero.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(this.jugadorjuega.getEjercito());
                                break;
                            case 3:
                                this.jugadorjuega.comprarUnidad(mk6);
                                mk6.setJugador(this.jugadorjuega);
                                mk6.setPosicion(posicion);
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
                                                    this.jugadorjuega.comprarSeres(mk6, comandante);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + comandante.getNombre());
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(mk6, artillero);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + artillero.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(this.jugadorjuega.getEjercito());
                                break;
                            case 4:
                                System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ninguna unidad");
                                break;
                        }
                    }
                    if (this.jugadorjuega.getEquipo() == Equipo.Navi) {
                        Unidad banshee = new Banshee();
                        Unidad thanator = new Thanator();
                        Unidad titanothere = new Titanothere();
                        Coordenadas posicion = new Coordenadas(this.escenario);
                        System.out.println("MERCADO DE UNIDADES: ");
                        System.out.println("1) Comprar Banshee de Montaña: \n" + banshee.toStringInfo());
                        System.out.println("2) Comprar Thanator: \n" + thanator.toStringInfo());
                        System.out.println("3) Comprar Titanothere Cabezamartillo: \n" + titanothere.toStringInfo());
                        System.out.println("4) No comprar nada");
                        int eleccionunidades = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");

                        switch (eleccionunidades) {
                            case 1:
                                this.jugadorjuega.comprarUnidad(banshee);
                                banshee.setJugador(this.jugadorjuega);
                                banshee.setPosicion(posicion);
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
                                                    this.jugadorjuega.comprarSeres(banshee, omaticaya);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + omaticaya.getNombre());
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(banshee, tipani);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + tipani.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(this.jugadorjuega.getEjercito());
                                break;
                            case 2:
                                this.jugadorjuega.comprarUnidad(thanator);
                                thanator.setJugador(this.jugadorjuega);
                                thanator.setPosicion(posicion);
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
                                                    this.jugadorjuega.comprarSeres(thanator, omaticaya);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + omaticaya.getNombre());
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(thanator, tipani);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + tipani.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(this.jugadorjuega.getEjercito());
                                break;

                            case 3:
                                this.jugadorjuega.comprarUnidad(titanothere);
                                titanothere.setJugador(this.jugadorjuega);
                                titanothere.setPosicion(posicion);
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
                                                    this.jugadorjuega.comprarSeres(titanothere, omaticaya);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + omaticaya.getNombre());
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(titanothere, tipani);
                                                    System.out.println(this.jugadorjuega.getNombre() + " ha comprado un " + tipani.getNombre());
                                                    break;
                                                case 3:
                                                    System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2:
                                        System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ningun ser");
                                        break;
                                }
                                System.out.println(this.jugadorjuega.getEjercito());
                                break;

                            case 4:
                                System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ninguna unidad");
                        }
                    }

                    break;
                } catch (PuntosdeCompraInsuficientesException pci) {
                    System.out.println("NO TIENE SUFICIENTES PUNTOS PARA COMPRAR ESTA UNIDAD");

                }
            }
        }
    }
}
