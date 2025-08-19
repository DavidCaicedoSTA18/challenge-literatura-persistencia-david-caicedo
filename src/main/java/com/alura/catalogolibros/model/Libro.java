package com.alura.catalogolibros.model;

import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;
    private Double numeroDescargas;

    public Libro() {}

    public Libro(DatosLibroResultado datos) {
        this.titulo = datos.titulo();
        this.idioma = datos.idiomas() != null && !datos.idiomas().isEmpty()
                ? datos.idiomas().get(0) : "desconocido";
        this.numeroDescargas = datos.numeroDescargas();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(Double numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

    @Override
    public String toString() {
        String nombreAutor = (autor != null) ? formatearNombreAutor(autor.getNombre()) : "Desconocido";
        return "Titulo: " + titulo + "\n" +
                "Autor: " + nombreAutor + "\n" +
                "Idioma: " + idioma + "\n" +
                "Numero de descargas: " + numeroDescargas;
    }

    private String formatearNombreAutor(String nombre) {
        if (nombre == null || !nombre.contains(",")) {
            return nombre;
        }
        // Si el nombre ya está en formato "Apellido, Nombre", lo dejamos así
        return nombre;
    }
}
