package sistema;

import interfaz.EstadoCamino;
import interfaz.Grafo;
import interfaz.Retorno;
import lista.Lista;
import lista.ListaImpl;

import java.awt.*;

public class EstacionDeTrenGrafo implements Grafo {

    private int cantMaxEstaciones;
    private boolean esDirigido;
    private int cantEstaciones;
   // private Conexion[][] matrizAdyacencia;
    private boolean[] estacionesUsadas;
    private Estacion[] estacionesVertices;
    private Lista<Conexion>[][] matrizAdyacencia;


    public EstacionDeTrenGrafo(int cantMaxEstaciones, boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.cantMaxEstaciones = cantMaxEstaciones;
        //this.matrizAdyacencia = new Conexion[cantMaxEstaciones + 1][cantMaxEstaciones + 1];
        this.matrizAdyacencia = new Lista[cantMaxEstaciones][cantMaxEstaciones];
        this.estacionesUsadas = new boolean[cantMaxEstaciones + 1];
        this.estacionesVertices = new Estacion[cantMaxEstaciones + 1];

        for (int i = 1; i <= cantMaxEstaciones; i++) {
            for (int j = 1; j <= cantMaxEstaciones; j++) {
                matrizAdyacencia[i][j] = new ListaImpl<>();
                //matrizAdyacencia[i][j] = new Conexion(); // se inicializa un arista en cada parte de la matriz y con eso podemos preguntar si existe una arsita o no, Atributo existe
            }
        }
    }


    @Override
    public Retorno agregarEstacion(String codigo, String nombre) {
        if (cantMaxEstaciones < cantEstaciones) {
            if (validarDatos(codigo, nombre))
                if (validarCodigo(codigo)) {
                    if (!existeEstacion(codigo)) {
                        cantEstaciones++;
                        int posLibre = obtenerPosLibre();
                        if (posLibre >= 0) {
                            estacionesVertices[posLibre] = new Estacion(nombre, codigo);
                            return Retorno.ok();
                        }
                        return Retorno.error3("Codigo invalido");
                    }
                    return Retorno.error4("Ya existe una estacion con ese codigo");
                }
            return Retorno.error2("Dato vacio o null");
        }
        return Retorno.error1("ya hay registrados maxEstaciones");
    }


    private boolean validarDatos(String codigo, String nombre) {
        if (codigo.isEmpty() || nombre.isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean validarCodigo(String codigo) {
        String cadena = "^[A-Z]{3}\\d{3}$";
        return codigo.matches(cadena);
      /*
        ^ y $: Representan el inicio y final de la cadena, asegurando que no haya caracteres adicionales antes o después del código.
        [A-Z]: Representa cualquier letra de la "A" a la "Z".
        {3}: Indica que se espera exactamente 3 repeticiones de la expresión anterior (letras mayúsculas).
        \\d: Representa cualquier dígito del 0 al 9.
        {3}: Indica que se espera exactamente 3 repeticiones de la expresión anterior (dígitos).
       */
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
            if (estacionesVertices[i] != null && estacionesVertices[i].equals(new Estacion(codigo)))
                return i; // retorno la posicion en el array
        }
        return -1;
    }

    // TODO Ver el error 6 de la letra
    @Override
    public Retorno agregarConexion(String codigoEstacionOrigen, String codigoEstacionDestino, int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion) {
//TODO NO PUEDE EXITIR 2 CONEXIONES CON EL MISMO ID ENTRE 2 EsTACIONES
        if (validarDoublesConexion(costo, tiempo, kilometros)) {

            if (validarStringConexion(codigoEstacionOrigen, codigoEstacionDestino, estadoDeLaConexion)) {

                if (validarCodigo(codigoEstacionOrigen) && validarCodigo(codigoEstacionDestino)) {

                    if (existeEstacion(codigoEstacionOrigen)) {

                        if (existeEstacion(codigoEstacionDestino)) {

                            int obtenerPosOrigen = obtenerPos(codigoEstacionOrigen); // obtiene el indice del array
                            int obtenerPosDestino = obtenerPos(codigoEstacionDestino);// obtiene el indice del array

                            if (obtenerPosOrigen >= 0 && obtenerPosDestino >= 0) {
                                if (esDirigido) {
                                    matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino].insertar(new Conexion(costo, tiempo, kilometros, estadoDeLaConexion,identificadorConexion)); // hace la matriz con esos indices y genera la conexion entre ellos
                                    return Retorno.ok();
                                } else {
                                    matrizAdyacencia[obtenerPosDestino][obtenerPosOrigen] = matrizAdyacencia[obtenerPosOrigen][obtenerPosDestino];
                                    return Retorno.ok();
                                }
                            }
                        }
                        return Retorno.error5("no existe la estación de destino.");
                    }
                    return Retorno.error4("no existe la estación de origen");
                }
                return Retorno.error3("alguno de los códigos de estación no es válido.");
            }
            return Retorno.error2("alguno de los parámetros String o enum es vacío o null");
        }
        return Retorno.error1("alguno de los parámetros double es menor o igual a 0");
    }


    private boolean validarDoublesConexion(double costo, double tiempo, double kilometros) {
        if (costo <= 0 | tiempo <= 0 | kilometros <= 0) {
            return false;
        }
        return true;
    }

    private boolean validarStringConexion(String codigoEstacionOrigen, String codigoEstacionDestino, Enum estadoDeLaConexion) {
        if (codigoEstacionOrigen.isEmpty() || codigoEstacionDestino.isEmpty() | estadoDeLaConexion == null) {
            return false;
        }
        return true;
    }

    /*

    @Override
    public void eliminarEstacion(String codigo) {
        cantEstaciones--;
        int posVert = obtenerPos(codigo);
        if (posVert >= 0) {
            estacionesVertices[posVert] = null;
            for (int i = 1; i <= cantMaxEstaciones; i++) {
                matrizAdyacencia[posVert][i].setExiste(false); //filas
                matrizAdyacencia[posVert][i].setCosto(0); //filas
                matrizAdyacencia[posVert][i].setKilometros(0); //filas
                matrizAdyacencia[posVert][i].setTiempo(0); //filas
                matrizAdyacencia[posVert][i].setEstadoDeLaConexion(null);

                matrizAdyacencia[i][posVert].setExiste(false); //columnas
                matrizAdyacencia[i][posVert].setCosto(0); //columnas
                matrizAdyacencia[i][posVert].setKilometros(0); //columnas
                matrizAdyacencia[i][posVert].setTiempo(0);
                matrizAdyacencia[i][posVert].setEstadoDeLaConexion(null);
            }
        }

    }

    @Override
    public void eliminarConexion(int origen, int destino) {
          matrizAdyacencia[origen][destino].setExiste(false);
          matrizAdyacencia[origen][destino].setCosto(0);
          matrizAdyacencia[origen][destino].setKilometros(0);
          matrizAdyacencia[origen][destino].setTiempo(0);
          matrizAdyacencia[origen][destino].setEstadoDeLaConexion(null);
    }

*/

    @Override
    public boolean existeEstacion(String codigo) {
        for (Estacion e : estacionesVertices) {
            if (e.codigo.equals(codigo)) {
                return true;
            }
        }
        return false;
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

        public void setCosto(int costo) {
            this.costo = costo;
        }

        public double getTiempo() {
            return tiempo;
        }

        public void setTiempo(int tiempo) {
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

        @Override
        public int compareTo(Conexion o) {
            return 0;
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


}
