/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.maquina;

/**
 *
 * @author artur
 */
public class MK6 extends Maquina {

    /**
     * Constructor de MK6. Hereda de Maquina y luego de Unidad. MK6 asigna sus
     * atributos unicos (nombre, coste, energias, potencia, puntos, tripulantes,
     * posicion, energia gastada al moverse, maximo movimiento, rango de ataque
     * y seres necesarios para el funcionamiento), Unidad (el padre de todos)
     * asigna a todos los valores como los que se le pasa por parametro, que son
     * los que se le pasa mediante sus hijos. Posicion y tripulantes se asignan
     * con setters al comprar la unidad
     */
    public MK6() {
        super("Plataforma de Movilidad Amplificada Mitsubishi MK-6", 500, 0, 1000, 500, 20, 50, null, null, 25, "2", 1, 1);
    }

}
