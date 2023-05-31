package interfaz;

public interface Lista<T> {

    void insertar(T dato);

    int largo();
    boolean existe(T dato);

    boolean esVacia();
    boolean esLlena();

    public T get(int indice);
}
