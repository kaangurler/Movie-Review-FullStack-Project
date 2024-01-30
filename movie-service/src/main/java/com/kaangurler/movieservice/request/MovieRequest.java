package com.kaangurler.movieservice.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MovieRequest {
    private String title;
    private String genre;
}
