package com.usuario.literatura; //Laparte de "usuario" se remplaza dpendiendo del ordenador al ser local el ejeemplo

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        mostrarMenu();
    }

    private void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("\n=== Catálogo de Libros ===");
            System.out.println("1. Buscar libro por título");
            System.out.println("2. Buscar libro por autor");
            System.out.println("3. Listar todos los libros");
            System.out.println("4. Filtrar por tema");
            System.out.println("5. Salir");
            System.out.print("Selecciona una opción: ");

            try {
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingresa un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Ingresa el título: ");
                    String titulo = scanner.nextLine();
                    // buscar por título
                    System.out.println("Buscando libro con título: " + titulo);
                    break;
                case 2:
                    System.out.print("Ingresa el autor: ");
                    String autor = scanner.nextLine();
                    // buscar por autor
                    System.out.println("Buscando libros de: " + autor);
                    break;
                case 3:
                    // listar todos los libros
                    System.out.println("Listando todos los libros...");
                    break;
                case 4:
                    System.out.print("Ingresa el tema: ");
                    String tema = scanner.nextLine();
                    // filtrar por tema
                    System.out.println("Filtrando libros por tema: " + tema);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    opcion = 0;
                    break;
                default:
                    System.out.println("Opción no válida. Intenta nuevamente.");
            }
        }

        scanner.close();
    }
}
