package com.kaangurler.movieservice.service;

import com.kaangurler.movieservice.request.MovieRequest;
import com.kaangurler.movieservice.response.MovieResponse;

import java.util.List;

public interface MovieService {
    void create(MovieRequest movieRequest);
    List<MovieResponse> getAll();
    String getTitleById(Long id);
    MovieResponse getById(Long id);
}
