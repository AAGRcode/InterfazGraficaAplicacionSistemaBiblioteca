package ec.edu.ups.models;

public class Bibliotecario {
    private String nombre;
    private String cedula;
    private int edad;
    private String codigoEmpleado;

    public Bibliotecario() {}

    public Bibliotecario(String nombre, String cedula, int edad, String codigoEmpleado) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.edad = edad;
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(String codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    @Override
    public String toString() {
        return "Bibliotecario{" + "nombre=" + nombre + ", codigoEmpleado=" + codigoEmpleado + '}';
    }
}
