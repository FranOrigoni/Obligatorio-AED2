package sistema;

public class ABBPasajero {
    private NodoABB raiz;

    public void insertar(Pasajero nodoPasajero) {
        if (raiz == null) {
            raiz = new NodoABB(nodoPasajero);
        } else {
            insertarRec(raiz, nodoPasajero);
        }
    }



    private void insertarRec(NodoABB nodo, Pasajero nodoPasajero) {
            if (nodo.getNodoPasajero().compareTo(nodoPasajero) > 0) {
                if (nodo.izq == null) {
                    nodo.izq = new NodoABB(nodoPasajero);
                } else {
                    insertarRec(nodo.izq, nodoPasajero);
                }
            } else if (nodo.getNodoPasajero().compareTo(nodoPasajero) < 0) {
                if (nodo.der == null) {
                    nodo.der = new NodoABB(nodoPasajero);
                } else {
                    insertarRec(nodo.der, nodoPasajero);
                }
            }

    }


    public boolean pertence(String identificadorPasajero) {
        return pertenceRec(raiz, identificadorPasajero);
    }

    private boolean pertenceRec(NodoABB nodo, String identificadorPasajero) {
        if (nodo != null) {
            if (identificadorPasajero.equals(nodo.getNodoPasajero().getIdentificador())) {
                return true;
            } else if (identificadorPasajero.compareTo(nodo.getNodoPasajero().getIdentificador()) < 0) {
                return pertenceRec(nodo.izq, identificadorPasajero);
            } else {
                return pertenceRec(nodo.der, identificadorPasajero);
            }
        } else {
            return false;
        }
    }

    public void imprimirDatos() {
        imprimirDatos3(raiz);
    }

    public void imprimirDatos3(NodoABB nodo) {
        if (nodo != null) {
            System.out.println(nodo.getNodoPasajero().getNombre());
            imprimirDatos3(nodo.getIzq());
            imprimirDatos3(nodo.getDer());
        } else {
            //no hago nada
        }
    }

    private class NodoABB {
        private Pasajero pasajero;
        private NodoABB izq;
        private NodoABB der;

        public NodoABB(Pasajero pasajero, NodoABB izq, NodoABB der) {
            this.pasajero = pasajero;
            this.izq = izq;
            this.der = der;
        }

        public NodoABB(Pasajero pasajero) {
            this.pasajero = pasajero;
        }

        public Pasajero getNodoPasajero() {
            return pasajero;
        }

        public void setNodoPasajero(Pasajero pasajero) {
            this.pasajero = pasajero;
        }

        public NodoABB getIzq() {
            return izq;
        }

        public void setIzq(NodoABB izq) {
            this.izq = izq;
        }

        public NodoABB getDer() {
            return der;
        }

        public void setDer(NodoABB der) {
            this.der = der;
        }
    }
}
