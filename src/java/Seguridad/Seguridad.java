/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;
/**
 *
 * @author Silvio
 */
public class Seguridad {

    String Caracteres;

    public Seguridad() {

    }

    public String getCaracteres() {
        return Caracteres;
    }

    public void setCaracteres(String Caracteres) {
        this.Caracteres = Caracteres;
    }

    // Esta funcion valida solo letras con espacios diferentes a '%'
    public boolean ValidarSoloLetras(String Caracteres) {
        boolean Respuesta = false;

        if (Caracteres.matches("[a-z A-Z]*")) {
            Respuesta = true;
        }
        return Respuesta;
    }

    // Esta funcion devuelve true si esta vacia
    public boolean ValidarCadenaVacia(String Caracteres) {
        boolean Respuesta = false;

        if (Caracteres.length() == 0) {
            Respuesta = true;
        }
        return Respuesta;
    }

    // Esta funcion devuelve true si solo son numeros en la cadena
    public boolean ValidarSoloNumeros(String Caracteres) {
        boolean Respuesta = false;

        if (Caracteres.matches("[0-9]*")) {
            Respuesta = true;
        }
        return Respuesta;
    }

    // Esta función valida solo numeros y letras con espacios diferentes a '%'
    public boolean ValidarNumerosYLetras(String Caracteres) {

        boolean Respuesta = false;

        if (Caracteres.matches("[a-z A-Z_0-9]*")) {
            Respuesta = true;
        }
        return Respuesta;
    }

    public static void main(String[] arg) {
        Seguridad Seguridad = new Seguridad();
        System.out.println(Seguridad.ValidarNumerosYLetras("Ana 12"));
    }
}
