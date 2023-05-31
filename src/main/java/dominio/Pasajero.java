package dominio;

public class Pasajero implements Comparable<Pasajero> {

    private String identificador;
    private String nombre;
    private int edad;
    private Nacionalidad nacionalidad;

    public Pasajero(String identificador, String nombre, int edad) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.edad = edad;
        this.nacionalidad = Nacionalidad.fromCodigo(identificador.substring(0, 2));
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

    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }


    @Override
    public String toString() {
        return
                identificador + ';' +
                        nombre + ';' +
                        edad + ';' +
                        nacionalidad.getCodigo() + "|";

    }


    public int compareTo(Pasajero p) {
        int comparacionIdentificador = convertirCadena(this.identificador).compareTo(convertirCadena(p.identificador));

        if (comparacionIdentificador == 0) {
            return this.nombre.compareTo(p.nombre);
        } else if (comparacionIdentificador < 0) {
            return -1;
        } else {
            return 1;
        }
    }

    private Integer convertirCadena(String cadena) {
        String numero = cadena.replaceAll("[^0-9.]", "").replaceAll("\\.", "");
        String[] partes = numero.split("#"); // evita el numero detras del #
        return Integer.parseInt(partes[0]);
    }



}


