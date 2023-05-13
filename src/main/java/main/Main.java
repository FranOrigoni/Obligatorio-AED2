package main;

import interfaz.*;
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
        IS.registrarPasajero("FR1.233.222#5","Fran",31);
        IS.registrarPasajero("FR222.333#9","Lucas",25);
        IS.registrarPasajero("OT3.212.322#2","Fabian",42);
        IS.registrarPasajero("OT1.232.322#0","Cavani",18);
        IS.registrarPasajero("US3212","CR7",38);
        IS.registrarPasajero("ES029.232#3:","Neymar",29);
        abbPasajero.imprimirDatos();
        IS.filtrarPasajeros(Consulta.edadMayor(22));
        // IS.filtrarPasajeros(Consulta.fromString("[edad > 20] AND [nacionalidad ='FR' AND nombre='Felipe']"));
        IS.buscarPasajero("OT3.212.322#2");
        // abbPasajero.imprimirDatos();

        /*  ORDENADOS DESCENDENTE
            DE1.233.222#5 Fran
            FR123.456#2   Felipe
            FR222.333#9   Lucas
            OT1.232.322#0 Cavani
            OT3.212.322#2 Fabian
        * */
        IS.listarPasajerosDescendente();
         IS.listarPasajerosAscendente();
         IS.listarPasajerosPorNacionalidad(Nacionalidad.Francia);
/*
        FR123.456#2
        ES222.333#9
        OT3.212.322#2
        DE1.233.222#5

*/

    }
}
