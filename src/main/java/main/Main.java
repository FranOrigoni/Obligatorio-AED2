package main;

import interfaz.Retorno;
import interfaz.Sistema;
import jdk.jshell.ImportSnippet;
import sistema.ImplementacionSistema;
import interfaz.Retorno;

public class Main {
    public static void main(String[] args) {

        ImplementacionSistema IS = new ImplementacionSistema();
        String identificadorValido = "FR123.456#2";
        String identificadorMalo = "ES029.232#3";
        if (ImplementacionSistema.validarIdentificador(identificadorValido)) {
            System.out.println("Identificador válido");
        } else {
            System.out.println("Identificador inválido");
        }






    }
}
