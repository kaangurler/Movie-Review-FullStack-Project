package com.kaangurler.movieservice.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieResponse {
    private long id;
    private String title;
    private String genre;
    private double rating;
}
