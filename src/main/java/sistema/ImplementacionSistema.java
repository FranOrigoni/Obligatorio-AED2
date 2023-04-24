package sistema;

import interfaz.*;

public class ImplementacionSistema implements Sistema {

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
            // Verificar que los parámetros no son vacíos o null
            if (identificadorPasajero == null || identificadorPasajero.isEmpty() ||
                    nombre == null || nombre.isEmpty()) {
                return Retorno.error1("Los parametros no pueden ser vacios");
            }

            // Validar el formato con expresión regular
            String regex = "^[A-Z]{2}\\d{6}[A-Z0-9]{1}$";
            if (!identificadorPasajero.matches(regex)) {
                return Retorno.error2("el identificador debe tener un formato vàlido");
            }

            // Verificar si ya existe un pasajero registrado con el identificador dado
            if (pasajeros.containsKey(identificadorPasajero)) {
                return Retorno.error3("Ya existe pasajero con ese identificador");
            }

            // Registrar al pasajero con sus datos
            Pasajero nuevoPasajero = new Pasajero(identificadorPasajero, nombre, edad);
            pasajeros.put(identificadorPasajero, nuevoPasajero);

            return Retorno.ok();
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
