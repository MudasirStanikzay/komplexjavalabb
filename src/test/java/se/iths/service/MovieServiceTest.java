package se.iths.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import se.iths.dto.CreateMovieDTO;
import se.iths.dto.MovieDTO;
import se.iths.dto.UpdateMovieDTO;
import se.iths.entity.Movie;
import se.iths.exception.ResourceNotFoundException;
import se.iths.mapper.MovieMapper;
import se.iths.repository.MovieRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private MovieMapper movieMapper;

    @InjectMocks
    private MovieServiceImpl movieService;

    private Movie movie;
    private MovieDTO movieDTO;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        movieService = new MovieServiceImpl(movieRepository, movieMapper);

        LocalDate releaseDate = LocalDate.of(2010, 7, 16);

        movie = new Movie(1L, "Inception", "Dreams within dreams", releaseDate, "Nolan", 148);
        movieDTO = new MovieDTO(1L, "Inception", "Dreams within dreams", "Nolan", 148, releaseDate);

        when(movieMapper.toDTO(movie)).thenReturn(movieDTO);
        when(movieMapper.toEntity(any(CreateMovieDTO.class))).thenReturn(movie);
    }


    @Test
    void createMovie_ShouldReturnSavedMovie() {
        CreateMovieDTO dto = new CreateMovieDTO("Inception", "Dreams within dreams", LocalDate.of(2010, 7, 16), "Nolan", 148);

        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        MovieDTO result = movieService.createMovie(dto);

        assertNotNull(result);
        assertEquals("Inception", result.title());
        verify(movieRepository).save(any(Movie.class));
    }

    @Test
    void getAllMovies_ShouldReturnListOfMovies() {
        when(movieRepository.findAll()).thenReturn((List<Movie>) Stream.of(movie));
        when(movieMapper.toDTO(movie)).thenReturn(movieDTO);

        List<MovieDTO> result = movieService.getAllMovies();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Inception", result.getFirst().title());
        verify(movieRepository).findAll();
    }

    @Test
    void getMovieById_ShouldReturnMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        MovieDTO result = movieService.getMovieById(1L);

        assertNotNull(result);
        assertEquals("Inception", result.title());
        verify(movieRepository).findById(1L);
    }

    @Test
    void getMovieById_ShouldThrowExceptionWhenMovieNotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        var ex = assertThrows(ResourceNotFoundException.class, () -> movieService.getMovieById(1L));
        assertEquals("Movie not found with ID: 1", ex.getMessage());
    }

    @Test
    void updateMovie_ShouldReturnUpdatedMovie() {
        UpdateMovieDTO dto = new UpdateMovieDTO("Inception", "Updated desc", LocalDate.of(2010, 7, 16), "Nolan", 150);

        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        doAnswer(_ -> {
            movie.setTitle(dto.title());
            movie.setDescription(dto.description());
            movie.setDirector(dto.director());
            movie.setDuration(dto.duration());
            movie.setReleaseDate(dto.releaseDate());
            return null;
        }).when(movieMapper).updateEntityFromDTO(eq(dto), eq(movie));
        when(movieRepository.save(movie)).thenReturn(movie);
        when(movieMapper.toDTO(movie)).thenReturn(movieDTO);

        MovieDTO result = movieService.updateMovie(1L, dto);

        assertNotNull(result);
        verify(movieRepository).save(movie);
    }

    @Test
    void updateMovie_ShouldThrowExceptionWhenMovieNotFound() {
        UpdateMovieDTO dto = new UpdateMovieDTO("Updated", "Nope", LocalDate.now(), "Dir", 100);

        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        var ex = assertThrows(ResourceNotFoundException.class, () -> movieService.updateMovie(1L, dto));
        assertEquals("Movie not found with ID: 1", ex.getMessage());
    }

    @Test
    void deleteMovie_ShouldDeleteMovie() {
        when(movieRepository.existsById(1L)).thenReturn(true);

        movieService.deleteMovie(1L);

        verify(movieRepository).deleteById(1L);
    }

    @Test
    void deleteMovie_ShouldThrowExceptionWhenMovieNotFound() {
        when(movieRepository.existsById(1L)).thenReturn(false);

        var ex = assertThrows(ResourceNotFoundException.class, () -> movieService.deleteMovie(1L));
        assertEquals("Movie not found to delete with ID: 1", ex.getMessage());
    }
}
