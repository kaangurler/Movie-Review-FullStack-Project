package com.kaangurler.reviewservice.utility;

import com.kaangurler.reviewservice.entity.Review;
import com.kaangurler.reviewservice.request.ReviewRequest;

public class RequestToEntityMapper {
    public static Review reviewRequestToReview(ReviewRequest reviewRequest) {
        return Review.builder()
                .userId(reviewRequest.getUserId())
                .movieId(reviewRequest.getMovieId())
                .comment(reviewRequest.getComment())
                .rating(reviewRequest.getRating())
                .build();
    }
}
