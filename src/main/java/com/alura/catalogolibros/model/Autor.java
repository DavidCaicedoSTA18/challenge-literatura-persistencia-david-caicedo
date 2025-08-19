package com.alura.catalogolibros.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor() {}

    public Autor(DatosAutor datos) {
        this.nombre = formatearNombre(datos.nombre());
        this.fechaNacimiento = datos.fechaNacimiento();
        this.fechaFallecimiento = datos.fechaFallecimiento();
    }

    private String formatearNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            return "Desconocido";
        }

        // Verificar si el nombre ya está en formato "Apellido, Nombre"
        if (nombre.contains(",")) {
            return nombre;
        }

        // Dividir el nombre en partes
        String[] partes = nombre.split(" ");
        if (partes.length >= 2) {
            // Asumir que la última parte es el apellido
            String apellido = partes[partes.length - 1];
            String nombres = String.join(" ", java.util.Arrays.copyOfRange(partes, 0, partes.length - 1));
            return apellido + ", " + nombres;
        }

        return nombre;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        String librosStr = libros.stream()
                .map(Libro::getTitulo)
                .collect(Collectors.joining(", "));

        return "Autor: " + nombre + "\n" +
                "Fecha de nacimiento: " + (fechaNacimiento != null ? fechaNacimiento : "Desconocida") + "\n" +
                "Fecha de fallecimiento: " + (fechaFallecimiento != null ? fechaFallecimiento : "Desconocida") + "\n" +
                "Libros: [" + librosStr + "]";
    }
}
