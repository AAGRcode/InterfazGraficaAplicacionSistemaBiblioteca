package ec.edu.ups.models;

public class Libro {
    private int codigo;
    private String titulo;
    private String categoria;
    private int anioPublicacion;
    private Autor autor;

    public Libro() {}

    public Libro(int codigo, String titulo, String categoria, int anioPublicacion, Autor autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    

    @Override
    public String toString() {
        return titulo;
    }
}
