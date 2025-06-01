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

    @NotBlank(message = "Titel måste anges")
    @Size(max = 100, message = "Maxlängd för titel är 100 tecken")
    private String title;

    @Size(max = 1000, message = "Beskrivning får max vara 1000 tecken lång")
    private String description;

    @NotNull(message = "Datum för utgivning krävs")
    @PastOrPresent(message = "Utgivningsdatum kan inte vara i framtiden")
    private LocalDate releaseDate;

    @NotBlank(message = "Regissör måste anges")
    private String director;

    @Min(value = 1, message = "Filmens längd måste vara minst 1 minut")
    private int duration;

    // --- Konstruktorer ---

    public Movie() {
        // Default-konstruktor krävs av JPA
    }

    public Movie(String title, String director, int duration) {
        this.title = title;
        this.director = director;
        this.duration = duration;
    }

    public Movie(String title, String description, LocalDate releaseDate, String director, int duration) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    // --- Getters och Setters ---

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
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
