import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Book {
    private int id;

    @JsonAlias("title")
    private String title;

    private List<Author> authors;
    private List<String> languages;

    @JsonAlias("download_count")
    private int downloadCount;

    // Getters y setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Author getPrimaryAuthor() {
        return (authors != null && !authors.isEmpty()) ? authors.get(0) : new Author("desconocido", null, null);
    }
    @Override
    public String toString() {
        Author autor = getPrimaryAuthor();
        return "Título: " + title +
                "\nAutor: " + autor.getName() +
                "\nIdioma: " + getPrimaryLanguage() +
                "\nDescargas: " + downloadCount;
    }


    public List<String> getLanguages() { return languages; }
    public void setLanguages(List<String> languages) { this.languages = languages; }

    public int getDownloadCount() { return downloadCount; }
    public void setDownloadCount(int downloadCount) { this.downloadCount = downloadCount; }

    public String getPrimaryLanguage() {
        return (languages != null && !languages.isEmpty()) ? languages.get(0) : "desconocido";
    }

    @Override
    public String toString() {
        String autores = (authors != null) ? authors.stream().map(Author::getName).reduce((a, b) -> a + ", " + b).orElse("desconocido") : "desconocido";
        return "Título: " + title + "\nAutor: " + autores + "\nIdioma: " + getPrimaryLanguage() + "\nDescargas: " + downloadCount;
    }
}
