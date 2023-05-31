package lista;


import interfaz.Lista;

public class ListaImpl<T extends Comparable<T>> implements Lista<T> {

    protected NodoLista<T> inicio;
    protected int largo;

    public ListaImpl() {
        this.inicio = null;
        this.largo = 0;
    }

    @Override
    public void insertar(T dato) {
        NodoLista<T> nuevoNodo = new NodoLista<T>(dato, null);

        if (inicio == null) {
            // Si la lista está vacía, el nuevo nodo se convierte en el inicio
            inicio = nuevoNodo;
        } else {
            NodoLista<T> nodoActual = inicio;

            // Recorremos la lista hasta llegar al último nodo

            while (nodoActual.getSig() != null) {
                nodoActual = nodoActual.getSig();
            }

            // Enlazamos el nuevo nodo al final de la lista
            nodoActual.setSig(nuevoNodo);
        }
        largo++;
    }



    @Override
    public int largo() {
        return largo;
    }

    @Override
    public boolean existe(T dato) {
        NodoLista<T> aux = inicio;
        while (aux != null) {
            if (aux.getDato().equals(dato)) {
                return true;
            }
            aux = aux.getSig();
        }
        return false;
    }


    @Override
    public boolean esVacia() {
        return largo == 0;
    }

    @Override
    public boolean esLlena() {
        return false;
    }


    private class NodoLista<T>{
        private T dato;
        private NodoLista<T> sig;

        public NodoLista(T dato) {
            this.dato = dato;
            this.sig = null;
        }

        public NodoLista(T dato, NodoLista<T> sig) {
            this.dato = dato;
            this.sig = sig;
        }

        public T getDato() {
            return dato;
        }

        public void setDato(T dato) {
            this.dato = dato;
        }

        public NodoLista<T> getSig() {
            return sig;
        }

        public void setSig(NodoLista<T> sig) {
            this.sig = sig;
        }

        @Override
        public String toString() {
            return dato.toString();
        }
    }

    @Override
    public T get(int indice) {

        NodoLista<T> actual = inicio;
        for (int i = 0; i < indice; i++) {
            actual = actual.sig;
        }

        return actual.dato;
    }


}
