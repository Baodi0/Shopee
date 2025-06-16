package com.example.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Review;
import com.example.demo.model.ReviewRequest;
import com.example.demo.repository.ReviewRepository;

import java.time.LocalDateTime;
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
        productId = productId.replaceAll("[{}]", "");

        String cacheKey = REVIEW_CACHE_PREFIX + productId;
        @SuppressWarnings("unchecked")
		List<Review> cachedReviews = (List<Review>) redisTemplate.opsForValue().get(cacheKey);

        //if (cachedReviews != null) {
        //    return cachedReviews;
        //}
        

        List<Review> reviews = reviewRepository.findReviewsByProductId(productId);
        redisTemplate.opsForValue().set(cacheKey, reviews, 1, TimeUnit.HOURS);

        return reviews;
    }


    public void saveReview(ReviewRequest reviewRequest) {
        System.out.println(reviewRequest.get_id());

        Review review = new Review();
        review.setId(reviewRequest.get_id()); 
        review.setBinhLuan(reviewRequest.getBinhLuan());
        review.setDiem(reviewRequest.getDiem());
        review.setSanPhamId(reviewRequest.getSanPhamID());
        review.setUserId(reviewRequest.getUserID());
        review.setThoiGian(LocalDateTime.now()); 
        review.setHinhAnh(reviewRequest.getHinhAnh());
        review.setVideo(reviewRequest.getVideo());

        Review savedReview = reviewRepository.save(review);
        redisTemplate.opsForValue().set(REVIEW_CACHE_PREFIX + savedReview.getId(), savedReview, 1, TimeUnit.HOURS);
    }


    public void deleteReview(ReviewRequest reviewRequest) {
    	String id = reviewRequest.get_id();
        reviewRepository.deleteById(id);
        redisTemplate.delete(REVIEW_CACHE_PREFIX + id);
    }
}