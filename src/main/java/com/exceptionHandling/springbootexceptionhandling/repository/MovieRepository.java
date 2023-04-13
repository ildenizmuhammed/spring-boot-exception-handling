package com.exceptionHandling.springbootexceptionhandling.repository;

import com.exceptionHandling.springbootexceptionhandling.model.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie,String> {
    List<Movie> findByName(String name);

    Optional<Movie> findAllByName(String name);
}
