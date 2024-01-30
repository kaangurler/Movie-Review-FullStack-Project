package com.kaangurler.movieservice.controller;

import com.kaangurler.movieservice.request.MovieRequest;
import com.kaangurler.movieservice.response.MovieResponse;
import com.kaangurler.movieservice.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/movie")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000")
public class MovieController {
    private final MovieService movieService;

    @PostMapping
    public void create(@RequestBody MovieRequest movieRequest) {
        movieService.create(movieRequest);
    }

    @GetMapping
    public ResponseEntity<List<MovieResponse>> getAll() {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{id}/get-name")
    public ResponseEntity<String> getNameById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getTitleById(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getById(id));
    }
}
