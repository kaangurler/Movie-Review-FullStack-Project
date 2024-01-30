package com.kaangurler.reviewservice.controller;

import com.kaangurler.reviewservice.request.ReviewRequest;
import com.kaangurler.reviewservice.response.ReviewResponse;
import com.kaangurler.reviewservice.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/review")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class ReviewController {
    private final ReviewService reviewService;
    @PostMapping
    public void create(@RequestBody ReviewRequest reviewRequest) {
        reviewService.create(reviewRequest);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponse>> getAll() {
        return ResponseEntity.ok(reviewService.getAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReviewResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getById(id));
    }

    @GetMapping("/get-by-movie/{id}")
    public ResponseEntity<List<ReviewResponse>> getByMovieId(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getByMovieId(id));
    }

    @GetMapping("/get-by-user/{id}")
    public ResponseEntity<List<ReviewResponse>> getByUserId(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getByUserId(id));
    }
}
