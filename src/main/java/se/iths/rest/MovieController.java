package se.iths.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import se.iths.MovieService;
import se.iths.dto.CreateMovieDTO;
import se.iths.dto.MovieDTO;
import se.iths.dto.UpdateMovieDTO;

import java.util.List;

@ApplicationScoped
public class MovieController {

    @Inject
    MovieService movieService;

    public MovieDTO createMovie(CreateMovieDTO dto) {
        return movieService.createMovie(dto);
    }

    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    public MovieDTO getMovieById(Long id) {
        return movieService.getMovieById(id);
    }

    public MovieDTO updateMovie(Long id, UpdateMovieDTO dto) {
        return movieService.updateMovie(id, dto);
    }

    public void deleteMovie(Long id) {
        movieService.deleteMovie(id);
    }
}
