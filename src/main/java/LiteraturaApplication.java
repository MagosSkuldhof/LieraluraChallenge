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
