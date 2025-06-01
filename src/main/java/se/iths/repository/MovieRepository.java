package se.iths.repository;

import se.iths.entity.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface MovieRepository {

    Optional<Movie> findById(Long id);

    List<Movie> findAll();

    List<Movie> findByDirector(String director);

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByDurationBetween(int min, int max);

    List<Movie> findByReleaseDateBetween(LocalDate start, LocalDate end);

    boolean existsById(Long id);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByDirectorContainingIgnoreCase(String director);

    List<Movie> findByTitleAndDirectorIgnoreCase(String title, String director);

    List<Movie> findByDurationGreaterThanEqual(int duration);

    <S extends Movie> S insert(S entity);

    <S extends Movie> List<S> insertAll(List<S> entities);

    <S extends Movie> S update(S entity);

    <S extends Movie> List<S> updateAll(List<S> entities);

    <S extends Movie> S save(S entity);

    <S extends Movie> List<S> saveAll(List<S> entities);

    void deleteById(Long id);

    void delete(Movie entity);

    void deleteAll(List<? extends Movie> entities);
}
