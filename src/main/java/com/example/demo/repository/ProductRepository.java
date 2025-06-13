package com.example.demo.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    
    // Tìm kiếm theo tên sản phẩm
    List<Product> findByTenSanPhamContainingIgnoreCase(String tenSanPham);
    
    // Tìm kiếm theo danh mục
    List<Product> findByDanhMucIgnoreCase(String danhMuc);
    
    // Tìm kiếm theo khoảng giá
    List<Product> findByGiaBetween(Double minPrice, Double maxPrice);
    
    // Tìm kiếm full-text
    @Query("{ $text: { $search: ?0 } }")
    List<Product> findByTextSearch(String searchText);
    
    // Tìm kiếm kết hợp với phân trang
    @Query("{ $and: [ " +
           "{ $or: [ " +
           "  { tenSanPham: { $regex: ?0, $options: 'i' } }, " +
           "  { moTa: { $regex: ?0, $options: 'i' } } " +
           "] }, " +
           "{ $or: [ " +
           "  { ?1: null }, " +
           "  { danhMuc: { $regex: ?1, $options: 'i' } } " +
           "] }, " +
           "{ gia: { $gte: ?2, $lte: ?3 } } " +
           "] }")
    Page<Product> searchProducts(String keyword, String category, Double minPrice, Double maxPrice, Pageable pageable);
    
    // Tìm sản phẩm theo shop
    List<Product> findByShopId(String shopId);
    
    // Top sản phẩm theo đánh giá
    List<Product> findTop10ByOrderByDiemDanhGiaDesc();
}