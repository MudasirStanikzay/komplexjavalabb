package se.iths.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate releaseDate;
    private String director;
    private int duration;

    // Standardkonstruktor krävs av JPA
    public Movie() {}

    // Fullständig konstruktor – perfekt för tester
    public Movie(Long id, String title, String description, LocalDate releaseDate, String director, int duration) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    // Konstruktor utan id – kan användas för skapande
    public Movie(String title, String description, LocalDate releaseDate, String director, int duration) {
        this.title = title;
        this.description = description;
        this.releaseDate = releaseDate;
        this.director = director;
        this.duration = duration;
    }

    // Getters och setters

}
