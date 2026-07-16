package ec.edu.ups.models;

public class Libro {
    private int codigo;
    private String titulo;
    private Categoria categoria;
    private int anioPublicacion;
    private Autor autor;
    private boolean estaDisponible;

    public Libro() {}

    public Libro(int codigo, String titulo, Categoria categoria, int anioPublicacion, Autor autor) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.categoria = categoria;
        this.anioPublicacion = anioPublicacion;
        this.autor = autor;
        this.estaDisponible = true;
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
    public Categoria getCategoria() { 
        return categoria; 
    }
    public void setCategoria(Categoria categoria) { 
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
    public boolean isEstaDisponible() { 
        return estaDisponible; 
    }
    public void setEstaDisponible(boolean estaDisponible) { 
        this.estaDisponible = estaDisponible; 
    }
    

    @Override
    public String toString() {
        return titulo;
    }
}
