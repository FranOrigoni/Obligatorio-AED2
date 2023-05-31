package sistema;

import dominio.*;
import interfaz.*;
import lista.ListaImpl;


public class ImplementacionSistema implements Sistema {

    ABBPasajero abbPasajero;
    EstacionDeTrenGrafo grafoEstacion;

    ListaImpl<Pasajero> pasajerosFranceses;
    ListaImpl<Pasajero> pasajerosAlemanes;
    ListaImpl<Pasajero> pasajerosEspanoles;
    ListaImpl<Pasajero> pasajerosReinoUnido;
    ListaImpl<Pasajero> pasajerosOtros;
    int kilometros;
    int dinero;


    @Override
    public Retorno inicializarSistema(int maxEstaciones) {

        if (maxEstaciones <= 5) {
            return Retorno.error1("maxEstaciones es menor o igual a 5");
        } else {
            this.abbPasajero = new ABBPasajero();
            this.kilometros = 1; // Parametro para reutilizar funcion dijkstra
            this.dinero = 2; // Parametro para reutilizar funcion dijkstra
            this.pasajerosFranceses = new ListaImpl<>();
            this.pasajerosAlemanes = new ListaImpl<>();
            this.pasajerosEspanoles = new ListaImpl<>();
            this.pasajerosOtros = new ListaImpl<>();
            this.pasajerosReinoUnido = new ListaImpl<>();
            this.grafoEstacion = new EstacionDeTrenGrafo(maxEstaciones,true);
            return Retorno.ok();
        }
    }

    @Override
    public Retorno registrarPasajero(String identificadorPasajero, String nombre, int edad) {
        if(identificadorPasajero != null && !identificadorPasajero.isEmpty()){
            ListaImpl<Pasajero> listaNacionalidadTipo = obtenerTipoNacionalidad(identificadorPasajero);
            return abbPasajero.registrarPasajero(identificadorPasajero, nombre, edad, listaNacionalidadTipo);
        }
        return Retorno.error1("");
    }

    private ListaImpl<Pasajero> obtenerTipoNacionalidad(String identificadorPasajero) {
        if (identificadorPasajero.substring(0,2).equals(Nacionalidad.Francia.getCodigo())) {
            return pasajerosFranceses;
        } else if (identificadorPasajero.substring(0,2).equals(Nacionalidad.Espania.getCodigo())) {
            return pasajerosEspanoles;
        } else if (identificadorPasajero.substring(0,2).equals(Nacionalidad.Alemania.getCodigo())) {
            return pasajerosOtros;
        } else if (identificadorPasajero.substring(0,2).equals(Nacionalidad.ReinoUnido.getCodigo())) {
            return pasajerosReinoUnido;
        }else
            return pasajerosAlemanes;
    }

    @Override
    public Retorno filtrarPasajeros(Consulta consulta) {
        if(consulta!= null){
          return abbPasajero.filtrarPasajero(consulta);
        }
        return Retorno.error1("");

    }


    @Override
    public Retorno buscarPasajero(String identificador) {
        return abbPasajero.buscarPasajero(identificador);
    }

    @Override
    public Retorno listarPasajerosAscendente() {
        Retorno ret = abbPasajero.listarPasajerosAscendente();
        ret.setValorString(quitarUltimoCaracter(ret.getValorString()));

        return ret;
    }

    @Override
    public Retorno listarPasajerosDescendente() {

       return abbPasajero.listarPasajerosDescendente();
    }

    private String quitarUltimoCaracter(String unString) {
        return unString.substring(0,unString.length()-1);
    }


    @Override
    public Retorno listarPasajerosPorNacionalidad(Nacionalidad nacionalidad) {
        ListaImpl<Pasajero> listaPorNacionalidad = obtenerTipoNacionalidad(nacionalidad);
        return abbPasajero.listarPasajerosPorNacionalidad(listaPorNacionalidad);
    }
    private ListaImpl<Pasajero> obtenerTipoNacionalidad(Nacionalidad nacionalidad) {
        if (nacionalidad.equals(Nacionalidad.Francia)) {
            return pasajerosFranceses;
        } else if (nacionalidad.equals(Nacionalidad.Espania)) {
            return pasajerosEspanoles;
        } else if (nacionalidad.equals(Nacionalidad.Alemania)) {
            return pasajerosOtros;
        } else if (nacionalidad.equals(Nacionalidad.ReinoUnido)) {
            return pasajerosReinoUnido;
        }else
            return pasajerosAlemanes;
    }

    @Override
    public Retorno registrarEstacionDeTren(String codigo, String nombre) {
        return grafoEstacion.agregarEstacion(codigo, nombre);
    }


    @Override
    public Retorno registrarConexion(String codigoEstacionOrigen, String codigoEstacionDestino, int identificadorConexion, double costo, double tiempo, double kilometros,
                                     EstadoCamino estadoDeLaConexion) {
        return grafoEstacion.agregarConexion(codigoEstacionOrigen, codigoEstacionDestino, identificadorConexion, costo, tiempo, kilometros, estadoDeLaConexion);
    }

    @Override
    public Retorno actualizarCamino(String codigoEstacionOrigen, String codigoEstacionDestino,
                                    int identificadorConexion, double costo, double tiempo,
                                    double kilometros, EstadoCamino estadoDelCamino) {
        return grafoEstacion.actualizarCamino(codigoEstacionOrigen,codigoEstacionDestino,identificadorConexion,costo,tiempo,kilometros,estadoDelCamino);
    }

    @Override
    public Retorno listadoEstacionesCantTrasbordos(String codigo, int cantidad) {
        return grafoEstacion.listadoEstacionesCantTrasbordos(codigo,cantidad);
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoEstacionOrigen, String codigoEstacionDestino) {
        return grafoEstacion.viajeCostoMinimoKilometros(codigoEstacionOrigen,codigoEstacionDestino,kilometros);
    }

    @Override
    public Retorno viajeCostoMinimoEuros(String codigoEstacionOrigen, String codigoEstacionDestino) {
        return grafoEstacion.viajeCostoMinimoEuros(codigoEstacionOrigen,codigoEstacionDestino,dinero);
    }


}
