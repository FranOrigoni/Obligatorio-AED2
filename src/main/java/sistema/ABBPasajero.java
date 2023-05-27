package sistema;

import interfaz.Consulta;
import interfaz.Retorno;
import interfaz.Nacionalidad;
import lista.ListaImpl;

public class ABBPasajero {
    private NodoABB raiz;


    public Retorno registrarPasajero(String identificadorPasajero, String nombre, int edad) {
        if (validadarDatosPasajero(identificadorPasajero, nombre, edad)) {
            if (validarIdentificador(identificadorPasajero)) {
                Retorno ret = insertar(new Pasajero(identificadorPasajero, nombre, edad));
                if (ret.isOk()) {
                    return Retorno.ok();
                }else {
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
            //Explicacion de validador // Ver si no conviene implementar metodo en ABBPasajero
             /*
    El indentificadoir debe comenzar (^) con una de las cinco abreviaturas de país: "FR", "DE", "UK", "ES" o "OT"
    [1-9] es una clase de caracteres que representa cualquier dígito del 1 al 9.
    d{0,2} puede haber un número de 0 a 2 dígitos despues de la abrevitartua del país
    (.\d{3})El punto decimal se representa con el carácter "." y el dígito {3} indica que debe haber exactamente tres dígitos después del punto decimal símbolo "{}" se utiliza para indicar una cantidad específica de dígitos
    {0,2}  indica que este patrón de tres dígitos separados por un punto puede repetirse de 0 a 2 veces, lo que significa que se pueden tener cero, uno o dos grupos de tres dígitos separados por punto
    #\d$ significa que debe haber un símbolo de número (#) seguido de un dígito al final de la cadena  "\d" representa cualquier dígito del 0 al 9, y el signo "$" indica que la cadena debe terminar después de este dígito
*/
        }
        return false;
    }

    private boolean validadarDatosPasajero(String identificadorPasajero, String nombre, int edad) {
        return identificadorPasajero != null && !identificadorPasajero.isEmpty() && nombre != null && !nombre.isEmpty() && edad > 0;
    }


    public Retorno insertar(Pasajero nodoPasajero) {
        if (raiz == null) {
            raiz = new NodoABB(nodoPasajero);
            return Retorno.ok();
        } else {
            Retorno ret = insertarRec(raiz, nodoPasajero);
            if (ret.isOk())
                return Retorno.ok();
        }
        return Retorno.error3("");
    }

    public NodoABB getRaiz() {
        return raiz;
    }

    private Retorno insertarRec(NodoABB nodo, Pasajero nodoPasajero) {
        if (!pertence(nodoPasajero.getIdentificador())) {
            if (nodo.getNodoPasajero().compareTo(nodoPasajero) > 0) {
                if (nodo.izq == null) {
                    nodo.izq = new NodoABB(nodoPasajero);
                    return Retorno.ok();
                } else {
                    return insertarRec(nodo.izq, nodoPasajero);
                }
            } else if (nodo.getNodoPasajero().compareTo(nodoPasajero) < 0) {
                if (nodo.der == null) {
                    nodo.der = new NodoABB(nodoPasajero);
                    return Retorno.ok();
                } else {
                    return insertarRec(nodo.der, nodoPasajero);
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
        }
           else if (identificadorPasajero.equals(nodo.getNodoPasajero().getIdentificador())) {
                return true;
            } else if (nodo.getNodoPasajero().getIdentificador().compareTo(identificadorPasajero) > 0) {
                return pertenceRec(nodo.getIzq(), identificadorPasajero);
            } else {
               return pertenceRec(nodo.getDer(), identificadorPasajero);
            }

        }








    /*
    private boolean pertenceRec(NodoABB nodo, String identificadorPasajero) {
        if (nodo == null) {
            return false;
        }

        if (identificadorPasajero == nodo.getNodoPasajero().getIdentificador()) {
            return true;
        }

        if (Integer.parseInt(identificadorPasajero) < Integer.parseInt(nodo.getNodoPasajero().getIdentificador())) {
            return pertenceRec(nodo.getIzq(), identificadorPasajero);
        } else {
            return pertenceRec(nodo.getDer(), identificadorPasajero);
        }
    }

     */

    public void imprimirDatos() {
        imprimirDatos(raiz);
    }

    public void imprimirDatos(NodoABB nodo) {
        if (nodo != null) {
            System.out.println(nodo.getNodoPasajero().getNombre());
            imprimirDatos(nodo.getIzq());
            imprimirDatos(nodo.getDer());
        } else {
            //no hago nada
        }
    }

    public void vaciarArbol() {
        vaciarArbol(raiz);
    }

    private void vaciarArbol(NodoABB nodo){
        if (nodo == null) {
            return;
        }

        vaciarArbol(nodo.izq);
        vaciarArbol(nodo.der);

        nodo.izq = null;
        nodo.der = null;
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

    public Retorno filtrarPasajero(Consulta c) {
        return filtrarPasajero(raiz, c.getRaiz());
    }


    private boolean filtrarPasajeroB(NodoABB pasajero, Consulta.NodoConsulta c) {
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

    private Retorno filtrarPasajero(NodoABB pasajero, Consulta.NodoConsulta c) {

        String resultado = "";
        if (c == null) {
            return Retorno.error1("");
        }
        if (pasajero == null) {
            return Retorno.ok(resultado);
        }
        if (filtrarPasajeroB(pasajero, c)) {
            resultado += "|" + pasajero.getNodoPasajero().getIdentificador();
        }
        return Retorno.ok(resultado + filtrarPasajero(pasajero.getIzq(), c) + filtrarPasajero(pasajero.getDer(), c));

    }


    public Retorno buscarPasajero(String identificador) {
        if (!validarIdentificador(identificador)) {
            return Retorno.error1("");
        }
        if (!pertence(identificador)) {
            return Retorno.error2("");
        }
        int contador = 0;
        return buscarPasajeroRec(raiz, identificador,contador);
    }


    private Retorno buscarPasajeroRec(NodoABB nodo, String identificador,int contador) {

        String datosPasajero = "";
        if (nodo != null) {

            if (identificador.equals(nodo.getNodoPasajero().getIdentificador())) {

                datosPasajero = nodo.getNodoPasajero().getIdentificador() + ";" + nodo.getNodoPasajero().getNombre() + ";" + nodo.getNodoPasajero().getEdad() + ";" + nodo.getNodoPasajero().getNacionalidad().getCodigo();
                return Retorno.ok(contador, datosPasajero);
            } else if (nodo.getNodoPasajero().getIdentificador().compareTo(identificador) > 0) {
                contador++;
                return buscarPasajeroRec(nodo.izq, identificador,contador);
            } else {
                contador++;
                return buscarPasajeroRec(nodo.der, identificador,contador);
            }
        } else {
            return null;
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
        return datos;
    }


    public Retorno listarPasajerosDescendente() {
        String datos = "";
        return Retorno.ok(listarPasajerosDescendenteRec(raiz, datos));
    }

    private String listarPasajerosDescendenteRec(NodoABB nodo, String datos) {
        if (nodo != null) {
            datos = listarPasajerosDescendenteRec(nodo.getDer(), datos);
            datos += nodo.getNodoPasajero().toString();
            datos = listarPasajerosDescendenteRec(nodo.getIzq(), datos);
        }


        return datos;
    }


    public Retorno listarPasajerosPorNacionalidad(Nacionalidad nacionalidad) {
        String datos = "";
        if (nacionalidad == null) {
            return Retorno.error1("");
        }
        return Retorno.ok(listarPasajerosPorNacionalidadRec(nacionalidad, raiz, datos));
    }

    public String listarPasajerosPorNacionalidadRec(Nacionalidad nacionalidad, NodoABB pasajero, String datos) {
        ListaImpl<String> datosPasajero = new ListaImpl<>();

        if (pasajero != null) {
            if (pasajero.getNodoPasajero().getNacionalidad().equals(nacionalidad)) {
                datos += pasajero.getNodoPasajero().getIdentificador() + ";" + pasajero.getNodoPasajero().getNombre() + ";" + pasajero.getNodoPasajero().getEdad() + ";" + pasajero.getNodoPasajero().getNacionalidad() + "\n";
                datosPasajero.insertar(datos);
            }
            datos = listarPasajerosPorNacionalidadRec(nacionalidad, pasajero.getIzq(), datos);
            datos = listarPasajerosPorNacionalidadRec(nacionalidad, pasajero.getDer(), datos);
        }


        return datosPasajero.toString();
    }
 /*
    public int compareTo(Pasajero p) {
        int comparacionIdentificador = convertirCadena(this.getRaiz().getNodoPasajero().getIdentificador()).compareTo(convertirCadena(p.getIdentificador()));

        if (comparacionIdentificador == 0) {
            return this.getRaiz().getNodoPasajero().getNombre().compareTo(p.getNombre());
        } else if (comparacionIdentificador < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    private String soloNumeros(String cadena1) {
        return cadena1.replaceAll("[^\\d.]", "");
    }

    private Integer convertirCadena(String cadena) {
        String resultado = "";
        for (char caracter : cadena.toCharArray()) {
            if (Character.isDigit(caracter)) {
                resultado += caracter;
            } else if (caracter == '#') {
                break;
            }
        }
        return Integer.parseInt(resultado);
    }


  */

}
