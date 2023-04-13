package com.exceptionHandling.springbootexceptionhandling.service;

import com.exceptionHandling.springbootexceptionhandling.exception.IdNotFoundException;
import com.exceptionHandling.springbootexceptionhandling.exception.NameAlreadyExistsException;
import com.exceptionHandling.springbootexceptionhandling.model.Movie;
import com.exceptionHandling.springbootexceptionhandling.repository.MovieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public List<Movie> getMovies(String name) {
        if(name == null){
            return movieRepository.findAll();
        }
        else {
            return movieRepository.findByName(name);
        }
    }

    public Movie getMovieById(String id) {
        return movieRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException("not found with id : " + id));
    }

    public Movie createMovie(Movie newMovie) {
        Optional<Movie>  movieByName = movieRepository.findAllByName(newMovie.getName());
        if(movieByName.isPresent()){
            throw new NameAlreadyExistsException("Already exists with name : " + newMovie.getName());
        }
        return movieRepository.save(newMovie);
    }

    public void updateMovie(String id, Movie newMovie) {
        Movie oldMovie = getMovieById(id);
        oldMovie.setName(newMovie.getName());
        oldMovie.setDirector(newMovie.getDirector());
        oldMovie.setYear(newMovie.getYear());
        movieRepository.save(oldMovie);

    }

    public void deleteMovie(String id) {
        movieRepository.deleteById(id);
    }
}
