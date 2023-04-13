package com.exceptionHandling.springbootexceptionhandling.controller;

import com.exceptionHandling.springbootexceptionhandling.exception.IdNotFoundException;
import com.exceptionHandling.springbootexceptionhandling.exception.NameAlreadyExistsException;
import com.exceptionHandling.springbootexceptionhandling.model.Movie;
import com.exceptionHandling.springbootexceptionhandling.service.MovieService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/movies")
@AllArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getMovies(@RequestParam(required = false) String name){
        return new ResponseEntity<>(movieService.getMovies(name), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable String id){
        return new ResponseEntity<>(getMovieById(id), OK);
    }

    private Movie getMovieById(String id) {
        return movieService.getMovieById(id);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie newMovie){
        return new ResponseEntity<>(movieService.createMovie(newMovie), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateMovie(@PathVariable String id, @RequestBody Movie newMovie){
        movieService.updateMovie(id,newMovie);
        return new ResponseEntity<>("updated successfully",OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable String id){
        movieService.deleteMovie(id);
        return new ResponseEntity<>("deleted successfully",OK);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleIdNotFoundException(IdNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler(NameAlreadyExistsException.class)
    public ResponseEntity<String> handleNameAlreadyExistsException(NameAlreadyExistsException ex){
        return new ResponseEntity<>(ex.getMessage(), CONFLICT);
    }

}
