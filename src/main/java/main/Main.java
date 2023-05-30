package main;

import interfaz.*;
import jdk.jshell.ImportSnippet;
import lista.Lista;
import lista.ListaImpl;
import sistema.ABBPasajero;
import sistema.EstacionDeTrenGrafo;
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



       // ABBPasajero abbPasajero = IS.getAbbPasajero();
        IS.registrarPasajero("FR123.456#2","Felipe",21);
        IS.registrarPasajero("FR222.333#9","Lucas",25);
        IS.registrarPasajero("OT3.212.322#2","Fabian",42);
        IS.registrarPasajero("OT1.232.322#0","Cavani",18);
        IS.registrarPasajero("FR222.333#9","Fran",31);
        //IS.registrarPasajero("US3212","CR7",38);
        //IS.registrarPasajero("ES029.232#3:","Neymar",29);

        System.out.println(IS.filtrarPasajeros(Consulta.edadMayor(22)));
    IS.filtrarPasajeros(Consulta.edadMayor(22));
     IS.filtrarPasajeros(Consulta.fromString("[edad > 24] AND [nacionalidad ='FR' OR nombre='Felipe']"));
   //  IS.listarPasajerosPorNacionalidad(Nacionalidad.Francia);

       // EstacionDeTrenGrafo grafo = new EstacionDeTrenGrafo(6,true);
/*
        IS.registrarEstacionDeTren("LLL000","MADRID_1");
        IS.registrarEstacionDeTren("BAB010","MADRID_2");
        IS.registrarConexion("LLL000","BAB010", 1, 222, 333, 22, EstadoCamino.BUENO);
        IS.registrarConexion("LLL000","BAB010", 2, 888, 333, 22, EstadoCamino.BUENO);

        IS.registrarConexion("LLL000","BAB010", 1, 666, 333, 22, EstadoCamino.BUENO);


*/
      // IS.buscarPasajero("FR123.456#2");
     //    abbPasajero.imprimirDatos();

      // IS.listarPasajerosDescendente();


/*
        FR123.456#2
        ES222.333#9
        OT3.212.322#2
        DE1.233.222#5

*/

    }
}
