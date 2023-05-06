package sistema;

import interfaz.*;


public class ImplementacionSistema implements Sistema {

    ABBPasajero abbPasajero = new ABBPasajero();

    public ABBPasajero getAbbPasajero() {
        return abbPasajero;
    }

    @Override
    public Retorno inicializarSistema(int maxEstaciones) {

        if (maxEstaciones <= 5) {
            return Retorno.error1("maxEstaciones es menor o igual a 5");
        } else {
            // Iniciar estructuras necesarias para el sistema.
            return Retorno.ok();
        }
    }
    @Override
    public Retorno registrarPasajero(String identificadorPasajero, String nombre, int edad) {
        if (!validarIdentificador(identificadorPasajero)) {
            return Retorno.error2("Identificador no tiene formato valido");
        }

        if (nombre.isEmpty() || edad < 0) {
            return Retorno.error1("alguno de los parámetros es vacío o nul");
        }


        if (abbPasajero.pertence(identificadorPasajero)) {
            return Retorno.error3("Ya existe un pasajero con ese identificador");
        }

        Pasajero pasajero = new Pasajero(identificadorPasajero, nombre, edad);
        abbPasajero.insertar(pasajero);
        return Retorno.ok();
    }




    //Explicacion de validador // Ver si no conviene implementar metodo en ABBPasajero
/*
    El indentificadoir debe comenzar (^) con una de las cinco abreviaturas de país: "FR", "DE", "UK", "ES" o "OT"
    [1-9] es una clase de caracteres que representa cualquier dígito del 1 al 9.
    d{0,2} puede haber un número de 0 a 2 dígitos despues de la abrevitartua del país
    (.\d{3})El punto decimal se representa con el carácter "." y el dígito {3} indica que debe haber exactamente tres dígitos después del punto decimal símbolo "{}" se utiliza para indicar una cantidad específica de dígitos
    {0,2}  indica que este patrón de tres dígitos separados por un punto puede repetirse de 0 a 2 veces, lo que significa que se pueden tener cero, uno o dos grupos de tres dígitos separados por punto
    #\d$ significa que debe haber un símbolo de número (#) seguido de un dígito al final de la cadena  "\d" representa cualquier dígito del 0 al 9, y el signo "$" indica que la cadena debe terminar después de este dígito
*/
    public static boolean validarIdentificador(String identificador) {
        String cadena = "^(FR|DE|UK|ES|OT)[1-9]\\d{0,2}(\\.\\d{3}){0,2}#\\d$";
        return identificador.matches(cadena);
    }

    @Override
    public Retorno filtrarPasajeros(Consulta consulta) {



        return Retorno.noImplementada();
    }



    @Override
    public Retorno buscarPasajero(String identificador) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosDescendente() {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listarPasajerosPorNacionalidad(Nacionalidad nacionalidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarEstacionDeTren(String codigo, String nombre) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno registrarConexion(String codigoEstacionOrigen, String codigoEstacionDestino,
                                     int identificadorConexion, double costo, double tiempo, double kilometros,
                                     EstadoCamino estadoDeLaConexion) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno actualizarCamino(String codigoEstacionOrigen, String codigoEstacionDestino,
                                    int identificadorConexion, double costo, double tiempo,
                                    double kilometros, EstadoCamino estadoDelCamino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno listadoEstacionesCantTrasbordos(String codigo, int cantidad) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoEstacionOrigen, String codigoEstacionDestino) {
        return Retorno.noImplementada();
    }

    @Override
    public Retorno viajeCostoMinimoEuros(String codigoEstacionOrigen, String codigoEstacionDestino) {
        return null;
    }

}
