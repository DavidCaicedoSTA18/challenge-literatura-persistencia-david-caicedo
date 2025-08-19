package com.alura.catalogolibros.principal;

import com.alura.catalogolibros.model.Autor;
import com.alura.catalogolibros.model.DatosLibro;
import com.alura.catalogolibros.model.Libro;
import com.alura.catalogolibros.repository.AutorRepository;
import com.alura.catalogolibros.repository.LibroRepository;
import com.alura.catalogolibros.service.ConsumoAPI;
import com.alura.catalogolibros.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos conversor = new ConvierteDatos();

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    
                    Elija la opcion a traves de su número:
                    1- Buscar libro por titulo
                    2- Listar libros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos en un determinado año
                    5- Listar libros por idioma
                    0- Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();

        var json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
        var datosBusqueda = conversor.obtenerDatos(json, DatosLibro.class);

        if (datosBusqueda.resultados() != null && !datosBusqueda.resultados().isEmpty()) {
            var primerLibro = datosBusqueda.resultados().get(0);

            // Verificar si el libro ya existe en la base de datos
            Optional<Libro> libroExistente = libroRepository.findByTitulo(primerLibro.titulo());

            if (libroExistente.isPresent()) {
                System.out.println("\nNo se puede registrar el mismo libro más de una vez");
            } else {
                // Crear o buscar el autor
                Autor autor = null;
                if (primerLibro.autor() != null && !primerLibro.autor().isEmpty()) {
                    var datosAutor = primerLibro.autor().get(0);

                    // Buscar si el autor ya existe
                    Optional<Autor> autorExistente = autorRepository.findByNombre(datosAutor.nombre());

                    if (autorExistente.isPresent()) {
                        autor = autorExistente.get();
                    } else {
                        autor = new Autor(datosAutor);
                        autor = autorRepository.save(autor);
                    }
                }

                // Crear y guardar el libro
                Libro libro = new Libro(primerLibro);
                libro.setAutor(autor);
                libroRepository.save(libro);

                System.out.println("\n------------ LIBRO ---------------");
                System.out.println(libro);
                System.out.println("-----------------------------------");
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }

    private void listarLibrosRegistrados() {
        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados");
        } else {
            libros.forEach(libro -> {
                System.out.println("\n--------- LIBRO ---------------");
                System.out.println(libro);
                System.out.println("-----------------------------------");
            });
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados");
        } else {
            //autores.forEach(System.out::println);
            autores.forEach(autor -> {
                System.out.println("\n--------- AUTOR ---------------");
                System.out.println(autor);
                System.out.println("-----------------------------------");
            });
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        var anio = teclado.nextInt();
        teclado.nextLine();

        List<Autor> autoresVivos = autorRepository.findAutoresVivosEnAnio(anio);

        if (autoresVivos.isEmpty()) {
            System.out.println("No hay autores vivos en ese año");
        } else {
            autoresVivos.forEach(autor -> {
                System.out.println("\n--------- AUTOR ---------------");
                System.out.println(autor);
                System.out.println("-----------------------------------");
            });
        }
    }

    private void listarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);
        var idioma = teclado.nextLine();

        List<Libro> librosPorIdioma = libroRepository.findByIdioma(idioma);

        if (librosPorIdioma.isEmpty()) {
            System.out.println("No hay libros en ese idioma");
        } else {
            librosPorIdioma.forEach(libro -> {
                System.out.println("\n--------- LIBRO ---------------");
                System.out.println(libro);
            });
        }
    }
}
