package com.kaangurler.reviewservice.service;

import com.kaangurler.reviewservice.request.ReviewRequest;
import com.kaangurler.reviewservice.response.ReviewResponse;

import java.util.List;

public interface ReviewService {
    void create(ReviewRequest reviewRequest);
    List<ReviewResponse> getAll();
    ReviewResponse getById(Long id);
    List<ReviewResponse> getByMovieId(Long id);
    List<ReviewResponse> getByUserId(Long id);
}
