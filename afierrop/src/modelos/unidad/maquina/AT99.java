/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos.unidad.maquina;

/**
 *
 * @author artur
 */
public class AT99 extends Maquina {

    /**
     * Constructor de AT99. Hereda de Maquina y luego de Unidad. AT99 asigna sus
     * atributos unicos (nombre, coste, energias, potencia, puntos, tripulantes
     * posicion, energia gastada al moverse, maximo movimiento y rango de
     * ataque), Unidad (el padre de todos) asigna a todos los valores como los
     * que se le pasa por parametro, que son los que se le pasa mediante sus
     * hijos. Posicion y tripulantes se asignan con setters al comprar la unidad
     */
    public AT99() {
        super("AT-99 Escorpion", 15000, 7500, 7500, 5000, 500, 10000, null, null, 100, "20", 10);
    }

}
