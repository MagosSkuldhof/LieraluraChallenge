import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GutendexService {

    private HttpClient client = HttpClient.newHttpClient();
    private ObjectMapper mapper = new ObjectMapper();

    public Book buscarPorTitulo(String titulo) {
        try {
            String url = "https://gutendex.com/books?search=" + titulo.replace(" ", "%20");
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            BookResponse bookResponse = mapper.readValue(response.body(), BookResponse.class);

            if (bookResponse.getResults() != null && !bookResponse.getResults().isEmpty()) {
                return bookResponse.getResults().get(0); // Tomamos el primer resultado
            } else {
                System.out.println("No se encontraron libros con ese título.");
                return null;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
