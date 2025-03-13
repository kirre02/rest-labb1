    package org.example.resource;

    import jakarta.inject.Inject;
    import jakarta.validation.Valid;
    import jakarta.validation.constraints.NotNull;
    import jakarta.ws.rs.*;
    import jakarta.ws.rs.core.MediaType;
    import jakarta.ws.rs.core.Response;
    import lombok.extern.java.Log;
    import org.example.entity.Movie;
    import org.example.business.MovieService;
    import org.example.dto.CreateMovie;
    import org.example.dto.ListMovie;
    import org.example.dto.MovieResponse;
    import org.example.dto.UpdateMovie;


    @Path("movies")
    @Log
    public class MovieResource {

        private MovieService movieService;

        @Inject
        public MovieResource(MovieService movieService) {
            this.movieService = movieService;
        }


        public MovieResource() {}


        @GET
        @Path("test")
        public Response testApi() {
            return Response.ok("API is working!").build();
        }

        //"http://localhost:8080/api/movies"
        @GET
        @Produces(MediaType.APPLICATION_JSON)
        public ListMovie getBooks() {
            return new ListMovie(movieService.getAllMovies());
        }

        //"http://localhost:8080/api/movies/{id}"
        @GET
        @Path("{id}")
        @Produces(MediaType.APPLICATION_JSON)
        public MovieResponse getOneBook(@PathParam("id") Long id) {
            return movieService.getMovieById(id);
        }

        @DELETE
        @Path("{id}")
        public Response deleteOneBook(@PathParam("id") Long id) {
            movieService.deleteMovie(id);
            return Response.noContent().build();
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public Response createNewBook(@Valid @NotNull CreateMovie movie) {
            if( movie == null)
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("Book cannot be null").build();
            Movie newMovie = movieService.createMovie(movie);
            log.info("Creating new book: " + newMovie);
            return Response
                    .status(Response.Status.CREATED)
                    .header("Location", "/api/movies/" + newMovie.getId())
                    .build();
        }


        //"http://localhost:8080/api/movies/{id}"
        @PATCH
        @Path("{id}")
        @Consumes(MediaType.APPLICATION_JSON)
        public Response updateBookFieldByField(@Valid UpdateMovie movie, @PathParam("id") Long id) {
            movieService.updateMovie(id, movie);
            return Response.noContent().build();
        }
    }
