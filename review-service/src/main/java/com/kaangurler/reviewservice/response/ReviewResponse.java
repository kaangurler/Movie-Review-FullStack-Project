package com.kaangurler.reviewservice.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponse {
    private long id;
    private String userName;
    private String movieTitle;
    private String comment;
    private double rating;
}
