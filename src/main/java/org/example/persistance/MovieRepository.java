package org.example.persistance;

import jakarta.data.repository.CrudRepository;
import jakarta.data.repository.Find;
import jakarta.data.repository.Repository;
import org.example.entity.Movie;

import java.util.Optional;

@Repository
public interface MovieRepository extends CrudRepository<Movie, Long> {

    @Find
    Optional<Movie> findByTitle(String title);

    @Find
    Optional<Movie> findByDirector(String director);

    @Find
    Optional<Movie> findByDuration(int duration);
}
