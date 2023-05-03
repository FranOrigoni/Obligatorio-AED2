package sistema;

public class Pasajero {

    private String identificador;
    private String nombre;
    private int edad;
    private String nacionalidad;

    public Pasajero(String identificador, String nombre, int edad) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = identificador.substring(0, 2);
    }


    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public boolean validarDatos(String identificadorPasajero, String nombre, int edad) {
        if (identificadorPasajero != null && !identificadorPasajero.isEmpty() && nombre != null && !nombre.isEmpty() && edad > 0) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Pasajero{" +
                "identificador='" + identificador + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", nacionalidad='" + nacionalidad + '\'' +
                '}';
    }
}


