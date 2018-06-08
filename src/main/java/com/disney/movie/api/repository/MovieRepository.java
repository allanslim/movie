package com.disney.movie.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.disney.movie.api.model.Movie;

@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

}
