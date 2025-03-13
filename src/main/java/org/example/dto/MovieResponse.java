package org.example.dto;

import org.example.entity.Movie;

public record MovieResponse(Long id, String title, String director, int duration, int year) {
    public MovieResponse(Movie movie) {
        this(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getDuration(), movie.getYear());
    }

    public static MovieResponse map(Movie movie) {
        return new MovieResponse(movie.getId(), movie.getTitle(), movie.getDirector(), movie.getDuration(), movie.getYear());
    }
}
