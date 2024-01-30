package com.kaangurler.reviewservice.service.impl;

import com.kaangurler.reviewservice.entity.Review;
import com.kaangurler.reviewservice.exception.ReviewNotFoundException;
import com.kaangurler.reviewservice.repository.ReviewRepository;
import com.kaangurler.reviewservice.request.ReviewRequest;
import com.kaangurler.reviewservice.response.ReviewResponse;
import com.kaangurler.reviewservice.service.ReviewService;
import com.kaangurler.reviewservice.utility.EntityToResponseMapper;
import com.kaangurler.reviewservice.utility.RequestToEntityMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final WebClient userServiceWebClient;
    private final WebClient movieServiceWebClient;

    public ReviewServiceImpl(WebClient.Builder webClientBuilder, ReviewRepository reviewRepository) {
        this.userServiceWebClient = webClientBuilder.baseUrl("http://localhost:8081/v1/user").build();
        this.movieServiceWebClient = webClientBuilder.baseUrl("http://localhost:8080/v1/movie").build();
        this.reviewRepository = reviewRepository;
    }
    @Override
    public void create(ReviewRequest reviewRequest) {
        reviewRepository.save(RequestToEntityMapper.reviewRequestToReview(reviewRequest));
    }

    @Override
    public List<ReviewResponse> getAll() {
        List<Review> reviews = reviewRepository.findAll();
        if (reviews.isEmpty()) {
            throw new ReviewNotFoundException("There is no review");
        }
        return reviews.stream()
                .map(review -> EntityToResponseMapper.reviewToReviewResponse(
                            review,
                            fetchUserName(review.getUserId()),
                            fetchMovieTitle(review.getMovieId()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse getById(Long id) {
        String userName = fetchUserName(id);
        String movieTitle = fetchMovieTitle(id);
        Review review = reviewRepository
                .findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review id " + id + " is not found"));
        return EntityToResponseMapper.reviewToReviewResponse(review, userName, movieTitle);
    }

    @Override
    public List<ReviewResponse> getByMovieId(Long id) {
        List<Review> reviews = reviewRepository.findAllByMovieId(id);
        if (reviews.isEmpty()) {
            //throw new ReviewNotFoundException("There is no review for this movie");
        }
        return reviewRepository.findAllByMovieId(id).stream()
                .map(review -> EntityToResponseMapper.reviewToReviewResponse(
                            review,
                            fetchUserName(review.getUserId()),
                            fetchMovieTitle(review.getMovieId()))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<ReviewResponse> getByUserId(Long id) {
        return reviewRepository.findAllByUserId(id).stream()
                .map(review -> EntityToResponseMapper.reviewToReviewResponse(
                        review,
                        fetchUserName(review.getUserId()),
                        fetchMovieTitle(review.getMovieId()))
                )
                .collect(Collectors.toList());
    }

    private String fetchUserName(Long id) {
        return userServiceWebClient
                .get()
                .uri("/" + id + "/get-name")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
    private String fetchMovieTitle(Long id) {
        return movieServiceWebClient
                .get()
                .uri("/" + id + "/get-name")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
