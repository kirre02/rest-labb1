package org.example.business;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.example.entity.Movie;
import org.example.dto.CreateMovie;
import org.example.dto.MovieResponse;
import org.example.dto.UpdateMovie;
import org.example.exceptions.NotFound;
import org.example.persistance.MovieRepository;

import java.util.List;
import java.util.Objects;

import static org.example.mapper.MovieMapper.*;


@ApplicationScoped
public class MovieService {

    private MovieRepository repository;

    @Inject
    public MovieService(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    public MovieService() {}

    public Movie createMovie(CreateMovie movie) {
        var newMovie = map(movie);
        newMovie = repository.insert(newMovie);
        return newMovie;
    }

    public void deleteMovie(long id) {
        var movie = repository.findById(id).orElseThrow(() -> new NotFound("Movie with id " + id + " not found"));
        repository.delete(movie);
    }

    public MovieResponse getMovieById(Long id) {
        return repository.findById(id).map(MovieResponse::new).orElseThrow( () -> new NotFound("Movie with id " + id + " not found"));
    }

    public List<MovieResponse> getAllMovies() {
        return repository.findAll()
                .map(MovieResponse::new)
                .filter(Objects::nonNull)
                .toList();
    }

    public void updateMovie(Long id, UpdateMovie movie) {
        var oldMovie = repository.findById(id).orElseThrow( () -> new NotFound("Movie with id " + id + " not found"));
        if (movie.title() != null) oldMovie.setTitle(movie.title());
        if (movie.director() != null) oldMovie.setDirector(movie.director());
        if (movie.duration() != 0) oldMovie.setDuration(movie.duration());
        if (movie.year() != 0) oldMovie.setYear(movie.year());
    }

}
