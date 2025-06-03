package se.iths.mapper;

import org.junit.jupiter.api.Test;
import se.iths.dto.CreateMovieDTO;
import se.iths.dto.MovieDTO;
import se.iths.dto.UpdateMovieDTO;
import se.iths.entity.Movie;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class MovieMapperTest {

    private final MovieMapper mapper = new MovieMapper();

    @Test
    void shouldMapFromEntityToDto() {
        Movie movie = new Movie(1L, "Inception", "Thriller", LocalDate.of(2010, 7, 16), "Nolan", 148);
        MovieDTO dto = mapper.toDto(movie);

        assertEquals(1L, dto.id());
        assertEquals("Inception", dto.title());
        assertEquals("Thriller", dto.description());
        assertEquals("Nolan", dto.director());
        assertEquals(148, dto.duration());
        assertEquals(LocalDate.of(2010, 7, 16), dto.releaseDate());
    }

    @Test
    void shouldMapFromCreateDtoToEntity() {
        CreateMovieDTO dto = new CreateMovieDTO("Inception", "Thriller", LocalDate.of(2010, 7, 16), "Nolan", 148);
        Movie movie = mapper.fromCreateDto(dto);

        assertEquals("Inception", movie.getTitle());
        assertEquals("Thriller", movie.getDescription());
        assertEquals("Nolan", movie.getDirector());
        assertEquals(148, movie.getDuration());
        assertEquals(LocalDate.of(2010, 7, 16), movie.getReleaseDate());
    }

    @Test
    void shouldUpdateEntityFromUpdateDto() {
        Movie movie = new Movie("Old Title", "Old Desc", LocalDate.of(2000, 1, 1), "Old Director", 90);
        UpdateMovieDTO updateDto = new UpdateMovieDTO("New Title", "New Desc", LocalDate.of(2020, 5, 5), "New Director", 120);

        mapper.updateEntity(movie, updateDto);

        assertEquals("New Title", movie.getTitle());
        assertEquals("New Desc", movie.getDescription());
        assertEquals("New Director", movie.getDirector());
        assertEquals(120, movie.getDuration());
        assertEquals(LocalDate.of(2020, 5, 5), movie.getReleaseDate());
    }
}
