package com.kaangurler.movieservice.service.impl;

import com.kaangurler.movieservice.entity.Movie;
import com.kaangurler.movieservice.exception.MovieNotFoundException;
import com.kaangurler.movieservice.repository.MovieRepository;
import com.kaangurler.movieservice.request.MovieRequest;
import com.kaangurler.movieservice.response.MovieResponse;
import com.kaangurler.movieservice.response.ReviewResponse;
import com.kaangurler.movieservice.service.MovieService;
import com.kaangurler.movieservice.utility.EntityToResponseMapper;
import com.kaangurler.movieservice.utility.RequestToEntityMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import javax.management.monitor.MonitorSettingException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceImpl implements MovieService {
    private final MovieRepository movieRepository;
    private final WebClient reviewServiceWebClient;

    public MovieServiceImpl(WebClient.Builder webClientBuilder, MovieRepository movieRepository) {
        this.reviewServiceWebClient = webClientBuilder.baseUrl("http://localhost:8082/v1/review").build();
        this.movieRepository = movieRepository;
    }
    @Override
    public void create(MovieRequest movieRequest) {
        movieRepository.save(RequestToEntityMapper.movieRequestToMovie(movieRequest));
    }

    @Override
    public List<MovieResponse> getAll() {
        List<Movie> movies = movieRepository.findAll();
        if (movies.isEmpty()) {
            throw new MovieNotFoundException("There are currently no movies");
        }
        return movies.stream()
                .map(movie -> EntityToResponseMapper.movieToMovieResponse(movie, calculateRating(movie.getId())))
                .collect(Collectors.toList());
    }

    @Override
    public String getTitleById(Long id) {
        return movieRepository.findById(id).get().getTitle();
    }

    @Override
    public MovieResponse getById(Long id) {
        return EntityToResponseMapper
                .movieToMovieResponse(
                        movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("Movie id: " + id + " is not found")), calculateRating(id)
                );
    }

    private List<ReviewResponse> fetchReviewsByMovie(Long id) {
        return reviewServiceWebClient
                .get()
                .uri("/get-by-movie/" + id)
                .retrieve()
                .bodyToFlux(ReviewResponse.class)
                .collectList()
                .block();
    }

    private double calculateRating(Long id) {
        List<ReviewResponse> reviewList = fetchReviewsByMovie(id);
        double sumOfRatings = 0;
        for (int i = 0; i < reviewList.size(); i++) {
            sumOfRatings += reviewList.get(i).getRating();
        }
        return sumOfRatings / reviewList.size();
    }
}
