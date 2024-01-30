package com.kaangurler.movieservice.response;

import lombok.Data;

@Data
public class ReviewResponse {
    private String userName;
    private String movieTitle;
    private String comment;
    private double rating;
}
