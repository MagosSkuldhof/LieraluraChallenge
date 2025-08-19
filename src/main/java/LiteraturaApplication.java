package com.tuusuario.literatura;

import com.tuusuario.literatura.entity.AuthorEntity;
import com.tuusuario.literatura.entity.BookEntity;
import com.tuusuario.literatura.model.Book;
import com.tuusuario.literatura.service.GutendexService;
import com.tuusuario.literatura.repository.BookRepository;
import com.tuusuario.literatura.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private GutendexService gutendexService = new GutendexService();

    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) {
        mostrarMenu();
    }

    private void mostrarMenu() {
        int opcion = -1;

        do {
            System.out.println("\n=== Catálogo de Libros ===");
            System.out.println("1. Buscar libro por título y guardar en BD");
            System.out.println("2. Listar todos los libros");
            System.out.println("3. Listar autores de los libros");
            System.out.println("4. Listar autores vivos en un año");
            System.out.println("5. Estadísticas por idioma");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Opción inválida.");
                continue;
            }

            switch (opcion) {
                case 1:
                    buscarYGuardarLibro();
                    break;
                case 2:
                    listarTodosLosLibros();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    mostrarEstadisticasIdioma();
                    break;
                case 0:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void buscarYGuardarLibro() {
        System.out.print("Ingresa el título del libro: ");
        String titulo = scanner.nextLine();
        Book libro = gutendexService.buscarPorTitulo(titulo);

        if (libro != null) {
            AuthorEntity authorEntity = new AuthorEntity(
                    libro.getPrimaryAuthor().getName(),
                    libro.getPrimaryAuthor().getBirthYear(),
                    libro.getPrimaryAuthor().getDeathYear()
            );

            BookEntity bookEntity = new BookEntity(
                    libro.getTitle(),
                    libro.getPrimaryLanguage(),
                    libro.getDownloadCount(),
                    authorEntity
            );

            bookRepository.save(bookEntity);
            System.out.println("\nLibro encontrado y guardado en la BD:");
            System.out.println(libro);
        }
    }

    private void listarTodosLosLibros() {
        List<BookEntity> libros = bookRepository.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la base de datos.");
        } else {
            System.out.println("\n=== Libros en la BD ===");
            libros.forEach(b -> System.out.println(
                    "Título: " + b.getTitle() +
                            "\nAutor: " + b.getAuthor().getName() +
                            "\nIdioma: " + b.getLanguage() +
                            "\nDescargas: " + b.getDownloadCount() + "\n"
            ));
        }
    }

    private void listarAutores() {
        List<AuthorEntity> autores = authorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores en la base de datos.");
        } else {
            System.out.println("\n=== Autores ===");
            autores.forEach(a -> System.out.println(
                    a.getName() + " (" +
                            (a.getBirthYear() != null ? a.getBirthYear() : "?") + " - " +
                            (a.getDeathYear() != null ? a.getDeathYear() : "Vivo") + ")"
            ));
        }
    }

    private void listarAutoresVivos() {
        System.out.print("Ingresa el año: ");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Año inválido.");
            return;
        }

        List<AuthorEntity> autoresVivos = authorRepository
                .findByBirthYearLessThanEqualAndDeathYearGreaterThanEqualOrDeathYearIsNull(year, year);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en " + year);
        } else {
            System.out.println("\n=== Autores vivos en " + year + " ===");
            autoresVivos.forEach(a -> System.out.println(
                    a.getName() + " (" +
                            (a.getBirthYear() != null ? a.getBirthYear() : "?") + " - " +
                            (a.getDeathYear() != null ? a.getDeathYear() : "Vivo") + ")"
            ));
        }
    }

    private void mostrarEstadisticasIdioma() {
        System.out.println("1. Inglés");
        System.out.println("2. Español");
        System.out.print("Selecciona idioma: ");
        int opcionIdioma;
        try {
            opcionIdioma = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida.");
            return;
        }

        String idioma;
        switch (opcionIdioma) {
            case 1:
                idioma = "en";
                break;
            case 2:
                idioma = "es";
                break;
            default:
                System.out.println("Opción inválida.");
                return;
        }

        long cantidad = bookRepository.findByLanguageIgnoreCase(idioma).size();
        System.out.println("Cantidad de libros en " + idioma + ": " + cantidad);
    }
}
