package org.example.mapper;

import org.example.entity.Movie;
import org.example.dto.CreateMovie;
import org.example.dto.MovieResponse;


public class MovieMapper {
    private MovieMapper() {}

    public static MovieResponse map(Movie movie) {
        if (movie == null) return null;
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getDuration(), movie.getYear());
    }

    public static Movie map(CreateMovie movie) {
        if (movie == null) return null;

        Movie newMovie = new Movie();
        newMovie.setTitle(movie.title());
        newMovie.setDirector(movie.director());
        newMovie.setDuration(movie.duration());
        newMovie.setYear(movie.year());

        return newMovie;
    }
}
