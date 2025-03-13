package org.example.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.example.dto.CreateMovie;
import org.example.dto.MovieResponse;
import org.example.entity.Movie;

class MovieMapperTest {

    @Test
    void testMapMovieToMovieResponse_Null() {
        // Test when the input movie is null
        MovieResponse movieResponse = MovieMapper.map((Movie) null);
        assertNull(movieResponse, "MovieResponse should be null when input is null");
    }

    @Test
    void testMapMovieToMovieResponse_ValidMovie() {
        // Test when the input movie is valid
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setTitle("Inception");
        movie.setDirector("Christopher Nolan");
        movie.setDuration(148);
        movie.setYear(2010);

        MovieResponse movieResponse = MovieMapper.map(movie);

        assertNotNull(movieResponse, "MovieResponse should not be null");
        assertEquals(movie.getId(), movieResponse.id(), "Id should match");
        assertEquals(movie.getTitle(), movieResponse.title(), "Title should match");
        assertEquals(movie.getDirector(), movieResponse.director(), "Director should match");
        assertEquals(movie.getDuration(), movieResponse.duration(), "Duration should match");
        assertEquals(movie.getYear(), movieResponse.year(), "Year should match");
    }

    @Test
    void testMapCreateMovieToMovie_Null() {
        // Test when the input CreateMovie is null
        Movie movie = MovieMapper.map((CreateMovie) null);
        assertNull(movie, "Movie should be null when input CreateMovie is null");
    }

    @Test
    void testMapCreateMovieToMovie_ValidCreateMovie() {
        // Test when the input CreateMovie is valid
        CreateMovie createMovie = new CreateMovie("Inception", "Christopher Nolan", 148, 2010);

        Movie movie = MovieMapper.map(createMovie);

        assertNotNull(movie, "Movie should not be null");
        assertEquals(createMovie.title(), movie.getTitle(), "Title should match");
        assertEquals(createMovie.director(), movie.getDirector(), "Director should match");
        assertEquals(createMovie.duration(), movie.getDuration(), "Duration should match");
        assertEquals(createMovie.year(), movie.getYear(), "Year should match");
    }
}
