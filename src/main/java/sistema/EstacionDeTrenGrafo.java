package sistema;

import interfaz.EstadoCamino;
import interfaz.Grafo;

public class EstacionDeTrenGrafo implements Grafo {

    //private String codigo;
    //private String nombre;
    private int cantMaxEstaciones;
    private boolean esDirigido;
    private int cantEstaciones;
    private Conexion[][] matrizAdyacencia;
    private boolean[] estacionesUsadas;
    private Estacion[] estacionesUsadasVertice;


    public EstacionDeTrenGrafo(int cantMaxEstaciones, boolean esDirigido) {
        this.esDirigido = esDirigido;
        this.cantMaxEstaciones = cantMaxEstaciones;
        this.matrizAdyacencia = new Conexion[cantMaxEstaciones + 1][cantMaxEstaciones + 1];
        this.estacionesUsadas = new boolean[cantMaxEstaciones + 1];
        this.estacionesUsadasVertice = new Estacion[cantMaxEstaciones + 1];

        for (int i = 1; i <= cantMaxEstaciones; i++) {
            for (int j = 1; j <= cantMaxEstaciones; j++) {
                matrizAdyacencia[i][j] = new Conexion();
            }
        }
    }

    @Override
    public void agregarEstacion(String codigo, String nombre, int indice) {
        cantEstaciones++;
        estacionesUsadas[indice] = true;
        //Estacion estacion = new Estacion(nombre,codigo,indice);
        //estacionesUsadasVertice[indice];
    }

    @Override
    public void agregarConexion(String codigoEstacionOrigen, String codigoEstacionDestino, int identificadorConexion, double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion) {
         //   matrizAdyacencia[codigoEstacionOrigen][codigoEstacionDestino]=new Conexion(costo,tiempo,kilometros,estadoDeLaConexion);
            if(!esDirigido){
             //   matrizAdyacencia[codigoEstacionOrigen][codigoEstacionDestino]=matrizAdyacencia[codigoEstacionOrigen][codigoEstacionDestino];
            }
        }


    @Override
    public void eliminarEstacion(int v) {

    }

    @Override
    public void eliminarConexion(int origen, int destino) {

    }

    @Override
    public boolean existeEstacion(int v) {
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


    private class Conexion {
        public double costo;
        public double tiempo;
        public double kilometros;
        public EstadoCamino estadoDeLaConexion;

        public Conexion( double costo, double tiempo, double kilometros, EstadoCamino estadoDeLaConexion) {
            this.costo = costo;
            this.tiempo = tiempo;
            this.kilometros = kilometros;
            this.estadoDeLaConexion = estadoDeLaConexion;
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
    }


    private class Estacion{
        private String nombre;
        private String codigo;
        private int indice;

        public Estacion(String nombre, String codigo, int indice) {
            this.nombre = nombre;
            this.codigo = codigo;
            this.indice = indice;
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

        public int getIndice() {
            return indice;
        }

        public void setIndice(int indice) {
            this.indice = indice;
        }
    }



}
