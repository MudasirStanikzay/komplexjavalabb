package se.iths.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public class UpdateMovieDTO {

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

    public UpdateMovieDTO() {
    }

    public UpdateMovieDTO(String title, String description, LocalDate releaseDate, String director, int duration) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public LocalDate releaseDate() {
        return releaseDate;
    }

    public String director() {
        return director;
    }

    public int duration() {
        return duration;
    }

    // Getters and setters ...
}
