package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Review;
import com.example.demo.repository.mongodb.ReviewRepository;

import java.time.Instant;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    // private final Neo4jIntegrationService neo4jService; // Nếu dùng Neo4j

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        // this.neo4jService = neo4jService;
    }

    public Review submitReview(Review review) {
        review.setThoiGian(Instant.now());
        Review savedReview = reviewRepository.save(review);
         return savedReview;
    }

    public List<Review> getReviewsByProductId(String productId) {
        return reviewRepository.findBySanPhamID(productId);
    }
}