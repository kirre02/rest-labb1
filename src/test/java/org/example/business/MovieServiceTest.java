package org.example.business;

import org.example.dto.CreateMovie;
import org.example.dto.MovieResponse;
import org.example.dto.UpdateMovie;
import org.example.entity.Movie;
import org.example.exceptions.NotFoundException;
import org.example.persistance.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;  // Mocked repository


    private MovieService movieService;  // Inject mocks

    private Movie movie;

    @BeforeEach
    void setUp() {
        movieService = new MovieService(movieRepository);

        movie = new Movie();
        movie.setId(1L);
        movie.setTitle("test");
        movie.setDirector("test");
        movie.setYear(2018);
    }


    @Test
    void testCreateMovie() {
        // Create the Movie object you want to return
        Movie createMovie = new Movie();
        createMovie.setId(2L);
        createMovie.setTitle("Inception");
        createMovie.setDirector("Christopher Nolan");
        createMovie.setDuration(148);
        createMovie.setYear(2010);

        when(movieRepository.insert(any(Movie.class))).thenReturn(createMovie);

        CreateMovie createdMovieDTO = new CreateMovie("Inception", "Christopher Nolan", 148, 2010);

        Movie createdMovie = movieService.createMovie(createdMovieDTO);

        assertNotNull(createdMovie);
        assertEquals("Inception", createdMovie.getTitle());
        verify(movieRepository, times(1)).insert(any(Movie.class));
    }

    @Test
    void testDeleteMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));
        doNothing().when(movieRepository).delete(movie);

        movieService.deleteMovie(1L);

        verify(movieRepository, times(1)).delete(movie);
    }

    @Test
    void testDeleteMovie_NotFound() {
        when(movieRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundException.class, () -> movieService.deleteMovie(1L));

        assertEquals("Movie with id 1 not found", exception.getMessage());
    }

    @Test
    void testGetMovieById() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        MovieResponse response = movieService.getMovieById(1L);

        assertNotNull(response);
        assertEquals("test", response.title());
    }

    @Test
    void testUpdateMovie() {
        when(movieRepository.findById(1L)).thenReturn(Optional.of(movie));

        UpdateMovie updateMovie = new UpdateMovie("Interstellar", "Christopher Nolan", "Matthew McConaughey", 169, 2014);
        movieService.updateMovie(1L, updateMovie);

        assertEquals("Interstellar", movie.getTitle());
        assertEquals("Christopher Nolan", movie.getDirector());
        assertEquals(169, movie.getDuration());
        assertEquals(2014, movie.getYear());
    }

    @Test
    void testGetAllMovies() {
        when(movieRepository.findAll()).thenReturn(List.of(movie).stream());

        List<MovieResponse> movies = movieService.getAllMovies();

        assertEquals(1, movies.size());
        assertEquals("test", movies.get(0).title());
    }
}
