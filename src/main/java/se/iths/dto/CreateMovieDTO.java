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

    public CreateMovieDTO() {
    }

    public CreateMovieDTO(String title, String description, LocalDate releaseDate, String director, int duration) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    public String title() {
        return null;
    }

    public String description() {
        return null;
    }

    public LocalDate releaseDate() {
        return null;
    }

    public String director() {
        return null;
    }

    public int duration() {
        return 0;
    }

    // Getters and setters ...
}
