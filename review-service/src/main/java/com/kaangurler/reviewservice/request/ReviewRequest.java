package com.kaangurler.reviewservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequest {
    private long userId;
    private long movieId;
    private String comment;
    private double rating;
}
