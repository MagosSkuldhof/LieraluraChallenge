import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Author {
    private String name;
    private Integer birthYear;  // año de nacimiento
    private Integer deathYear;  // año de fallecimiento, null si sigue vivo

    public Author() {}
    public Author(String name, Integer birthYear, Integer deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Integer getBirthYear() { return birthYear; }
    public void setBirthYear(Integer birthYear) { this.birthYear = birthYear; }

    public Integer getDeathYear() { return deathYear; }
    public void setDeathYear(Integer deathYear) { this.deathYear = deathYear; }

    public boolean isAliveInYear(int year) {
        boolean born = (birthYear == null || birthYear <= year);
        boolean died = (deathYear != null && deathYear < year);
        return born && !died;
    }

    @Override
    public String toString() {
        return name + ((birthYear != null) ? " (" + birthYear + " - " + ((deathYear != null) ? deathYear : "Vivo") + ")" : "");
    }
}
