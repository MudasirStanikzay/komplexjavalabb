package se.iths.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record CreateMovieDTO(
        @NotBlank(message = "Title must not be blank")
        @Size(max = 100, message = "Title must be at most 100 characters") String title,

        @Size(max = 1000, message = "Description must be at most 1000 characters") String description,

        @PastOrPresent(message = "Release date cannot be in the future") LocalDate releaseDate,

        @NotBlank(message = "Director must not be blank") String director,

        @Min(value = 1, message = "Duration must be at least 1 minute") int duration
) {}
