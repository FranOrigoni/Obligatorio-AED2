package main;

import interfaz.Retorno;
import interfaz.Sistema;
import jdk.jshell.ImportSnippet;
import sistema.ABBPasajero;
import sistema.ImplementacionSistema;
import interfaz.Retorno;
import sistema.Pasajero;

public class Main {
    public static void main(String[] args) {

        ImplementacionSistema IS = new ImplementacionSistema();
        String identificadorValido = "FR123.456#2";
        String identificadorMalo = "ES029.232#3";
        /*
        if (ImplementacionSistema.validarIdentificador(identificadorValido)) {
            System.out.println("Identificador válido");
        } else {
            System.out.println("Identificador inválido");
        }
*/





        ABBPasajero abbPasajero = IS.getAbbPasajero();
        IS.registrarPasajero("FR123.456#2","Felipe",21);
        IS.registrarPasajero("ES222.333#9","Lucas",24);
        IS.registrarPasajero("OT3.212.322#2","Fabian",42);
        IS.registrarPasajero("DE1.233.222#5","Fran",31);
        IS.registrarPasajero("US3212","CR7",38);
        IS.registrarPasajero("ES029.232#3:","Neymar",29);


        abbPasajero.imprimirDatos();





    }
}
