package interfaz;

public interface Grafo {

    Retorno agregarEstacion(String codigo,String nombre);
    //Pre: origen y destino son los índices de vértices ya ingresados en el grafo
    //Post: Agrega la arista origen-destino de peso "peso" en el grafo
    Retorno agregarConexion(String codigoEstacionOrigen, String codigoEstacionDestino,int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion);
    Retorno actualizarCamino(String codigoEstacionOrigen, String codigoEstacionDestino,int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino);
    //void eliminarEstacion(String codigo);
    //void eliminarConexion(int origen, int destino);
    boolean existeEstacion(String codigo);
    boolean sonAdyacentes(int a, int b);
  //  ListaInt verticesAdyacentes(int v);
    boolean esVacio();
    boolean estaLlena();

    Retorno listadoEstacionesCantTrasbordos(String codigo, int cantidad);

    Retorno viajeCostoMinimoKilometros(String codigoEstacionOrigen, String codigoEstacionDestino);

    Retorno viajeCostoMinimoEuros(String codigoEstacionOrigen, String codigoEstacionDestino);
}
