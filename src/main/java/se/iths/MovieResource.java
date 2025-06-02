package se.iths;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import se.iths.dto.CreateMovieDTO;
import se.iths.dto.MovieDTO;
import se.iths.dto.UpdateMovieDTO;

import java.util.List;

@ApplicationScoped
public class MovieResource {

    private final MovieService movieService;

    @Inject
    public MovieResource(MovieService movieService) {
        this.movieService = movieService;
    }

    public List<MovieDTO> getAllMovies() {
        return movieService.getAllMovies();
    }

    public MovieDTO getMovieById(Long id) {
        return movieService.getMovieById(id);
    }

    public MovieDTO createMovie(@Valid CreateMovieDTO createMovieDTO) {
        return movieService.createMovie(createMovieDTO);
    }

    public MovieDTO updateMovie(Long id, @Valid UpdateMovieDTO updateMovieDTO) {
        return movieService.updateMovie(id, updateMovieDTO);
    }

    public void deleteMovie(Long id) {
        movieService.deleteMovie(id);
    }
}
