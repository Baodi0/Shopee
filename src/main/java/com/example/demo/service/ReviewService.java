package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Review;
import com.example.demo.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    private static final String REVIEW_CACHE_PREFIX = "review:";

    public List<Review> getReviewsByProduct(String productId) {
        String cacheKey = REVIEW_CACHE_PREFIX + productId;
        List<Review> cachedReviews = (List<Review>) redisTemplate.opsForValue().get(cacheKey);

        if (cachedReviews != null) {
            return cachedReviews;
        }

        List<Review> reviews = reviewRepository.findBySanPhamId(productId);
        redisTemplate.opsForValue().set(cacheKey, reviews, 1, TimeUnit.HOURS);

        return reviews;
    }

    public Optional<Review> getReviewById(String id) {
        String cacheKey = REVIEW_CACHE_PREFIX + id;
        Review cachedReview = (Review) redisTemplate.opsForValue().get(cacheKey);

        if (cachedReview != null) {
            return Optional.of(cachedReview);
        }

        Optional<Review> review = reviewRepository.findById(id);
        review.ifPresent(r -> redisTemplate.opsForValue().set(cacheKey, r, 1, TimeUnit.HOURS));

        return review;
    }

    public Review saveReview(Review review) {
        Review savedReview = reviewRepository.save(review);
        redisTemplate.opsForValue().set(REVIEW_CACHE_PREFIX + review.getId(), savedReview, 1, TimeUnit.HOURS);
        return savedReview;
    }

    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
        redisTemplate.delete(REVIEW_CACHE_PREFIX + id);
    }
}