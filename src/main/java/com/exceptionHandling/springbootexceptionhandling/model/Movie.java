package com.exceptionHandling.springbootexceptionhandling.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    private String id;

    private String name;
    private String director;
    private String year;
}
