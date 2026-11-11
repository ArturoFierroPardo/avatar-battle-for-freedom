package interfaz.menu;

import interfaz.centrodemandos.CentroDeMandos;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
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
 * Menu de acciones de un turno: comprar unidades y tripulantes, mover,
 * atacar, consultar el centro de mandos, pasar turno o guardar y salir.
 *
 * @author Arturo Fierro Pardo
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
     * Gestiona la compra de unidades de un turno.
     *
     * <p>
     * Pregunta cuantas unidades quiere comprar el jugador y repite para cada
     * una el mercado de unidades y, a continuacion, el de tripulantes. La
     * version anterior repetia este mismo bloque de codigo seis veces, una por
     * cada unidad comprable de cada bando; ahora el catalogo de cada bando se
     * declara en un solo sitio y el proceso de compra es el mismo para todas.
     *
     * @return la ultima unidad comprada con exito, o {@code null} si no se
     * compro ninguna
     * @throws modelos.excepciones.FueradeMapaException si no se encuentra
     * casilla libre donde desplegar la unidad
     */
    private Unidad compraUnidad() throws FueradeMapaException {
        System.out.println("-------------------MERCADO GENERAL-------------------\n");
        int nunidades;
        do {
            nunidades = Utils.leerEntero("Cuantas unidades desea comprar?");
            if (nunidades < 0) {
                System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
            }
        } while (nunidades < 0);

        Unidad ultimaComprada = null;
        for (int i = 0; i < nunidades; i++) {
            Unidad comprada = comprarUnaUnidad();
            if (comprada != null) {
                ultimaComprada = comprada;
            }
        }
        return ultimaComprada;
    }

    /**
     * Muestra el mercado de unidades del bando del jugador y tramita la compra
     * de una sola unidad, incluida su tripulacion.
     *
     * @return la unidad comprada y operativa, o {@code null} si el jugador no
     * compro nada o no pudo completar la tripulacion
     * @throws modelos.excepciones.FueradeMapaException si no se encuentra
     * casilla libre donde desplegar la unidad
     */
    private Unidad comprarUnaUnidad() throws FueradeMapaException {
        List<Supplier<Unidad>> catalogo = catalogoUnidades();
        while (true) {
            // Se crean ejemplares nuevos en cada vuelta: la ficha que se
            // muestra es la de la unidad que se comprara, y una unidad
            // descartada no debe reutilizarse.
            List<Unidad> escaparate = new ArrayList<>();
            for (Supplier<Unidad> fabrica : catalogo) {
                escaparate.add(fabrica.get());
            }

            System.out.println("\n-------------------MERCADO DE UNIDADES-------------------\n");
            for (int i = 0; i < escaparate.size(); i++) {
                System.out.println("\t" + (i + 1) + ") Comprar " + escaparate.get(i).getNombre()
                        + ": \n" + escaparate.get(i).toStringInfo());
            }
            int opcionNoComprar = escaparate.size() + 1;
            System.out.println("\t" + opcionNoComprar + ") No comprar nada\n");

            // Ante una opcion que no existe solo se repite la pregunta, sin
            // volver a imprimir todo el catalogo: el jugador lo tiene justo
            // encima en la pantalla.
            int eleccion;
            do {
                eleccion = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                if (eleccion < 1 || eleccion > opcionNoComprar) {
                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                }
            } while (eleccion < 1 || eleccion > opcionNoComprar);

            if (eleccion == opcionNoComprar) {
                System.out.println("\n" + this.jugadorjuega.getNombre() + " no ha comprado ninguna unidad\n");
                return null;
            }

            Unidad unidad = escaparate.get(eleccion - 1);
            try {
                this.jugadorjuega.comprarUnidad(unidad);
            } catch (PuntosdeCompraInsuficientesException pci) {
                System.out.println("\tNO TIENE SUFICIENTES PUNTOS PARA COMPRAR ESTA UNIDAD\n");
                continue;
            }
            unidad.setJugador(this.jugadorjuega);

            reclutarTripulantes(unidad);
            System.out.println(this.jugadorjuega.getEjercito());

            if (unidad.getTripulantes().size() < unidad.getSeresneed()) {
                // Sin la tripulacion completa la unidad no puede operar, asi
                // que se retira del ejercito y se libera su casilla. Antes se
                // quitaba del ejercito pero se quedaba ocupando el mapa, y
                // encima se informaba de la compra como exitosa porque la
                // variable de retorno se reasignaba justo despues de anularla.
                this.jugadorjuega.getEjercito().getUnidades().remove(unidad);
                if (unidad.getPosicion() != null) {
                    this.escenario.getMapa()[unidad.getPosicion().getX()][unidad.getPosicion().getY()] = null;
                }
                System.out.println("\tLA UNIDAD NO HA PODIDO COMPLETAR SU TRIPULACION Y NO ENTRA EN COMBATE\n");
                return null;
            }
            return unidad;
        }
    }

    /**
     * Compra los tripulantes que necesita una unidad para quedar operativa,
     * uno a uno, del catalogo del bando del jugador.
     *
     * <p>
     * Si en algun momento el jugador se queda sin puntos para pagar un
     * tripulante se avisa y se deja de reclutar: quien llama comprueba si la
     * tripulacion ha quedado completa.
     *
     * @param unidad unidad que se quiere tripular
     */
    private void reclutarTripulantes(Unidad unidad) {
        List<Supplier<Ser>> catalogo = catalogoSeres();
        while (unidad.getTripulantes().size() < unidad.getSeresneed()) {
            List<Ser> escaparate = new ArrayList<>();
            for (Supplier<Ser> fabrica : catalogo) {
                escaparate.add(fabrica.get());
            }

            System.out.println("\n-------------------MERCADO DE SERES-------------------\n");
            for (int i = 0; i < escaparate.size(); i++) {
                System.out.println((i + 1) + ") Comprar " + escaparate.get(i).getNombre()
                        + ": \n" + escaparate.get(i).toStringInfo());
            }

            int eleccion;
            do {
                eleccion = Utils.leerEntero("Elija que quiere comprar introduciendo el numero correspondiente: ");
                if (eleccion < 1 || eleccion > escaparate.size()) {
                    System.out.println("\tOPCION INVALIDA. VUELVA A INTENTARLO CORRECTAMENTE\n");
                }
            } while (eleccion < 1 || eleccion > escaparate.size());

            try {
                this.jugadorjuega.comprarSeres(unidad, escaparate.get(eleccion - 1));
            } catch (PuntosdeCompraInsuficientesException pci) {
                System.out.println("\tNO TIENE SUFICIENTES PUNTOS PARA COMPRAR ESTE TRIPULANTE\n");
                return;
            }
        }
    }

    /**
     * Devuelve las unidades que puede comprar el bando del jugador, en el orden
     * en que se le presentan en el menu.
     *
     * @return las fabricas de las unidades del catalogo
     */
    private List<Supplier<Unidad>> catalogoUnidades() {
        if (this.jugadorjuega.getEquipo() == Equipo.RDA) {
            return List.of(AT99::new, Aerospatiale::new, MK6::new);
        }
        return List.of(Banshee::new, Thanator::new, Titanothere::new);
    }

    /**
     * Devuelve los tripulantes que puede comprar el bando del jugador, en el
     * orden en que se le presentan en el menu.
     *
     * @return las fabricas de los tripulantes del catalogo
     */
    private List<Supplier<Ser>> catalogoSeres() {
        if (this.jugadorjuega.getEquipo() == Equipo.RDA) {
            return List.of(Comandante::new, Artillero::new);
        }
        return List.of(Omaticaya::new, Tipani::new);
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
                            System.out.println(this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getNombre() + " se ha movido a " + this.jugadorjuega.getEjercito().getUnidades().get(eleccionmover).getPosicion());
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
