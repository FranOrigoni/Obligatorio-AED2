package lista;

public interface Lista<T> {

    void insertar(T dato);
    void borrar(T dato);
    int largo();
    boolean existe(T dato);
    T recuperar(T dato);
    boolean esVacia();
    boolean esLlena();
    void imprimirDatos();
    public T get(int indice);
}
