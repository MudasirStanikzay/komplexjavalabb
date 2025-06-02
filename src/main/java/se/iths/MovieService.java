package se.iths;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import se.iths.dto.CreateMovieDTO;
import se.iths.dto.MovieDTO;
import se.iths.dto.UpdateMovieDTO;
import se.iths.entity.Movie;
import se.iths.mapper.MovieMapper;
import se.iths.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MovieService {

    @Inject
    MovieRepository movieRepository;

    @Inject
    MovieMapper movieMapper;

    @Transactional
    public MovieDTO createMovie(CreateMovieDTO createMovieDTO) {
        Movie movie = movieMapper.toEntity(createMovieDTO);
        movieRepository.save(movie);
        return movieMapper.toDTO(movie);
    }

    public List<MovieDTO> getAllMovies() {
        return StreamSupport.stream(movieRepository.findAll().spliterator(), false)
                .map(movieMapper::toDTO)
                .collect(Collectors.toList());
    }

    public MovieDTO getMovieById(Long id) {
        return movieRepository.findById(id)
                .map(movieMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Movie not found with ID: " + id));
    }

    @Transactional
    public MovieDTO updateMovie(Long id, UpdateMovieDTO updateMovieDTO) {
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found to update with ID: " + id));

        existingMovie.setTitle(updateMovieDTO.title());
        existingMovie.setDirector(updateMovieDTO.director());
        existingMovie.setDuration(updateMovieDTO.duration());

        movieRepository.save(existingMovie);
        return movieMapper.toDTO(existingMovie);
    }

    @Transactional
    public void deleteMovie(Long id) {
        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found to delete with ID: " + id);
        }
        movieRepository.deleteById(id);
    }
}
