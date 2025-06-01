package se.iths.repository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import se.iths.entity.Movie;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class MovieRepositoryImpl implements MovieRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public Optional<Movie> findById(Long id) {
        return Optional.ofNullable(em.find(Movie.class, id));
    }

    @Override
    public List<Movie> findAll() {
        return em.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    @Override
    public List<Movie> findByDirector(String director) {
        return em.createQuery("SELECT m FROM Movie m WHERE m.director = :director", Movie.class)
                .setParameter("director", director)
                .getResultList();
    }

    @Override
    public List<Movie> findByTitleContaining(String title) {
        return em.createQuery("SELECT m FROM Movie m WHERE m.title LIKE :title", Movie.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    @Override
    public List<Movie> findByDurationBetween(int min, int max) {
        return em.createQuery("SELECT m FROM Movie m WHERE m.duration BETWEEN :min AND :max", Movie.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    @Override
    public List<Movie> findByReleaseDateBetween(LocalDate start, LocalDate end) {
        return em.createQuery("SELECT m FROM Movie m WHERE m.releaseDate BETWEEN :start AND :end", Movie.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    @Override
    public boolean existsById(Long id) {
        Long count = em.createQuery("SELECT COUNT(m) FROM Movie m WHERE m.id = :id", Long.class)
                .setParameter("id", id)
                .getSingleResult();
        return count > 0;
    }

    @Override
    public List<Movie> findByTitleContainingIgnoreCase(String title) {
        return em.createQuery("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(:title)", Movie.class)
                .setParameter("title", "%" + title + "%")
                .getResultList();
    }

    @Override
    public List<Movie> findByDirectorContainingIgnoreCase(String director) {
        return em.createQuery("SELECT m FROM Movie m WHERE LOWER(m.director) LIKE LOWER(:director)", Movie.class)
                .setParameter("director", "%" + director + "%")
                .getResultList();
    }

    @Override
    public List<Movie> findByTitleAndDirectorIgnoreCase(String title, String director) {
        return em.createQuery("SELECT m FROM Movie m WHERE LOWER(m.title) LIKE LOWER(:title) AND LOWER(m.director) LIKE LOWER(:director)", Movie.class)
                .setParameter("title", "%" + title + "%")
                .setParameter("director", "%" + director + "%")
                .getResultList();
    }
