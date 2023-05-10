/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.menu;

import modelos.coordenadas.Coordenadas;
import modelos.equipo.Equipo;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.IndexOutofBounceException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaMovimientoException;
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
     * @param escenario
     */
    public Menu(Jugador jugadorjuega, Escenario escenario) {
        this.jugadorjuega = jugadorjuega;
        this.escenario = escenario;
    }

    /**
     * * Funcion enseñarMenu() que al llamarse enseña por pantala las distintas
     * opciones que tiene el jugador.Dependiendo de cual escoja se llamara a una
     * funcion especifica o si es la 4 directamente se cambiara de turno gracias
     * a una funcion en partida. Con un bucle dowhile se registra y se repita si
     * el usuario ha introducido la opcion mal.
     *
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void enseñarMenu() throws PuntosdeCompraInsuficientesException, FueradeMapaException, SinEnergiaMovimientoException {
        boolean bucle = false;
        System.out.println("Turno de " + this.jugadorjuega.getNombre() + "\n");
        System.out.println("-------------------MENU-------------------\n");
        System.out.println("\t1) Comprar una unidad");
        System.out.println("\t2) Mover una unidad");
        System.out.println("\t3) Atacar a una unidad enemiga");
        System.out.println("\t4) Finalizar el turno\n");
        while (true) {
            try {
                do {
                    int opcion = Utils.leerEntero("Elija que quiere hacer escribiendo el numero correspondiente: ");
                    switch (opcion) {
                        case 1:
                            compraUnidad();
                            bucle = false;
                            break;
                        case 2:
                            if (this.jugadorjuega.getEjercito().getUnidades().isEmpty()) {
                                bucle = true;
                                throw new IndexOutofBounceException();
                            }
                            if (this.jugadorjuega.getEjercito().getUnidades().size() >= 0) {
                                bucle = false;
                                moverUnidad();
                            }
                            break;
                        case 3:
                            if (this.jugadorjuega.getEjercito().getUnidades().isEmpty()) {
                                bucle = true;
                                throw new IndexOutofBounceException();
                            }
                            if (this.jugadorjuega.getEjercito().getUnidades().size() >= 0) {
                                bucle = false;
                                atacarUnidad();
                            }
                            break;
                        case 4:
                            bucle = false;
                            break;
                        default:
                            System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                            bucle = true;
                            break;

                    }

                } while (bucle);
                break;
            } catch (IndexOutofBounceException iob) {
                System.out.println("\tOPCION INVALIDA. NO PUEDE MOVER UNIDADES PORQUE NO TIENE. VUELVA A INTENTARLO CORRECTAMENTE\n");

            }
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
     * lo compra. Una vez comprada la unidad, se obligara al usairo a comprar
     * los seres necesarios para la unidad. Una vez comprados los seres, se
     * añaden a la tripulacion de dicha unidad; se compran mediante una funcion
     * de compra en la clase usuario que si no tiene los puntos necesarios se
     * lanza una excepcion. Una vez haya efectuado toda su compra se acaba su
     * turno. Si el jugador no tiene los puntos necesarios para comprar una
     * unidad o ser se lanzara una excepcion desde la funcion compra tanto de
     * seres o de unidades en la clase jugador, que sera cacheada en esta
     * funcion (por ello el try catch). Si se atrapa la excepcion saldra un
     * mensaje por pantalla de que no puede comprarlo, además, de que gracias al
     * bucle while(true) se repetira el proceso de compra. Si no se lanza
     * ninguna excepcion el bucle while(true) se acaba gracias a un break
     * efectuado al acabar todas las funciones anteriores de compra. Gracias a
     * un bucle dowhile se registra si el usuario ha metido la opcion correcta o
     * no, si es no, se repite.
     */
    private void compraUnidad() throws PuntosdeCompraInsuficientesException, FueradeMapaException {
        boolean bucle = true;
        boolean bucleunidades = false;
        boolean bucleseres = false;
        System.out.println("-------------------MERCADO GENERAL-------------------\n");
        int nunidades = Utils.leerEntero("Cuantas unidades desea comprar?");
        for (int j = 0; j < nunidades; j++) {
            while (bucle) {
                try {
                    if (this.jugadorjuega.getEquipo() == Equipo.RDA) {
                        Unidad at99 = new AT99();
                        Unidad aerospatiale = new Aerospatiale();
                        Unidad mk6 = new MK6();
                        System.out.println("\n-------------------MERCADO DE UNIDADES-------------------\n");
                        System.out.println("1) Comprar AT99 - Escorpion: \n" + at99.toStringInfo());
                        System.out.println("2) Comprar Aerospatiale SA-2 Samson: \n" + aerospatiale.toStringInfo());
                        System.out.println("3) Comprar Plataforma de Movilidad Amplificada Mitsubishi MK-6: \n" + mk6.toStringInfo());
                        System.out.println("4) No comprar nada\n");
                        do {
                            int eleccionunidades = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                            switch (eleccionunidades) {
                                case 1:
                                    this.jugadorjuega.comprarUnidad(at99);
                                    at99.setJugador(this.jugadorjuega);
                                    for (int i = 0; i < at99.getSeresneed(); i++) {
                                        Ser comandante = new Comandante();
                                        Ser artillero = new Artillero();
                                        System.out.println("\n-------------------MERCADO DE SERES-------------------\n");
                                        System.out.println("1) Comprar Comandante: \n" + comandante.toStringInfo());
                                        System.out.println("2) Comprar Artillero: \n" + artillero.toStringInfo());
                                        do {
                                            int eleccionseres = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    this.jugadorjuega.comprarSeres(at99, comandante);
                                                    bucleseres = false;
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(at99, artillero);
                                                    bucleseres = false;
                                                    break;
                                                default:
                                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                                    bucleseres = true;
                                                    break;
                                            }
                                        } while (bucleseres);
                                    }
                                    System.out.println(this.jugadorjuega.getEjercito());
                                    bucleunidades = false;
                                    break;
                                case 2:
                                    this.jugadorjuega.comprarUnidad(aerospatiale);
                                    aerospatiale.setJugador(this.jugadorjuega);
                                    for (int i = 0; i < aerospatiale.getSeresneed(); i++) {
                                        Ser comandante = new Comandante();
                                        Ser artillero = new Artillero();
                                        System.out.println("\n-------------------MERCADO DE SERES-------------------\n");
                                        System.out.println("1) Comprar Comandante: \n" + comandante.toStringInfo());
                                        System.out.println("2) Comprar Artillero: \n" + artillero.toStringInfo());
                                        do {
                                            int eleccionseres = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    this.jugadorjuega.comprarSeres(aerospatiale, comandante);
                                                    bucleseres = false;
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(aerospatiale, artillero);
                                                    bucleseres = false;
                                                    break;
                                                default:
                                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                                    bucleseres = true;
                                                    break;

                                            }

                                        } while (bucleseres);
                                    }

                                    System.out.println(this.jugadorjuega.getEjercito());
                                    bucleunidades = false;
                                    break;
                                case 3:
                                    this.jugadorjuega.comprarUnidad(mk6);
                                    mk6.setJugador(this.jugadorjuega);
                                    for (int i = 0; i < mk6.getSeresneed(); i++) {
                                        Ser comandante = new Comandante();
                                        Ser artillero = new Artillero();
                                        System.out.println("\n-------------------MERCADO DE SERES-------------------\n");
                                        System.out.println("1) Comprar Comandante: \n" + comandante.toStringInfo());
                                        System.out.println("2) Comprar Artillero: \n" + artillero.toStringInfo());
                                        do {
                                            int eleccionseres = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    this.jugadorjuega.comprarSeres(mk6, comandante);
                                                    bucleseres = false;
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(mk6, artillero);
                                                    bucleseres = false;
                                                    break;
                                                default:
                                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                                    bucleseres = true;
                                                    break;
                                            }

                                        } while (bucleseres);
                                    }

                                    System.out.println(this.jugadorjuega.getEjercito());
                                    bucleunidades = false;
                                    break;
                                case 4:
                                    System.out.println("\n" + this.jugadorjuega.getNombre() + " no ha comprado ninguna unidad\n");
                                    bucleunidades = false;
                                    break;
                                default:
                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                    bucleunidades = true;
                                    break;
                            }
                        } while (bucleunidades);

                    } else {
                        Unidad banshee = new Banshee();
                        Unidad thanator = new Thanator();
                        Unidad titanothere = new Titanothere();
                        System.out.println("\n-------------------MERCADO DE UNIDADES-------------------\n");
                        System.out.println("\t1) Comprar Banshee de Montaña: \n" + banshee.toStringInfo());
                        System.out.println("\t2) Comprar Thanator: \n" + thanator.toStringInfo());
                        System.out.println("\t3) Comprar Titanothere Cabezamartillo: \n" + titanothere.toStringInfo());
                        System.out.println("\t4) No comprar nada");
                        do {
                            int eleccionunidades = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                            switch (eleccionunidades) {
                                case 1:
                                    this.jugadorjuega.comprarUnidad(banshee);
                                    banshee.setJugador(this.jugadorjuega);
                                    for (int i = 0; i < banshee.getSeresneed(); i++) {
                                        Ser omaticaya = new Omaticaya();
                                        Ser tipani = new Tipani();
                                        System.out.println("\n-------------------MERCADO DE SERES-------------------\n");
                                        System.out.println("1) Comprar Omaticaya: \n" + omaticaya.toStringInfo());
                                        System.out.println("2) Comprar Tipani: \n" + tipani.toStringInfo());
                                        do {
                                            int eleccionseres = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    this.jugadorjuega.comprarSeres(banshee, omaticaya);
                                                    bucleseres = false;
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(banshee, tipani);
                                                    bucleseres = false;
                                                    break;
                                                default:
                                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                                    bucleseres = true;
                                                    break;
                                            }
                                        } while (bucleseres);
                                    }
                                    System.out.println(this.jugadorjuega.getEjercito());
                                    bucleunidades = false;
                                    break;
                                case 2:
                                    this.jugadorjuega.comprarUnidad(thanator);
                                    thanator.setJugador(this.jugadorjuega);
                                    for (int i = 0; i < thanator.getSeresneed(); i++) {
                                        Ser omaticaya = new Omaticaya();
                                        Ser tipani = new Tipani();
                                        System.out.println("\n-------------------MERCADO DE SERES-------------------\n");
                                        System.out.println("1) Comprar Omaticaya: \n" + omaticaya.toStringInfo());
                                        System.out.println("2) Comprar Tipani: \n" + tipani.toStringInfo());
                                        do {
                                            int eleccionseres = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    this.jugadorjuega.comprarSeres(thanator, omaticaya);
                                                    bucleseres = false;
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(thanator, tipani);
                                                    bucleseres = false;
                                                    break;
                                                default:
                                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                                    bucleseres = true;
                                                    break;
                                            }
                                        } while (bucleseres);
                                    }
                                    System.out.println(this.jugadorjuega.getEjercito());
                                    bucleunidades = false;
                                    break;

                                case 3:
                                    this.jugadorjuega.comprarUnidad(titanothere);
                                    titanothere.setJugador(this.jugadorjuega);
                                    for (int i = 0; i < titanothere.getSeresneed(); i++) {
                                        Ser omaticaya = new Omaticaya();
                                        Ser tipani = new Tipani();
                                        System.out.println("\n-------------------MERCADO DE SERES-------------------\n");
                                        System.out.println("1) Comprar Omaticaya: \n" + omaticaya.toStringInfo());
                                        System.out.println("2) Comprar Tipani: \n" + tipani.toStringInfo());
                                        do {
                                            int eleccionseres = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                                            switch (eleccionseres) {
                                                case 1:
                                                    this.jugadorjuega.comprarSeres(titanothere, omaticaya);
                                                    bucleseres = false;
                                                    break;
                                                case 2:
                                                    this.jugadorjuega.comprarSeres(titanothere, tipani);
                                                    bucleseres = false;
                                                    break;
                                                default:
                                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                                    bucleseres = true;
                                                    break;
                                            }
                                        } while (bucleseres);
                                    }
                                    System.out.println(this.jugadorjuega.getEjercito());
                                    bucleunidades = false;
                                    break;

                                case 4:
                                    System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ninguna unidad\n");
                                    bucleunidades = false;
                                    break;
                                default:
                                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                    bucleunidades = true;
                                    break;
                            }
                        } while (bucleunidades);
                    }
                    break;
                } catch (PuntosdeCompraInsuficientesException pci) {
                    System.out.println("\tNO TIENE SUFICIENTES PUNTOS PARA COMPRAR ESTA UNIDAD\n");

                }
            }
        }
    }

    /**
     * Funcion para restar energia de movimiento a una unidad en funcion de sus
     * limitaciones.
     */
    private void restarEnergia(Unidad unidad) throws SinEnergiaMovimientoException {
        if (unidad instanceof AT99) {
            if (unidad.getEnergiamov() >= unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())) {
                unidad.setEnergiamov(unidad.getEnergiamov() - (unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())));
            } else {
                throw new SinEnergiaMovimientoException();
            }
        }
        if (unidad instanceof MK6) {
            if (unidad.getEnergiamov() >= unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())) {
                unidad.setEnergiamov(unidad.getEnergiamov() - unidad.getEnergiagastadaxmov());
            } else {
                throw new SinEnergiaMovimientoException();
            }
        }
        if (unidad instanceof Aerospatiale) {
            if (unidad.getEnergiamov() >= unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())) {
                unidad.setEnergiamov(unidad.getEnergiamov() - (unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())));
            } else {
                throw new SinEnergiaMovimientoException();
            }
        }
        if (unidad instanceof Banshee) {
            if (unidad.getEnergiamov() >= unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())) {
                unidad.setEnergiamov(unidad.getEnergiamov() - unidad.getEnergiagastadaxmov());
            } else {
                throw new SinEnergiaMovimientoException();
            }
        }
        if (unidad instanceof Thanator) {
            if (unidad.getEnergiamov() >= unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())) {
                unidad.setEnergiamov(unidad.getEnergiamov() - (unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())));
            } else {
                throw new SinEnergiaMovimientoException();
            }
        }
        if (unidad instanceof Titanothere) {
            if (unidad.getEnergiamov() >= unidad.getEnergiagastadaxmov() * (unidad.getPosicion().getCasillasMovidasX() + unidad.getPosicion().getCasillasMovidasY())) {
                unidad.setEnergiamov(unidad.getEnergiamov() - unidad.getEnergiagastadaxmov());
            } else {
                throw new SinEnergiaMovimientoException();
            }
        }
    }

    /**
     * Funcion mover unidad que mueve la unidad chequeando si hay o no una
     * unidad en la nueva posicion de movimiento y restandole la energia de mov
     * indicada
     */
    private void moverUnidad() throws FueradeMapaException, SinEnergiaMovimientoException {
        boolean buclemover = false;
        System.out.println(this.jugadorjuega.getEjercito());
        while (true) {
            try {
                do {
                    int eleccionmover = Utils.leerEntero("\nQue unidad de su ejercito desea mover? ") - 1;
                    if (this.jugadorjuega.getEjercito().getUnidades().isEmpty() || this.jugadorjuega.getEjercito().getUnidades().size() < eleccionmover + 1 || eleccionmover < 0) {
                        buclemover = true;
                        throw new IndexOutofBounceException();
                    }
                    if (this.jugadorjuega.getEjercito().getUnidades().size() >= eleccionmover) {
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover) instanceof AT99) {
                            this.jugadorjuega.moverUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            restarEnergia(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            buclemover = false;
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover) instanceof MK6) {
                            this.jugadorjuega.moverUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            restarEnergia(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            buclemover = false;
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover) instanceof Aerospatiale) {
                            this.jugadorjuega.moverUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            restarEnergia(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            buclemover = false;
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover) instanceof Banshee) {
                            this.jugadorjuega.moverUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            restarEnergia(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            buclemover = false;
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover) instanceof Thanator) {
                            this.jugadorjuega.moverUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            restarEnergia(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            buclemover = false;
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover) instanceof Titanothere) {
                            this.jugadorjuega.moverUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            restarEnergia(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            buclemover = false;
                        }
                    }
                    System.out.println("\n" + this.jugadorjuega.getNombre() + " ha movido a la unidad " + this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getNombre() + " a la posicion " + this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getPosicion());

                } while (buclemover);
                break;
            } catch (IndexOutofBounceException iob) {
                System.out.println("\tOPCION INVALIDA. NO TIENE TANTAS UNIDADES. VUELVA A INTENTARLO CORRECTAMENTE\n");

            } catch (SinEnergiaMovimientoException sem) {
                System.out.println("\tOPCION INVALIDA. SU UNIDAD NO TIENE ENERGIA PARA MOVERSE\n");
            }

        }

    }

    /**
     * Funcion atacar unidad, ataca siguiendo el rango de ataque de cada unidad
     * generando en su rango una coordenada aleatoria y comprobando si hay una
     * unidad o no ahí
     *
     * @throws modelos.excepciones.IndexOutofBounceException
     * @throws modelos.excepciones.FueradeMapaException
     */
    public void atacarUnidad() throws IndexOutofBounceException, FueradeMapaException {
        boolean bucleatacar = true;
        boolean nounidad = true;
        System.out.println(this.jugadorjuega.getEjercito());
        while (true) {
            try {
                do {
                    int eleccionatacar = Utils.leerEntero("\nCon que unidad de su ejercito desea atacar? ") - 1;
                    if (this.jugadorjuega.getEjercito().getUnidades().isEmpty() || this.jugadorjuega.getEjercito().getUnidades().size() < eleccionatacar + 1 || eleccionatacar < 0) {
                        bucleatacar = true;
                        throw new IndexOutofBounceException();
                    }
                    if (this.jugadorjuega.getEjercito().getUnidades().size() >= eleccionatacar) {
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) instanceof AT99) {
                            if (nounidad) {
                                Coordenadas posicioninvestigar = new Coordenadas(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getX(), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getY(), this.escenario);
                                if (!(this.escenario.checkMapa(posicioninvestigar))) {
                                    this.jugadorjuega.recibirdaño(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), posicioninvestigar);
                                    System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) + " ha atacado a una unidad en la posicion " + posicioninvestigar);
                                    bucleatacar = false;
                                } else {
                                    bucleatacar = false;
                                    nounidad = false;
                                    System.out.println("NO HABIA NINGUNA UNIDAD EN EL RANGO DE ATAQUE");

                                }

                            }
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) instanceof MK6) {
                            if (nounidad) {
                                Coordenadas posicioninvestigar = new Coordenadas(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getX(), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getY(), this.escenario);
                                if (!(this.escenario.checkMapa(posicioninvestigar))) {
                                    this.jugadorjuega.recibirdaño(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), posicioninvestigar);
                                    System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) + " ha atacado a una unidad en la posicion " + posicioninvestigar);
                                    bucleatacar = false;
                                } else {
                                    bucleatacar = false;
                                    nounidad = false;
                                    System.out.println("NO HABIA NINGUNA UNIDAD EN EL RANGO DE ATAQUE");

                                }

                            }
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) instanceof Aerospatiale) {
                            if (nounidad) {
                                Coordenadas posicioninvestigar = new Coordenadas(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getX(), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getY(), this.escenario);
                                if (!(this.escenario.checkMapa(posicioninvestigar))) {
                                    this.jugadorjuega.recibirdaño(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), posicioninvestigar);
                                    System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) + " ha atacado a una unidad en la posicion " + posicioninvestigar);
                                    bucleatacar = false;
                                } else {
                                    bucleatacar = false;
                                    nounidad = false;
                                    System.out.println("NO HABIA NINGUNA UNIDAD EN EL RANGO DE ATAQUE");

                                }

                            }
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) instanceof Banshee) {
                            if (nounidad) {
                                Coordenadas posicioninvestigar = new Coordenadas(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getX(), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getY(), this.escenario);
                                if (!(this.escenario.checkMapa(posicioninvestigar))) {
                                    this.jugadorjuega.recibirdaño(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), posicioninvestigar);
                                    System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) + " ha atacado a una unidad en la posicion " + posicioninvestigar);
                                    bucleatacar = false;
                                } else {
                                    bucleatacar = false;
                                    nounidad = false;
                                    System.out.println("NO HABIA NINGUNA UNIDAD EN EL RANGO DE ATAQUE");

                                }

                            }
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) instanceof Thanator) {
                            if (nounidad) {
                                Coordenadas posicioninvestigar = new Coordenadas(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getX(), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getY(), this.escenario);
                                if (!(this.escenario.checkMapa(posicioninvestigar))) {
                                    this.jugadorjuega.recibirdaño(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), posicioninvestigar);
                                    System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) + " ha atacado a una unidad en la posicion " + posicioninvestigar);
                                    bucleatacar = false;
                                } else {
                                    bucleatacar = false;
                                    nounidad = false;
                                    System.out.println("NO HABIA NINGUNA UNIDAD EN EL RANGO DE ATAQUE");

                                }

                            }
                        }
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) instanceof Titanothere) {
                            if (nounidad) {
                                Coordenadas posicioninvestigar = new Coordenadas(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getX(), this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getPosicion().getY(), this.escenario);
                                if (!(this.escenario.checkMapa(posicioninvestigar))) {
                                    this.jugadorjuega.recibirdaño(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar), posicioninvestigar);
                                    System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar) + " ha atacado a una unidad en la posicion " + posicioninvestigar);
                                    bucleatacar = false;
                                } else {
                                    bucleatacar = false;
                                    nounidad = false;
                                    System.out.println("NO HABIA NINGUNA UNIDAD EN EL RANGO DE ATAQUE");

                                }

                            }
                        }
                    }
                } while (bucleatacar);
                break;
            } catch (IndexOutofBounceException iob) {
                System.out.println("\tOPCION INVALIDA. NO TIENE TANTAS UNIDADES. VUELVA A INTENTARLO CORRECTAMENTE\n");

            }
        }
    }

}
