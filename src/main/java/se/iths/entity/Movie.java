package se.iths.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titel får inte vara tom")
    @Size(max = 100, message = "Titel får ha högst 100 tecken")
    private String title;

    @Size(max = 500, message = "Beskrivning får ha högst 500 tecken")
    private String description;

    @NotNull(message = "Utgivningsdatum måste anges")
    private LocalDate releaseDate;

    @NotBlank(message = "Regissör får inte vara tom")
    private String director;

    @Min(value = 1, message = "Längden måste vara större än 0 minuter")
    private int duration;

    public Movie() {
    }

    public Movie(String title, String description, LocalDate releaseDate, String director, int duration) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    // --- Getters & Setters ---

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
