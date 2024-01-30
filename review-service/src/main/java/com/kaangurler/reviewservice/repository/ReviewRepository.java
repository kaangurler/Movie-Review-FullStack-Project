package com.kaangurler.reviewservice.repository;

import com.kaangurler.reviewservice.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByMovieId(Long id);
    List<Review> findAllByUserId(Long id);
}
