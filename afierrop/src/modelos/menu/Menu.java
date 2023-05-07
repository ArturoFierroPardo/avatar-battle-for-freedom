/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.menu;

import modelos.jugador.Jugador;

/**
 *
 * @author artur
 */
public class Menu {

    private Jugador jugadorjuega;

    public Menu(Jugador jugadorjuega) {
        this.jugadorjuega = jugadorjuega;
    }

    public void enseñarMenu() {
        System.out.println("Elija que quiere hacer escribiendo el nº correspondiente: ");
        System.out.println("1) Comprar una unidad");
        System.out.println("2) Mover una unidad");
        System.out.println("3) Atacar a una unidad enemiga");
        System.out.println("Finalizar el turno");
    }

    public void opcionEscogida(int opcion) {
        switch (opcion) {
            case 1:
            /*jugadorjuega.comprarUnidad(unidad);*/
            case 2:
            /*Mover unidad*/
            case 3:
            /*Atacar a una unidad*/
            case 4:
            /*Cambiar turno*/

        }
    }

    /**
     * Hacer cada una de las 4 funciones con sus excepciones en modo bucle en
     * esta clase
     */
}
