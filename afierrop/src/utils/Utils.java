/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author artur
 */
/**
 * Como leer datos de la consola (cuando el usuario teclea algo)
 *
 */
public class Utils {

    /**
     * Función para leer una cadena de caracteres, hay que introducir un string
     * y devuelve otro string.
     *
     * @param mensaje
     * @return
     */
    public static String leerCadena(String mensaje) {

        BufferedReader consola = new BufferedReader(new InputStreamReader(System.in));
        /**
         * InputStreamReader es una clase que lee de la entrada estándar de la
         * consola(System.in) datos de texto (por poner Reader). Si pusiesemos
         * OutputStream saca datos. OutputStreamWriter genera caracteres. *
         */
        String linea = "";
        /**
         * Hay que inicializar la variable por si el string esta vacío*
         */
        System.out.println(mensaje);
        /**
         * Pedimos al usuario que escriba una cadena*
         */
        try {
            linea = consola.readLine();
            /**
             * Creamos la variable linea que es un string(creada arriba), para
             * que a la variable consola que la leemos con readLine() la
             * guardemos en esa variable. La metemos en un try porque puede
             * haber excepciones que dan a error, por ello ejecutamos el catch
             * por si sale una excepcion.*
             */
        } catch (IOException ex) {
            System.out.println("Error de lectura por entrada estandar");
            /**
             * El programa ejecutara el try y si hay error, lo que haya en el
             * catch.*
             */
        }

        return linea;
    }

    /**
     * Función para leer un entero, hay que introducir un string y devuelve un
     * int.
     *
     * @param mensaje
     * @return
     */
    public static int leerEntero(String mensaje) {

        int numero = 0;
        /**
         * creamos una variable numero*
         */
        String linea;
        boolean lecturaCorrecta = false;

        while (!lecturaCorrecta) {
            try {
                linea = leerCadena(mensaje);
                numero = Integer.parseInt(linea);
                lecturaCorrecta = true;

            } catch (NumberFormatException nfe) {
                System.out.println("Debe introducir un numero, por favor, repita");
            }
            /**
             * la variable numero será igual a lo que ponga el usuario por
             * consola. Tiene excepciones, en el que el usuario no ponga un
             * entero, por lo que habra que hacer un bucle para volver a
             * repetirlo hasta que ponga un entero. Si salta la excepcion en el
             * parseint las lineas de abajo no se leen. Por lo que cuando pasa a
             * la de abajo (cuando no hay execpciones) cambiamos el valor de
             * lecturacorrecta para salir del bucle.*
             */
        }
        return numero;
    }

    /**
     * Funciona para saber si un número es primo, devuelve un booleano siendo
     * true si es primo.Hay que introducir un int para utlizarlo.
     *
     * @param numero
     * @return
     */
    public static boolean esPrimo(int numero) {
        int divisor = 2;
        boolean primo = true;
        while ((primo) && (numero != divisor)) {
            if (numero % divisor == 0) {
                primo = false;
            }
            divisor++;
        }
        return primo;
    }

    /**
     * Funciona para hacer que una cadena (String) que tenga alguna vocal con
     * acento la convierta en una vocal sin acenta para poder imprimirla.
     *
     * @param cadenaALimpiar
     * @return
     */
    public static String limpiarAcentos(String cadenaALimpiar) {
        cadenaALimpiar = cadenaALimpiar.replace("á", "a");
        cadenaALimpiar = cadenaALimpiar.replace("é", "e");
        cadenaALimpiar = cadenaALimpiar.replace("í", "i");
        cadenaALimpiar = cadenaALimpiar.replace("ó", "o");
        cadenaALimpiar = cadenaALimpiar.replace("ú", "u");
        cadenaALimpiar = cadenaALimpiar.replace("Á", "A");
        cadenaALimpiar = cadenaALimpiar.replace("É", "E");
        cadenaALimpiar = cadenaALimpiar.replace("Í", "I");
        cadenaALimpiar = cadenaALimpiar.replace("Ó", "O");
        cadenaALimpiar = cadenaALimpiar.replace("Ú", "U");
        return cadenaALimpiar;
    }

    /**
     * Funciona para hacer que una cadena (String) que tenga espacios los quite.
     *
     * @param cadenaALimpiar
     * @return
     */
    public static String limpiarEspacios(String cadenaALimpiar) {
        cadenaALimpiar = cadenaALimpiar.replace(" ", "");
        return cadenaALimpiar;
    }

    /**
     * Función para leer un double, hay que introducir un string y devuelve un
     * double.
     *
     * @param mensaje
     * @return
     */
    public static double leerDouble(String mensaje) {

        double numero = 0;
        /**
         * creamos una variable numero*
         */
        String linea;
        boolean lecturaCorrecta = false;

        while (!lecturaCorrecta) {
            try {
                linea = leerCadena(mensaje);
                numero = Double.parseDouble(linea);
                lecturaCorrecta = true;

            } catch (NumberFormatException nfe) {
                System.out.println("Debe introducir un numero, por favor, repita");
            }
            /**
             * la variable numero será igual a lo que ponga el usuario por
             * consola. Tiene excepciones, en el que el usuario no ponga un
             * entero, por lo que habra que hacer un bucle para volver a
             * repetirlo hasta que ponga un entero. Si salta la excepcion en el
             * parseint las lineas de abajo no se leen. Por lo que cuando pasa a
             * la de abajo (cuando no hay execpciones) cambiamos el valor de
             * lecturacorrecta para salir del bucle.*
             */
        }
        return numero;
    }

    /**
     * Función para leer un float, hay que introducir un string y devuelve un
     * float.
     *
     * @param mensaje
     * @return
     */
    public static float leerFloat(String mensaje) {

        float numero = 0;
        /**
         * creamos una variable numero*
         */
        String linea;
        boolean lecturaCorrecta = false;

        while (!lecturaCorrecta) {
            try {
                linea = leerCadena(mensaje);
                numero = Float.parseFloat(linea);
                lecturaCorrecta = true;

            } catch (NumberFormatException nfe) {
                System.out.println("Debe introducir un numero, por favor, repita");
            }
            /**
             * la variable numero será igual a lo que ponga el usuario por
             * consola. Tiene excepciones, en el que el usuario no ponga un
             * entero, por lo que habra que hacer un bucle para volver a
             * repetirlo hasta que ponga un entero. Si salta la excepcion en el
             * parseint las lineas de abajo no se leen. Por lo que cuando pasa a
             * la de abajo (cuando no hay execpciones) cambiamos el valor de
             * lecturacorrecta para salir del bucle.*
             */
        }
        return numero;
    }
}
