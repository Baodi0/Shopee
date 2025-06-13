package com.example.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findBySanPhamId(String sanPhamId);
    List<Review> findByUserId(String userId);
    List<Review> findBySanPhamIdOrderByThoiGianDesc(String sanPhamId);
    
    @Query("{ 'sanPhamId': ?0 }")
    List<Review> findReviewsByProductId(String sanPhamId);
    
    // Tính điểm trung bình
    @Query(value = "{ 'sanPhamId': ?0 }", fields = "{ 'diem': 1 }")
    List<Review> findRatingsByProductId(String sanPhamId);
}