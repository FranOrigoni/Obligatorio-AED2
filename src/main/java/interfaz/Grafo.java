package interfaz;

public interface Grafo {

    void agregarEstacion(String codigo,String nombre, int v);
    //Pre: origen y destino son los índices de vértices ya ingresados en el grafo
    //Post: Agrega la arista origen-destino de peso "peso" en el grafo
    void agregarConexion(String codigoEstacionOrigen, String codigoEstacionDestino,int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion);
    void eliminarEstacion(int v);
    void eliminarConexion(int origen, int destino);
    boolean existeEstacion(int v);
    boolean sonAdyacentes(int a, int b);
  //  ListaInt verticesAdyacentes(int v);
    boolean esVacio();
    boolean estaLlena();

}
