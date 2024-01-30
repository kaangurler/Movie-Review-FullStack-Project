package com.kaangurler.reviewservice.utility;

import com.kaangurler.reviewservice.entity.Review;
import com.kaangurler.reviewservice.response.ReviewResponse;

public class EntityToResponseMapper {
    public static ReviewResponse reviewToReviewResponse(Review review, String name, String title) {
        return ReviewResponse.builder()
                .id(review.getId())
                .userName(name)
                .movieTitle(title)
                .comment(review.getComment())
                .rating(review.getRating())
                .build();
    }
}
