package com.example.demo.controller;

import com.example.demo.model.Review;
import com.example.demo.model.ReviewRequest;
import com.example.demo.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{productId}")
    public List<Review> getReviewsByProduct(@PathVariable String productId) {
        return reviewService.getReviewsByProduct(productId);
    }

    @PostMapping
    public void saveReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.saveReview(reviewRequest);
    }

    @DeleteMapping
    public void deleteReview(@RequestBody ReviewRequest reviewRequest) {
        reviewService.deleteReview(reviewRequest);
    }
}