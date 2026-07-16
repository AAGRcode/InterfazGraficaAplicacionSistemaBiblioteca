package ec.edu.ups.models;

public class Prestamo {
    private int id;
    private Libro libro;
    private Usuario usuario;
    private Bibliotecario bibliotecario;
    private String fechaDevolucion;
    private EstadoPrestamo estado;

    public Prestamo() {
    }

    public Prestamo(int id, Libro libro, Usuario usuario, Bibliotecario bibliotecario, String fechaDevolucion) {
        this.id = id;
        this.libro = libro;
        this.usuario = usuario;
        this.bibliotecario = bibliotecario;
        this.fechaDevolucion = fechaDevolucion;
        this.estado = EstadoPrestamo.PRESTADO;
    }

    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }
    public Libro getLibro() { 
        return libro; 
    }
    public void setLibro(Libro libro) { 
        this.libro = libro; 
    }
    public Usuario getUsuario() { 
        return usuario; 
    }
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }
    public Bibliotecario getBibliotecario() { 
        return bibliotecario; 
    }
    public void setBibliotecario(Bibliotecario bibliotecario) { 
        this.bibliotecario = bibliotecario; 
    }
    public String getFechaDevolucion() { 
        return fechaDevolucion; 
    }
    public void setFechaDevolucion(String fechaDevolucion) { 
        this.fechaDevolucion = fechaDevolucion; 
    }
    public EstadoPrestamo getEstado() { 
        return estado; 
    }
    public void setEstado(EstadoPrestamo estado) { 
        this.estado = estado; 
    }

    public boolean isDevuelto() { 
        return estado == EstadoPrestamo.DEVUELTO; 
    }

    public void setDevuelto(boolean devuelto) {
        this.estado = devuelto ? EstadoPrestamo.DEVUELTO : EstadoPrestamo.PRESTADO;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "id=" + id + ", libro=" + libro + ", usuario=" + usuario + '}';
    }
}
