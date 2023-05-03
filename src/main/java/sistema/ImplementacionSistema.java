package sistema;

import interfaz.*;

import java.util.ArrayList;

public class ImplementacionSistema implements Sistema {

    ArrayList<Pasajero> pasajeros = new ArrayList<>();

    public ArrayList<Pasajero> getPasajeros() {
        return pasajeros;
    }

    public void setPasajeros(ArrayList<Pasajero> pasajeros) {
        this.pasajeros = pasajeros;
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
    public  Retorno registrarPasajero(String identificadorPasajero, String nombre, int edad) {
            if (!validarIdentificador(identificadorPasajero)) {
                return Retorno.error2("Identificador no tiene formato valido");
            }

            if (nombre == null || nombre.isEmpty() && edad >0) {
                return Retorno.error1("alguno de los parámetros es vacío o nul");
            }

            if (pasajeros.stream().anyMatch(p -> p.getIdentificador().equals(identificadorPasajero))) {
                return Retorno.error3("Ya existe un pasajero con ese identificador");
            }

            Pasajero pasajero = new Pasajero(identificadorPasajero, nombre, edad);
            pasajeros.add(pasajero);
            return Retorno.ok();
    }


    public static boolean validarIdentificador(String identificador) {
        String regex = "^(FR|DE|UK|ES|OT)[1-9]\\d{0,2}(\\.\\d{3}){0,2}#\\d$";
        return identificador.matches(regex);
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
