/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz.menu;

import interfaz.centrodemandos.CentroDeMandos;
import java.util.Date;
import java.util.Objects;
import modelos.equipo.Equipo;
import modelos.escenario.Escenario;
import modelos.excepciones.FueradeMapaException;
import modelos.excepciones.IndexOutofBounceException;
import modelos.excepciones.PuntosdeCompraInsuficientesException;
import modelos.excepciones.SinEnergiaAtaqueException;
import modelos.excepciones.SinEnergiaMovimientoException;
import modelos.excepciones.SinTripulantesSuficientesException;
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
     * Centro de mandos del jugador que esta en su turno jugando
     */
    private CentroDeMandos centrodemandos;

    /**
     * Centro de mandos del jugador que no esta en su turno y sin jugar
     */
    private CentroDeMandos centrodemandosnojuega;

    /**
     * Constructor de menu que necesita el jugador que este jugando actualmente
     * en el turno y el escenario
     *
     * @param jugadorjuega
     * @param escenario
     * @param centrodemandos
     * @param centrodemandosnojuega
     */
    public Menu(Jugador jugadorjuega, Escenario escenario, CentroDeMandos centrodemandos, CentroDeMandos centrodemandosnojuega) {
        this.jugadorjuega = jugadorjuega;
        this.escenario = escenario;
        this.centrodemandos = centrodemandos;
        this.centrodemandosnojuega = centrodemandosnojuega;
    }

    /**
     * Funcion mostrarMenu() que al llamarse enseña por pantala las distintas
     * opciones que tiene el jugador.Dependiendo de cual escoja se llamara a una
     * funcion especifica o si es la 4 directamente se cambiara de turno gracias
     * a una funcion en partida.Con un bucle dowhile se registra y se repita si
     * el usuario ha introducido la opcion mal. Añade al centro de mandos la
     * accion de cada opcion del menu.
     *
     * @throws modelos.excepciones.PuntosdeCompraInsuficientesException
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     */
    public void mostrarMenu() throws PuntosdeCompraInsuficientesException, FueradeMapaException, SinEnergiaMovimientoException, SinEnergiaAtaqueException, SinTripulantesSuficientesException {
        boolean bucle = false;
        while (true) {
            do {
                try {
                    System.out.println("Turno de " + this.jugadorjuega.getNombre() + "\n");
                    System.out.println("-------------------MENU-------------------\n");
                    System.out.println("\t1) Comprar una unidad");
                    System.out.println("\t2) Mover una unidad");
                    System.out.println("\t3) Atacar a una unidad enemiga");
                    System.out.println("\t4) Ver el centro de mandos");
                    System.out.println("\t5) Finalizar el turno");
                    System.out.println("\t6) Finalizar y guardar partida\n");
                    int opcion = Utils.leerEntero("Elija que quiere hacer escribiendo el numero correspondiente: ");
                    switch (opcion) {
                        case 1:
                            Unidad unidadcompra = compraUnidad();
                            Date compra = new Date();
                            if (unidadcompra == null) {
                                String comprano = "El jugador " + this.jugadorjuega.getNombre() + " ha intentado comprar una unidad sin exito ---------->" + compra + "\n";
                                this.centrodemandos.anadirAccion(comprano);
                                this.centrodemandosnojuega.anadirAccion(comprano);
                            } else {
                                String compradoinfo = "El jugador " + this.jugadorjuega.getNombre() + " ha comprado un " + unidadcompra.getNombre() + ", y lo ha desplegado en el campo de batalla en la posicion " + unidadcompra.getPosicion().toString() + " ---------->" + compra + "\n";
                                String comprado = "El jugador " + this.jugadorjuega.getNombre() + " ha comprado un " + unidadcompra.getNombre() + ", y lo ha desplegado en el campo de batalla ---------->" + compra + "\n";
                                this.centrodemandos.anadirAccion(compradoinfo);
                                this.centrodemandosnojuega.anadirAccion(comprado);
                            }
                            break;
                        case 2:
                            if (this.jugadorjuega.getEjercito().getUnidades().isEmpty()) {
                                bucle = true;
                                Date mover = new Date();
                                String movido = "El jugador " + this.jugadorjuega.getNombre() + " ha intentado mover una unidad sin exito ---------->" + mover + "\n";
                                this.centrodemandos.anadirAccion(movido);
                                this.centrodemandosnojuega.anadirAccion(movido);
                                throw new IndexOutofBounceException();
                            }
                            if (this.jugadorjuega.getEjercito().getUnidades().size() >= 0) {
                                bucle = false;
                                Unidad unidadmover = moverUnidad();
                                Date mover = new Date();
                                if (unidadmover == null) {
                                    String movido = "El jugador " + this.jugadorjuega.getNombre() + " ha intentado mover una unidad sin exito ---------->" + mover + "\n";
                                    this.centrodemandos.anadirAccion(movido);
                                    this.centrodemandosnojuega.anadirAccion(movido);
                                } else {
                                    String movido = "El jugador " + this.jugadorjuega.getNombre() + " ha movido su " + unidadmover.getNombre() + " ---------->" + mover + "\n";
                                    String movidoinfo = "El jugador " + this.jugadorjuega.getNombre() + " ha movido su " + unidadmover.getNombre() + " a la posicion " + unidadmover.getPosicion().toString() + " ---------->" + mover + "\n";
                                    this.centrodemandos.anadirAccion(movidoinfo);
                                    this.centrodemandosnojuega.anadirAccion(movido);
                                }
                            }
                            break;
                        case 3:
                            if (this.jugadorjuega.getEjercito().getUnidades().isEmpty()) {
                                bucle = true;
                                Date atacar = new Date();
                                String atacado = "El jugador " + this.jugadorjuega.getNombre() + " ha intentado atacar a una unidad sin exito ---------->" + atacar + "\n";
                                this.centrodemandos.anadirAccion(atacado);
                                this.centrodemandosnojuega.anadirAccion(atacado);
                                throw new IndexOutofBounceException();
                            }
                            if (this.jugadorjuega.getEjercito().getUnidades().size() >= 0) {
                                bucle = false;
                                String ataque = atacarUnidad();
                                Date atacar = new Date();
                                if (ataque.isBlank()) {
                                    String noatacado = "El jugador " + this.jugadorjuega.getNombre() + " ha intentado atacar a una unidad sin exito ---------->" + atacar + "\n";
                                    this.centrodemandos.anadirAccion(noatacado);
                                    this.centrodemandosnojuega.anadirAccion(noatacado);
                                } else {
                                    this.centrodemandos.anadirAccion(ataque);
                                    this.centrodemandosnojuega.anadirAccion(ataque);
                                }
                            }
                            break;
                        case 4:
                            this.centrodemandos.mostrarCentroDeMandos();
                            Date vercm = new Date();
                            String cm = "El jugador " + this.jugadorjuega.getNombre() + " ha abierto su centro de mandos ---------->" + vercm + "\n";
                            this.centrodemandos.anadirAccion(cm);
                            this.centrodemandosnojuega.anadirAccion(cm);
                            bucle = true;
                            break;
                        case 5:
                            System.out.println(this.jugadorjuega.getNombre() + " ha pasado de turno \n");
                            Date turnos = new Date();
                            String turno = "El jugador " + this.jugadorjuega.getNombre() + " ha pasado el turno ---------->" + turnos + "\n";
                            this.centrodemandos.anadirAccion(turno);
                            this.centrodemandosnojuega.anadirAccion(turno);
                            bucle = false;
                            break;
                        case 6:
                            Date fin = new Date();
                            String finyguardar = "El jugador " + this.jugadorjuega.getNombre() + " ha guardado y finalizado la partida ---------->" + fin + "\n";
                            this.centrodemandos.anadirAccion(finyguardar);
                            this.centrodemandosnojuega.anadirAccion(finyguardar);
                            this.jugadorjuega.setAcabarPartida(true);
                            bucle = false;
                            break;
                        default:
                            System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                            bucle = true;
                            break;
                    }
                } catch (IndexOutofBounceException iob) {
                    System.out.println("\tOPCION INVALIDA. NO PUEDE MOVER UNIDADES NI ATACAR CON ELLAS PORQUE NO TIENE. VUELVA A INTENTARLO CORRECTAMENTE\n");
                }
            } while (bucle);
            break;
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
     * no, si es no, se repite. Si una unidad no ha comprado los suficientes
     * seres para estar operativo se quita del ejercito.
     */
    private Unidad compraUnidad() throws PuntosdeCompraInsuficientesException, FueradeMapaException {
        Unidad unidad = null;
        boolean bucle = true;
        boolean bucleunidades = false;
        boolean bucleseres = false;
        boolean buclenunidades = true;
        System.out.println("-------------------MERCADO GENERAL-------------------\n");
        while (buclenunidades) {
            int nunidades = Utils.leerEntero("Cuantas unidades desea comprar?");
            if (nunidades < 0) {
                System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");

            } else {
                buclenunidades = false;
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
                                            if (at99.getSeresneed() > at99.getTripulantes().size() || at99.getTripulantes().isEmpty()) {
                                                this.jugadorjuega.getEjercito().getUnidades().remove(at99);
                                                unidad = null;
                                            }
                                            System.out.println(this.jugadorjuega.getEjercito());
                                            bucleunidades = false;
                                            unidad = at99;
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
                                            if (aerospatiale.getSeresneed() > aerospatiale.getTripulantes().size() || aerospatiale.getTripulantes().isEmpty()) {
                                                this.jugadorjuega.getEjercito().getUnidades().remove(aerospatiale);
                                                unidad = null;
                                            }
                                            System.out.println(this.jugadorjuega.getEjercito());
                                            bucleunidades = false;
                                            unidad = aerospatiale;
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
                                            if (mk6.getSeresneed() > mk6.getTripulantes().size() || mk6.getTripulantes().isEmpty()) {
                                                this.jugadorjuega.getEjercito().getUnidades().remove(mk6);
                                                unidad = null;
                                            }
                                            System.out.println(this.jugadorjuega.getEjercito());
                                            bucleunidades = false;
                                            unidad = mk6;
                                            break;
                                        case 4:
                                            System.out.println("\n" + this.jugadorjuega.getNombre() + " no ha comprado ninguna unidad\n");
                                            bucleunidades = false;
                                            break;
                                        default:
                                            System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                                            bucleunidades = true;
                                            unidad = null;
                                            break;
                                    }
                                } while (bucleunidades);
                                return unidad;
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
                                            if (banshee.getSeresneed() > banshee.getTripulantes().size() || banshee.getTripulantes().isEmpty()) {
                                                this.jugadorjuega.getEjercito().getUnidades().remove(banshee);
                                                unidad = null;
                                            }
                                            System.out.println(this.jugadorjuega.getEjercito());
                                            bucleunidades = false;
                                            unidad = banshee;
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
                                            if (thanator.getSeresneed() > thanator.getTripulantes().size() || thanator.getTripulantes().isEmpty()) {
                                                this.jugadorjuega.getEjercito().getUnidades().remove(thanator);
                                                unidad = null;
                                            }
                                            System.out.println(this.jugadorjuega.getEjercito());
                                            bucleunidades = false;
                                            unidad = thanator;
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
                                            if (titanothere.getSeresneed() > titanothere.getTripulantes().size() || titanothere.getTripulantes().isEmpty()) {
                                                this.jugadorjuega.getEjercito().getUnidades().remove(titanothere);
                                                unidad = null;
                                            }
                                            System.out.println(this.jugadorjuega.getEjercito());
                                            bucleunidades = false;
                                            unidad = titanothere;
                                            break;

                                        case 4:
                                            System.out.println(this.jugadorjuega.getNombre() + " no ha comprado ninguna unidad\n");
                                            bucleunidades = false;
                                            unidad = null;
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
        }
        return unidad;
    }

    /**
     * Funcion mover unidad que mueve la unidad chequeando si hay o no una
     * unidad en la nueva posicion de movimiento gracias a la llamada de la
     * funcion moverunidad de jugador que llama a la de unidad y restandole la
     * energia de mov indicada de la clase unidad.
     *
     * @return 
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaMovimientoException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     */
    public Unidad moverUnidad() throws FueradeMapaException, SinEnergiaMovimientoException, SinTripulantesSuficientesException {
        boolean buclemover = false;
        System.out.println(this.jugadorjuega.getEjercito());
        while (true) {
            try {
                do {
                    int eleccionmover = Utils.leerEntero("\nQue unidad de su ejercito desea mover? Pulse el 0 para no mover ninguna unidad") - 1;
                    if (this.jugadorjuega.getEjercito().getUnidades().isEmpty() || this.jugadorjuega.getEjercito().getUnidades().size() < eleccionmover + 1 || eleccionmover < -1) {
                        buclemover = true;
                        throw new IndexOutofBounceException();
                    }
                    if (eleccionmover == -1) {
                        System.out.println("\n" + this.jugadorjuega.getNombre() + " no ha movido ninguna unidad");
                        buclemover = false;
                    }
                    if (this.jugadorjuega.getEjercito().getUnidades().size() >= eleccionmover && eleccionmover != -1) {
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getTripulantes().size() < this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getSeresneed()) {
                            buclemover = true;
                            throw new SinTripulantesSuficientesException();
                        } else {
                            this.jugadorjuega.restarEnergia(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            Unidad unidad = this.jugadorjuega.moverUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover));
                            System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getNombre() + " se ha movida a " + this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getPosicion());
                            buclemover = false;
                            return unidad;
                        }
                    }
                } while (buclemover);
                break;
            } catch (IndexOutofBounceException iob) {
                System.out.println("\tOPCION INVALIDA. NO TIENE TANTAS UNIDADES. VUELVA A INTENTARLO CORRECTAMENTE\n");
            } catch (SinEnergiaMovimientoException sem) {
                System.out.println("\tOPCION INVALIDA. SU UNIDAD NO TIENE ENERGIA PARA MOVERSE\n");
                break;
            } catch (SinTripulantesSuficientesException stf) {
                System.out.println("\tOPCION INVALIDA. SU UNIDAD NO TIENE TRIPULANTES SUFICIENTES PARA MOVERSE. SELECCIONE OTRA UNIDAD\n");
            }
        }
        return null;
    }

    /**
     * Funcion atacar unidad, ataca llamando a la funcion atacar de jugador, que
     * llama a la de unidad.Resta energia de ataque a la unidad y a la enemiga
 la de defensa en la clase jugador y unidad. Puede haber fuego amigo.
     *
     * @return 
     * @throws modelos.excepciones.IndexOutofBounceException
     * @throws modelos.excepciones.FueradeMapaException
     * @throws modelos.excepciones.SinEnergiaAtaqueException
     * @throws modelos.excepciones.SinTripulantesSuficientesException
     */
    public String atacarUnidad() throws IndexOutofBounceException, FueradeMapaException, SinEnergiaAtaqueException, SinTripulantesSuficientesException {
        boolean bucleatacar = true;
        System.out.println(this.jugadorjuega.getEjercito());
        while (true) {
            try {
                do {
                    int eleccionatacar = Utils.leerEntero("\nCon que unidad de su ejercito desea atacar? Pulse el 0 para no atacar con ninguna unidad.") - 1;
                    if (this.jugadorjuega.getEjercito().getUnidades().isEmpty() || this.jugadorjuega.getEjercito().getUnidades().size() < eleccionatacar + 1 || eleccionatacar < -1) {
                        bucleatacar = true;
                        throw new IndexOutofBounceException();
                    }
                    if (eleccionatacar == -1) {
                        System.out.println("\n" + this.jugadorjuega.getNombre() + " no ha atacado con ninguna unidad");
                        bucleatacar = false;
                    }
                    if (this.jugadorjuega.getEjercito().getUnidades().size() >= eleccionatacar && eleccionatacar != -1) {
                        if (this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getTripulantes().size() < this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar).getSeresneed()) {
                            bucleatacar = true;
                            throw new SinTripulantesSuficientesException();
                        } else {
                            this.jugadorjuega.restarEnergiaAtaque(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar));
                            String ataque = this.jugadorjuega.atacarUnidad(this.jugadorjuega.getEjercito().getUnidades().get(eleccionatacar));
                            bucleatacar = false;
                            return ataque;
                        }
                    }
                } while (bucleatacar);
                break;
            } catch (IndexOutofBounceException iob) {
                System.out.println("\tOPCION INVALIDA. NO TIENE TANTAS UNIDADES. VUELVA A INTENTARLO CORRECTAMENTE\n");
            } catch (SinEnergiaAtaqueException sea) {
                System.out.println("\tOPCION INVALIDA. NO TIENE ENERGIA PARA ATACAR.\n");
                break;
            } catch (SinTripulantesSuficientesException stf) {
                System.out.println("\tOPCION INVALIDA. SU UNIDAD NO TIENE TRIPULANTES SUFICIENTES PARA ATACAR. SELECCIONE OTRA UNIDAD\n");
            }
        }
        return "";
    }

    /**
     * Override de la funcion equals para comparar si dos menus son iguales
     *
     * @param o
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Menu) {
            Menu m = (Menu) o;
            return Objects.equals(this.jugadorjuega, m.jugadorjuega)
                    && Objects.equals(this.escenario, m.escenario);
        } else {
            return false;
        }
    }

    /**
     * Override del hashcode para esta clase
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.jugadorjuega);
        hash = 97 * hash + Objects.hashCode(this.escenario);
        return hash;
    }

}
