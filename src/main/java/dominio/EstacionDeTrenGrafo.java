package dominio;

import cola.Cola;
import interfaz.Grafo;
import interfaz.Lista;
import lista.ListaImpl;

public class EstacionDeTrenGrafo implements Grafo {

    private int cantMaxEstaciones;
    private boolean esDirigido;
    private int cantEstaciones;
    private Estacion[] estacionesVertices;
    private Lista<Conexion>[][] matrizAdyacencia;


    public EstacionDeTrenGrafo(int cantMaxEstaciones, boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.cantMaxEstaciones = cantMaxEstaciones;
        this.matrizAdyacencia = new Lista[cantMaxEstaciones + 1][cantMaxEstaciones + 1];
        this.estacionesVertices = new Estacion[cantMaxEstaciones];

        for (int i = 0; i <= cantMaxEstaciones; i++) {
            for (int j = 0; j <= cantMaxEstaciones; j++) {
                matrizAdyacencia[i][j] = new ListaImpl<>();
            }
        }
    }


    @Override
    public Retorno agregarEstacion(String codigo, String nombre) {
        if (cantEstaciones < cantMaxEstaciones) {
            if (validarDatos(codigo, nombre)) {
                if (validarCodigo(codigo)) {
                    if (!existeEstacion(codigo)) {
                        cantEstaciones++;
                        int posLibre = obtenerPosLibre();
                        if (posLibre >= 0) {
                            estacionesVertices[posLibre] = new Estacion(nombre, codigo);
                            return Retorno.ok();
                        }
                    }
                    return Retorno.error4("");
                }
                return Retorno.error3("");
            }
            return Retorno.error2("");
        }
        return Retorno.error1("");
    }


    private boolean validarDatos(String codigo, String nombre) {
        if (codigo == null || codigo.isEmpty() || nombre == null || nombre.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validarCodigo(String codigo) {
        String cadena = "^[A-Z]{3}\\d{3}$";
        return codigo.matches(cadena);
    }


    @Override
    public Retorno agregarConexion(String codigoEstacionOrigen, String codigoEstacionDestino, int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion) {

        if (validarDoublesConexion(costo, tiempo, kilometros, identificadorConexion)) {

            if (validarStringConexion(codigoEstacionOrigen, codigoEstacionDestino, estadoDeLaConexion)) {

                if (validarCodigo(codigoEstacionOrigen) && validarCodigo(codigoEstacionDestino)) {

                    if (existeEstacion(codigoEstacionOrigen)) {

                        if (existeEstacion(codigoEstacionDestino)) {

                            int obtenerPosOrigen = obtenerPos(codigoEstacionOrigen); // obtiene el indice del array
                            int obtenerPosDestino = obtenerPos(codigoEstacionDestino);// obtiene el indice del array

                            if (obtenerPosOrigen >= 0 && obtenerPosDestino >= 0) {
                                if (esDirigido) {
                                    if (!existeConexionEntreOyD(obtenerPosOrigen, obtenerPosDestino, identificadorConexion)) {
                                        matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino].insertar(new Conexion(costo, tiempo, kilometros, estadoDeLaConexion, identificadorConexion)); // hace la matriz con esos indices y genera la conexion entre ellos
                                        return Retorno.ok();
                                    } else {
                                        return Retorno.error6("");
                                    }
                                } else {
                                    matrizAdyacencia[obtenerPosDestino][obtenerPosOrigen] = matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino];
                                    return Retorno.ok();
                                }
                            }

                        }
                        return Retorno.error5("");
                    }
                    return Retorno.error4("");
                }
                return Retorno.error3("");
            }
            return Retorno.error2("");
        }
        return Retorno.error1("");
    }


