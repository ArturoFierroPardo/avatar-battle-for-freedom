package modelos.excepciones;

/**
 * Se lanza cuando el jugador elige una unidad de su ejercito que no existe.
 *
 * @author Arturo Fierro Pardo
 */
public class IndexOutofBounceException extends Exception {

    /**
     * Identificador de version para la serializacion. Fijarlo a mano evita
     * que un cambio en la clase invalide las partidas ya guardadas con una
     * InvalidClassException.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Excepcion para poder comprobar que el usuario mete las opciones
     * correctamente, así, ya que jugamos con arrays y arraylist podemos ver si
     * esta fuera de ellos, al poner un numero negativo o excedido del limite
     * del array o arraylist. Se lanza y se catchea en menu.
     */
}
