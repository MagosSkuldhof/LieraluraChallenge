import java.util.ArrayList;
import java.util.List;

private List<Book> librosGuardados = new ArrayList<>();
private GutendexService service = new GutendexService();

case 1:
        System.out.print("Ingresa el título: ");
String titulo = scanner.nextLine();
Book libro = service.buscarPorTitulo(titulo);
    if (libro != null) {
        librosGuardados.add(libro);
        System.out.println("\nLibro encontrado:");
        System.out.println(libro);
    }
            break;

            case 3:
            if (librosGuardados.isEmpty()) {
        System.out.println("No se han buscado libros aún.");
    } else {
            System.out.println("\n=== Libros buscados ===");
        librosGuardados.forEach(System.out::println);
    }
            break;

            case 4: // Listar autores de los libros buscados
            if (librosGuardados.isEmpty()) {
        System.out.println("No se han buscado libros aún.");
    } else {
            System.out.println("\n=== Autores de los libros buscados ===");
        librosGuardados.stream()
                .map(Book::getPrimaryAuthor)
                .distinct()
                .forEach(System.out::println);
    }
            break;

            case 5: // Listar autores vivos en determinado año
            System.out.print("Ingresa el año: ");
int year;
    try {
year = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
        System.out.println("Año inválido.");
        break;
                }
                System.out.println("\n=== Autores vivos en " + year + " ===");
    librosGuardados.stream()
            .map(Book::getPrimaryAuthor)
            .filter(a -> a.isAliveInYear(year))
        .distinct()
            .forEach(System.out::println);
    break;