    private int obtenerPosLibre() {
        for (int i = 0; i < estacionesVertices.length; i++) {
            if (estacionesVertices[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int obtenerPos(String codigo) {
        for (int i = 0; i < cantMaxEstaciones; i++) {
            if (estacionesVertices[i] != null && estacionesVertices[i].getCodigo().equals(codigo))
                return i;
        }
        return -1;
    }

    @Override
    public Retorno actualizarCamino(String codigoEstacionOrigen, String codigoEstacionDestino, int identificadorCamino, double costo, double tiempo, double kilometros, EstadoCamino estadoDelCamino) {
        if (validarDoublesConexion(costo, tiempo, kilometros, identificadorCamino)) {

            if (validarStringConexion(codigoEstacionOrigen, codigoEstacionDestino, estadoDelCamino)) {

                if (validarCodigo(codigoEstacionOrigen) && validarCodigo(codigoEstacionDestino)) {

                    if (existeEstacion(codigoEstacionOrigen)) {

                        if (existeEstacion(codigoEstacionDestino)) {

                            int obtenerPosOrigen = obtenerPos(codigoEstacionOrigen); // obtiene el indice del array
                            int obtenerPosDestino = obtenerPos(codigoEstacionDestino);// obtiene el indice del array

                            if (obtenerPosOrigen >= 0 && obtenerPosDestino >= 0) {

                                if (existeConexionEntreOyD(obtenerPosOrigen, obtenerPosDestino, identificadorCamino)) {
                                    Conexion conexActualizar = new Conexion(costo, tiempo, kilometros, estadoDelCamino, identificadorCamino);
                                    actualizarConexionEntreOyD(obtenerPosOrigen, obtenerPosDestino, conexActualizar);
                                    return Retorno.ok();
                                } else {
                                    return Retorno.error6("");
                                }
                            }

                        }
                        return Retorno.error5("");
                    }
                    return Retorno.error4("");
                }
                return Retorno.error3("");
            }
            return Retorno.error2("");
        }
        return Retorno.error1("");
    }

    private boolean validarDoublesConexion(double costo, double tiempo, double kilometros, int identificadorConexion) {
        if (costo <= 0 | tiempo <= 0 | kilometros <= 0 | identificadorConexion <= 0) {
            return false;
        }
        return true;
    }

    private boolean validarStringConexion(String codigoEstacionOrigen, String codigoEstacionDestino, Enum estadoDeLaConexion) {
        if (codigoEstacionOrigen == null || codigoEstacionOrigen.isEmpty() || codigoEstacionDestino == null || codigoEstacionDestino.isEmpty() || estadoDeLaConexion == null) {
            return false;
        }
        return true;
    }
    private boolean existeConexionEntreOyD(int obtenerPosOrigen, int obtenerPosDestino, int identificadorConexion) {

        ListaImpl<Conexion> con = (ListaImpl<Conexion>) matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino];
        if (con != null) {
            for (int i = 0; i < con.largo(); i++) {
                Conexion conexion = con.get(i);
                if (conexion.getIdConexion() == identificadorConexion) {
                    return true; // El identificador de conexión ya está en la lista
                }
            }
        }
        return false; // El identificador de conexión no se encuentra en la lista
    }

    private void actualizarConexionEntreOyD(int obtenerPosOrigen, int obtenerPosDestino, Conexion conexActualizar) {
        ListaImpl<Conexion> con = (ListaImpl<Conexion>) matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino];
        if (con != null) {
            for (int i = 0; i < con.largo(); i++) {
                Conexion conexion = con.get(i);
                if (conexion.getIdConexion() == conexActualizar.getIdConexion()) {
                    conexion.setIdConexion(conexActualizar.getIdConexion());
                    conexion.setEstadoDeLaConexion(conexActualizar.estadoDeLaConexion);
                    conexion.setTiempo(conexActualizar.getTiempo());
                    conexion.setKilometros(conexActualizar.getKilometros());
                    conexion.setCosto(conexActualizar.getCosto());
                    conexion.setExiste(true);
                }
            }
        }
    }

    @Override
    public Retorno viajeCostoMinimoKilometros(String codigoEstacionOrigen, String codigoEstacionDestino, int parametro) {
        return dijkstra(codigoEstacionOrigen, codigoEstacionDestino, parametro);
    }

    @Override
    public Retorno viajeCostoMinimoEuros(String codigoEstacionOrigen, String codigoEstacionDestino, int parametro) {
        return dijkstra(codigoEstacionOrigen, codigoEstacionDestino, parametro);
    }

    public Retorno dijkstra(String codigoEstacionOrigen, String codigoEstacionDestino, int parametro) {

        boolean[] visitados = new boolean[cantMaxEstaciones];
        double[] caminoCorto = new double[cantMaxEstaciones];
        Estacion[] vengo = new Estacion[cantMaxEstaciones];

        int posOri = obtenerPos(codigoEstacionOrigen);


        if (codigoEstacionOrigen == null || codigoEstacionOrigen.isEmpty() || codigoEstacionDestino == null || codigoEstacionDestino.isEmpty()) {
            return Retorno.error1("");
        }
        if (!validarCodigo(codigoEstacionOrigen) || !validarCodigo(codigoEstacionDestino)) {
            return Retorno.error2("");
        }
        if (!existeEstacion(codigoEstacionOrigen)) {
            return Retorno.error4("");
        }
        if (!existeEstacion(codigoEstacionDestino)) {
            return Retorno.error5("");
        }


        for (int i = 0; i < cantMaxEstaciones; i++) {
            caminoCorto[i] = Integer.MAX_VALUE;
        }


        caminoCorto[posOri] = 0;
        for (int v = 0; v < cantEstaciones; v++) { //cantEstaciones en esta varible llevamos la cuenta de la cantidad de vertices que agregamos
            int posV = obetenerSiguienteEstacionNoVisitadaDeMenorCosto(caminoCorto, visitados);
            if (posV >= 0) {
                visitados[posV] = true;
                for (int i = 0; i < cantMaxEstaciones; i++) {  //Voy a buscar adyacentes
                    if (!matrizAdyacencia[posV][i].esVacia() && !visitados[i]) {
                        //Aqui obtuve para analizar un vertice que es adyacente y no fue visitado

                        if (parametro == 1) {
                            if (obtenerConexionMasBarataPorKilometro(matrizAdyacencia[posV][i]) != null) {
                                double distanciaNueva = caminoCorto[posV] + obtenerConexionMasBarataPorKilometro(matrizAdyacencia[posV][i]).getKilometros();

                                if (caminoCorto[i] > distanciaNueva) {
                                    caminoCorto[i] = distanciaNueva;
                                    vengo[i] = estacionesVertices[posV];
                                }
                            }
                        } else {
                            if (obtenerConexionMasBarata(matrizAdyacencia[posV][i]) != null) {
                                double distanciaNueva = caminoCorto[posV] + obtenerConexionMasBarata(matrizAdyacencia[posV][i]).getCosto();

                                if (caminoCorto[i] > distanciaNueva) {
                                    caminoCorto[i] = distanciaNueva;
                                    vengo[i] = estacionesVertices[posV];
                                }
                            }
                        }
                    }
                }
            }
        }


        String camino = "";
        int posDestino = obtenerPos(codigoEstacionDestino);
        camino = estacionesVertices[posDestino].getCodigo() + ";" + estacionesVertices[posDestino].getNombre() + camino;

        int posDestinoAux = obtenerPos(codigoEstacionDestino);
        Estacion estacionAnt = vengo[posDestinoAux];
        if (estacionAnt != null) {
            camino = estacionAnt.getCodigo() + ";" + estacionAnt.getNombre() + "|" + camino;

            while (estacionAnt != null) {
                posDestinoAux = obtenerPos(estacionAnt.getCodigo());
                estacionAnt = vengo[posDestinoAux];
                if (estacionAnt != null)
                    camino = estacionAnt.getCodigo() + ";" + estacionAnt.getNombre() + "|" + camino;
            }
        }
        if (!existeConexionEntreOyDSinCamino(obtenerPos(codigoEstacionDestino), visitados)) {
            return Retorno.error3("");
        }

        return Retorno.ok((int) caminoCorto[posDestino], camino);
    }

    private boolean existeConexionEntreOyDSinCamino(int obtenerPosDestino, boolean[] visitados) {
        if (!visitados[obtenerPosDestino]) {
            return false;
        }
        return true; // Hay camino
    }


    private Conexion obtenerConexionMasBarata(Lista<Conexion> conexionLista) {

        double minCosto = Integer.MAX_VALUE;
        Conexion resultado = null;
        for (int x = 0; x < conexionLista.largo(); x++) {
            Conexion conexion = conexionLista.get(x);
            if (!conexion.getEstadoDeLaConexion().equals(EstadoCamino.MALO)) {
                if (conexion.getCosto() < minCosto) {
                    resultado = conexion;
                    minCosto = conexion.getCosto();
                }
            }
        }
        return resultado;
    }


    private Conexion obtenerConexionMasBarataPorKilometro(Lista<Conexion> conexiones) {

        double minKms = Integer.MAX_VALUE;
        Conexion resultado = null;
        for (int x = 0; x < conexiones.largo(); x++) {
            Conexion conexion = conexiones.get(x);
            if (!conexion.getEstadoDeLaConexion().equals(EstadoCamino.MALO)) {
                if (conexion.getKilometros() < minKms) {
                    resultado = conexion;
                    minKms = conexion.getKilometros();
                }
            }
        }
        return resultado;
    }


    private int obetenerSiguienteEstacionNoVisitadaDeMenorCosto(double[] caminoCorto, boolean[] visitados) { // devuelve el vertice NO VISTIADO de menor costo
        int posDelMininimo = -1;
        double min = Integer.MAX_VALUE;

        for (int i = 0; i < cantMaxEstaciones; i++) {
            if (!visitados[i] && caminoCorto[i] < min) { // cualquier numero que le pase va ser mas chico que el minimo
                min = caminoCorto[i];
                posDelMininimo = i;
            }
        }
        return posDelMininimo; // pos que tiene los kilometros  mas chico y que no ha sido visitado

    }


    private String ordenarEstaciones(String datos) {
        String[] elementos = datos.split("\\|");

        for (int i = 0; i < elementos.length - 1; i++) {
            for (int j = i + 1; j < elementos.length; j++) {
                String codigo1 = obtenerCodigo(elementos[i]);
                String codigo2 = obtenerCodigo(elementos[j]);

                if (codigo1.compareTo(codigo2) > 0) {
                    String temp = elementos[i];
                    elementos[i] = elementos[j];
                    elementos[j] = temp;
                }
            }
        }
        String resultado = String.join("|", elementos);
        return resultado;
    }

    private static String obtenerCodigo(String elemento) {
        String[] partes = elemento.split(";");
        return partes[0];
    }


    private class Conexion implements Comparable<Conexion> {

        public int idConexion;
        public boolean existe;
        public double costo;
        public double tiempo;
        public double kilometros;
        public EstadoCamino estadoDeLaConexion;

        public Conexion(double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion, int idConexion) {
            this.costo = costo;
            this.tiempo = tiempo;
            this.kilometros = kilometros;
            this.estadoDeLaConexion = estadoDeLaConexion;
            this.idConexion = idConexion;
            this.existe = true;
        }

        public Conexion() {

        }

        public double getCosto() {
            return costo;
        }

        public void setCosto(double costo) {
            this.costo = costo;
        }

        public double getTiempo() {
            return tiempo;
        }

        public void setTiempo(double tiempo) {
            this.tiempo = tiempo;
        }

        public double getKilometros() {
            return kilometros;
        }

        public void setKilometros(double kilometros) {
            this.kilometros = kilometros;
        }

        public EstadoCamino getEstadoDeLaConexion() {
            return estadoDeLaConexion;
        }

        public void setEstadoDeLaConexion(EstadoCamino estadoDeLaConexion) {
            this.estadoDeLaConexion = estadoDeLaConexion;
        }

        public boolean isExiste() {
            return existe;
        }

        public void setExiste(boolean existe) {
            this.existe = existe;
        }


        public int getIdConexion() {
            return idConexion;
        }

        public void setIdConexion(int idConexion) {
            this.idConexion = idConexion;
        }

        @Override
        public int compareTo(Conexion o) {
            return 0;
        }

    }


    private class Tupla {
        private int pos;
        private int salto;

        public Tupla(int pos, int salto) {
            this.pos = pos;
            this.salto = salto;
        }

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public int getSalto() {
            return salto;
        }

        public void setSalto(int salto) {
            this.salto = salto;
        }
    }


    private class Estacion {
        private String nombre;
        private String codigo;

        public Estacion(String nombre, String codigo) {
            this.nombre = nombre;
            this.codigo = codigo;
        }

        public Estacion(String codigo) {
            this.codigo = codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }
    }



    @Override
    public boolean existeEstacion(String codigo) {

        for (Estacion e : estacionesVertices) {
            if (e != null && e.codigo.equals(codigo)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public Retorno listadoEstacionesCantTrasbordos(String codigo, int cantidad) {
        if (cantidad < 0) {
            return Retorno.error1("");
        }
        if (codigo == null || codigo.isEmpty()) {
            return Retorno.error2("");
        }
        if (!validarCodigo(codigo)) {
            return Retorno.error3("");
        }
        if (!existeEstacion(codigo)) {
            return Retorno.error4("");
        }
        String datos = "";
        int pos = obtenerPos(codigo);
        boolean[] visitados = new boolean[cantMaxEstaciones];
        Cola<Tupla> cola = new Cola<>();
        visitados[pos] = true;
        cola.encolar(new Tupla(pos, 0)); // agregar mi vertice de arranque, mas las aristas en esta caso 0al ser la primera
        while (!cola.esVacia()) {
            Tupla tuplaDesencolada = cola.desencolar(); // de este desencolado hay que revisar sus adyacentes
            System.out.println(estacionesVertices[tuplaDesencolada.pos]);
            for (int i = 0; i < cantMaxEstaciones; i++) {

                ListaImpl<Conexion> con = (ListaImpl<Conexion>) matrizAdyacencia[tuplaDesencolada.pos][i];
                if (!con.esVacia() && !visitados[i]) {
                    cola.encolar(new Tupla(i, tuplaDesencolada.salto + 1));
                    visitados[i] = true;
                }
            }
            if (tuplaDesencolada.getSalto() <= cantidad) {
                datos += estacionesVertices[tuplaDesencolada.pos].getCodigo() + ";" + estacionesVertices[tuplaDesencolada.pos].getNombre() + "|";
            }
        }


        return Retorno.ok(ordenarEstaciones(datos));

    }




    @Override
    public boolean sonAdyacentes(int a, int b) {
        return false;
    }

    @Override
    public boolean esVacio() {
        return false;
    }

    @Override
    public boolean estaLlena() {
        return false;
    }


}
