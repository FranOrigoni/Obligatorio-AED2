package dominio;

import lista.ListaImpl;

public class ABBPasajero {
    private NodoABB raiz;
    private String resultado;

    public NodoABB getRaiz() {
        return raiz;
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

    public Retorno registrarPasajero(String identificadorPasajero, String nombre, int edad, ListaImpl<Pasajero> listaPorNacionalidad) {
        if (validadarDatosPasajero(identificadorPasajero, nombre, edad)) {
            if (validarIdentificador(identificadorPasajero)) {
                Retorno ret = insertar(new Pasajero(identificadorPasajero, nombre, edad), listaPorNacionalidad);
                if (ret.isOk()) {
                    return Retorno.ok();
                } else {
                    return Retorno.error3("");
                }
            }
            return Retorno.error2("");
        }
        return Retorno.error1("");
    }


    public static boolean validarIdentificador(String identificador) {

        if (identificador != null) {
            String cadena = "^(FR|DE|UK|ES|OT)[1-9]\\d{0,2}(\\.\\d{3}){0,2}#\\d$";
            return identificador.matches(cadena);
        }
        return false;
    }

    private boolean validadarDatosPasajero(String identificadorPasajero, String nombre, int edad) {
        return identificadorPasajero != null && !identificadorPasajero.isEmpty() && nombre != null && !nombre.isEmpty() && edad > 0;
    }

    public Retorno insertar(Pasajero nodoPasajero, ListaImpl<Pasajero> listaPorNacionalidad) {
        if (raiz == null) {
            raiz = new NodoABB(nodoPasajero);
            listaPorNacionalidad.insertar(nodoPasajero);
            return Retorno.ok();
        } else {
            Retorno ret = insertarRec(raiz, nodoPasajero, listaPorNacionalidad);
            if (ret.isOk())

                return Retorno.ok();
        }
        return Retorno.error3("");
    }

    private Retorno insertarRec(NodoABB nodo, Pasajero nodoPasajero, ListaImpl<Pasajero> listaPorNacionalidad) {
        if (!pertence(nodoPasajero.getIdentificador())) {
            if (nodo.getNodoPasajero().compareTo(nodoPasajero) > 0) {
                if (nodo.izq == null) {
                    nodo.izq = new NodoABB(nodoPasajero);
                    listaPorNacionalidad.insertar(nodoPasajero);
                    return Retorno.ok();
                } else {
                    return insertarRec(nodo.izq, nodoPasajero, listaPorNacionalidad);
                }
            } else if (nodo.getNodoPasajero().compareTo(nodoPasajero) < 0) {
                if (nodo.der == null) {
                    nodo.der = new NodoABB(nodoPasajero);
                    listaPorNacionalidad.insertar(nodoPasajero);
                    return Retorno.ok();
                } else {
                    return insertarRec(nodo.der, nodoPasajero, listaPorNacionalidad);
                }
            }
        }
        return Retorno.error3("");
    }

    public boolean pertence(String identificadorPasajero) {
        return pertenceRec(raiz, identificadorPasajero);
    }


    private boolean pertenceRec(NodoABB nodo, String identificadorPasajero) {
        if (nodo == null) {
            return false;
        } else if (convertirCadena(identificadorPasajero).equals(convertirCadena(nodo.getNodoPasajero().getIdentificador()))) {
            return true;
        } else if (convertirCadena(nodo.getNodoPasajero().getIdentificador()).compareTo(convertirCadena(identificadorPasajero)) > 0) {
            return pertenceRec(nodo.getIzq(), identificadorPasajero);
        } else {
            return pertenceRec(nodo.getDer(), identificadorPasajero);
        }

    }


    private Integer convertirCadena(String cadena) {
        String numero = cadena.replaceAll("[^0-9.]", "").replaceAll("\\.", "");
        String[] partes = numero.split("#"); // evita el numero detras del #
        return Integer.parseInt(partes[0]);
    }



    public Retorno filtrarPasajero(Consulta c) {
        resultado = "";
        resultado = filtrarPasajeroRec(raiz, c.getRaiz());
        return Retorno.ok(resultado);
    }

    private String filtrarPasajeroRec(NodoABB pasajero, Consulta.NodoConsulta c) {

        if (pasajero != null) {
            filtrarPasajeroRec(pasajero.getIzq(), c);
            if (filtrarPasajeroB(pasajero, c)){
                if(!resultado.isEmpty())
                {
                    resultado += "|";
                }
                resultado += pasajero.getNodoPasajero().getIdentificador();
            }
            filtrarPasajeroRec(pasajero.getDer(), c);
        }
        return resultado;
    }

    private boolean filtrarPasajeroB (NodoABB pasajero, Consulta.NodoConsulta c){
        if (pasajero != null) {
            if (c.getIzq() == null && c.getDer() == null) {
                if (c.getTipoNodoConsulta().equals(Consulta.TipoNodoConsulta.EdadMayor)) {
                    return pasajero.getNodoPasajero().getEdad() > c.getValorInt();
                }
                if (c.getTipoNodoConsulta().equals(Consulta.TipoNodoConsulta.Nacionalidad)) {
                    return pasajero.getNodoPasajero().getNacionalidad().equals(c.getValorNacionalidad());
                }
                if (c.getTipoNodoConsulta().equals(Consulta.TipoNodoConsulta.NombreIgual)) {
                    return pasajero.getNodoPasajero().getNombre().equals(c.getValorString());
                }
            }
            if (c.getTipoNodoConsulta().equals(Consulta.TipoNodoConsulta.And)) {
                return filtrarPasajeroB(pasajero, c.getIzq()) && filtrarPasajeroB(pasajero, c.getDer());
            }
            if (c.getTipoNodoConsulta().equals(Consulta.TipoNodoConsulta.Or)) {
                return filtrarPasajeroB(pasajero, c.getIzq()) || filtrarPasajeroB(pasajero, c.getDer());
            }
        }
        return false;
    }


    public Retorno buscarPasajero(String identificador) {
        if (!validarIdentificador(identificador)) {
            return Retorno.error1("");
        }
        if (!pertence(identificador)) {
            return Retorno.error2("");
        }
        int contador = 0;
        return buscarPasajeroRec(raiz, identificador, contador);
    }


    private Retorno buscarPasajeroRec(NodoABB nodo, String identificador, int contador) {

        String datosPasajero = "";
        if (nodo != null) {

            if (identificador.equals(nodo.getNodoPasajero().getIdentificador())) {
                datosPasajero = nodo.getNodoPasajero().getIdentificador() + ";" + nodo.getNodoPasajero().getNombre() + ";" + nodo.getNodoPasajero().getEdad() + ";" + nodo.getNodoPasajero().getNacionalidad().getCodigo();
                return Retorno.ok(contador, datosPasajero);
            } else if (convertirCadena(nodo.getNodoPasajero().getIdentificador()).compareTo(convertirCadena(identificador)) > 0) {
                contador++;
                return buscarPasajeroRec(nodo.izq, identificador, contador);
            } else {
                contador++;
                return buscarPasajeroRec(nodo.der, identificador, contador);
            }
        } else {
            return Retorno.ok();
        }

    }

    public Retorno listarPasajerosAscendente() {
        String datos = "";
        return Retorno.ok(listarPasajerosAscendenteRec(raiz, datos));
    }

    private String listarPasajerosAscendenteRec(NodoABB nodo, String datos) {
        if (nodo != null) {
            datos = listarPasajerosAscendenteRec(nodo.getIzq(), datos);
            datos += nodo.getNodoPasajero().toString();
            datos = listarPasajerosAscendenteRec(nodo.getDer(), datos);
        }
        if (!datos.isEmpty()) {
            return datos;
        }
        return "";
    }


    public Retorno listarPasajerosDescendente() {
        String datos = "";
        String resultado = listarPasajerosDescendenteRec(raiz, datos);
        if (!resultado.isEmpty()) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        return Retorno.ok(resultado);
    }

    private String listarPasajerosDescendenteRec(NodoABB nodo, String datos) {

        if (nodo != null) {
            datos = listarPasajerosDescendenteRec(nodo.getDer(), datos);
            datos += nodo.getNodoPasajero().toString();
            datos = listarPasajerosDescendenteRec(nodo.getIzq(), datos);

        }
        if (!datos.isEmpty()) {
            return datos;
        }
        return "";

    }



    public Retorno listarPasajerosPorNacionalidad (ListaImpl<Pasajero> listaPorNacionalidad){
        for (int i = 0; i < listaPorNacionalidad.largo(); i++) {
            if (listaPorNacionalidad.get(i) == null) {
                return Retorno.error1("");
            }
        }
        String resultado = obtenerStringPasajeros(listaPorNacionalidad);
        if (!resultado.isEmpty()) {
            resultado = resultado.substring(0, resultado.length() - 1);
        }
        return Retorno.ok(resultado);

    }

    public String obtenerStringPasajeros (ListaImpl<Pasajero> listaPorNacionalidad){
        String datos="";
        if (listaPorNacionalidad != null) {
            for (int i = 0; i < listaPorNacionalidad.largo(); i++) {
                Pasajero pasajero = listaPorNacionalidad.get(i);
                datos += pasajero.toString();
            }
        }
        if (!datos.isEmpty()) {
            return datos;
        }
        return "";
    }

}
