package se.iths.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.time.LocalDate;

/**
 * @param title Getters
 */
@Getter
public record UpdateMovieDTO(
        @NotBlank(message = "Title must not be blank") @Size(max = 100, message = "Title must be at most 100 characters") String title,
        @Size(max = 1000, message = "Description must be at most 1000 characters") String description,
        @PastOrPresent(message = "Release date cannot be in the future") LocalDate releaseDate,
        @NotBlank(message = "Director must not be blank") String director,
        @Min(value = 1, message = "Duration must be at least 1 minute") int duration) {

    // Full constructor
    public UpdateMovieDTO(String title, String description, LocalDate releaseDate, String director, int duration) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    // (Setters kan läggas till vid behov)
}
