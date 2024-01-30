package com.kaangurler.movieservice.utility;

import com.kaangurler.movieservice.entity.Movie;
import com.kaangurler.movieservice.request.MovieRequest;

public class RequestToEntityMapper {
    public static Movie movieRequestToMovie(MovieRequest movieRequest) {
        return Movie.builder()
                .title(movieRequest.getTitle())
                .genre(movieRequest.getGenre())
                .build();
    }
}
