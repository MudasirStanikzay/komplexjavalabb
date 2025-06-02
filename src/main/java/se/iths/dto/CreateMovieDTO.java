package se.iths.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class CreateMovieDTO {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title must be at most 100 characters")
    private String title;

    @Size(max = 1000, message = "Description must be at most 1000 characters")
    private String description;

    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releaseDate;

    @NotBlank(message = "Director must not be blank")
    private String director;

    @Min(value = 1, message = "Duration must be at least 1 minute")
    private int duration;

    // Full constructor
    public CreateMovieDTO(String title, String description, LocalDate releaseDate, String director, int duration) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public int getDuration() {
        return duration;
    }

    // (Setters kan läggas till vid behov)
}
