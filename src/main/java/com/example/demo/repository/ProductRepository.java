package com.example.demo.repository;

import com.example.demo.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    // Tìm kiếm sản phẩm theo từ khóa (Tên sản phẩm), danh mục, và khoảng giá
    @Query("?0")
    List<Product> searchProducts(String query);
    
    // Tìm sản phẩm theo danh mục
    List<Product> findByDanhMucIgnoreCase(String category);

    // Tìm sản phẩm theo khoảng giá
    List<Product> findByGiaBetween(Double minPrice, Double maxPrice);

    // Lấy danh sách top sản phẩm có điểm đánh giá cao nhất
    List<Product> findTop10ByOrderByDiemDanhGiaDesc();
}