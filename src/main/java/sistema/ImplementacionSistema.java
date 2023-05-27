package sistema;

import interfaz.*;


public class ImplementacionSistema implements Sistema {

    ABBPasajero abbPasajero = new ABBPasajero();
    EstacionDeTrenGrafo grafoEstacion = new EstacionDeTrenGrafo(0,true);


    @Override
    public Retorno inicializarSistema(int maxEstaciones) {

        if (maxEstaciones <= 5) {
            return Retorno.error1("maxEstaciones es menor o igual a 5");
        } else {
           this.abbPasajero.vaciarArbol();
          // EstacionDeTrenGrafo grafoEstacion = new EstacionDeTrenGrafo(maxEstaciones,true);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarPasajero(String identificadorPasajero, String nombre, int edad) {
        return abbPasajero.registrarPasajero(identificadorPasajero, nombre, edad);
    }


    @Override
    public Retorno filtrarPasajeros(Consulta consulta) {
        return abbPasajero.filtrarPasajero(consulta);
    }


    @Override
    public Retorno buscarPasajero(String identificador) {
     return abbPasajero.buscarPasajero(identificador);
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        return abbPasajero.listarPasajerosAscendente();
    }

    @Override
    public Retorno listarPasajerosDescendente() {
       return abbPasajero.listarPasajerosDescendente();

    }

    @Override
    public Retorno listarPasajerosPorNacionalidad(Nacionalidad nacionalidad) {
        return abbPasajero.listarPasajerosPorNacionalidad(nacionalidad);

    }

    @Override
    public Retorno registrarEstacionDeTren(String codigo, String nombre) {
        return grafoEstacion.agregarEstacion(codigo, nombre);
    }



    @Override
    public Retorno registrarConexion(String codigoEstacionOrigen, String codigoEstacionDestino,int identificadorConexion, double costo, double tiempo, double kilometros,
                                     EstadoCamino estadoDeLaConexion) {
        return grafoEstacion.agregarConexion(codigoEstacionOrigen,codigoEstacionDestino,identificadorConexion,costo,tiempo,kilometros,estadoDeLaConexion);
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
