package interfaz;

import dominio.EstadoCamino;
import dominio.Retorno;

public interface Grafo {

    Retorno agregarEstacion(String codigo, String nombre);
    //Pre: origen y destino son los índices de vértices ya ingresados en el grafo
    //Post: Agrega la arista origen-destino de peso "peso" en el grafo
    Retorno agregarConexion(String codigoEstacionOrigen, String codigoEstacionDestino,int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion);
    Retorno actualizarCamino(String codigoEstacionOrigen, String codigoEstacionDestino,int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino);
    boolean existeEstacion(String codigo);
    boolean sonAdyacentes(int a, int b);
    boolean esVacio();
    boolean estaLlena();

    Retorno listadoEstacionesCantTrasbordos(String codigo, int cantidad);

    Retorno viajeCostoMinimoKilometros(String codigoEstacionOrigen, String codigoEstacionDestino,int kilometros);

    Retorno viajeCostoMinimoEuros(String codigoEstacionOrigen, String codigoEstacionDestino,int dinero);
}
