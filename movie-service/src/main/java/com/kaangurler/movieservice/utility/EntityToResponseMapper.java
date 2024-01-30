package com.kaangurler.movieservice.utility;

import com.kaangurler.movieservice.entity.Movie;
import com.kaangurler.movieservice.response.MovieResponse;

public class EntityToResponseMapper {
    public static MovieResponse movieToMovieResponse(Movie movie, double rating) {
        return MovieResponse.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .genre(movie.getGenre())
                .rating(rating)
                .build();
    }
}
