package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private static final String CACHE_KEY_PREFIX = "product:";
    private static final String SEARCH_CACHE_PREFIX = "search:";
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
    
    public Optional<Product> getProductById(String id) {
        // Kiểm tra cache trước
        String cacheKey = CACHE_KEY_PREFIX + id;
        Product cachedProduct = (Product) redisTemplate.opsForValue().get(cacheKey);
        
        if (cachedProduct != null) {
            return Optional.of(cachedProduct);
        }
        
        // Nếu không có trong cache, lấy từ database
        Optional<Product> product = productRepository.findById(id);
        
        // Lưu vào cache nếu tìm thấy
        if (product.isPresent()) {
            redisTemplate.opsForValue().set(cacheKey, product.get(), 1, TimeUnit.HOURS);
        }
        
        return product;
    }
    
    public Product saveProduct(Product product) {
        Product saved = productRepository.save(product);
        
        // Cập nhật cache
        String cacheKey = CACHE_KEY_PREFIX + saved.getId();
        redisTemplate.opsForValue().set(cacheKey, saved, 1, TimeUnit.HOURS);
        
        return saved;
    }
    
    public void deleteProduct(String id) {
        productRepository.deleteById(id);
        
        // Xóa khỏi cache
        String cacheKey = CACHE_KEY_PREFIX + id;
        redisTemplate.delete(cacheKey);
    }
    
    public Page<Product> searchProducts(String keyword, String category, 
                                      Double minPrice, Double maxPrice, 
                                      int page, int size, String sortBy) {
        // Tạo cache key cho tìm kiếm
        String searchCacheKey = SEARCH_CACHE_PREFIX + keyword + ":" + category + ":" + 
                               minPrice + ":" + maxPrice + ":" + page + ":" + size + ":" + sortBy;
        
        // Kiểm tra cache
        Page<Product> cachedResult = (Page<Product>) redisTemplate.opsForValue().get(searchCacheKey);
        if (cachedResult != null) {
            return cachedResult;
        }
        
        // Tạo Pageable với sắp xếp
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy != null ? sortBy : "tenSanPham");
        Pageable pageable = PageRequest.of(page, size, sort);
        
        // Xử lý giá trị mặc định
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = Double.MAX_VALUE;
        
        // Thực hiện tìm kiếm
        Page<Product> result = productRepository.searchProducts(
            keyword != null ? keyword : "", 
            category, 
            minPrice, 
            maxPrice, 
            pageable
        );
        
        // Lưu vào cache
        redisTemplate.opsForValue().set(searchCacheKey, result, 5, TimeUnit.MINUTES);
        
        return result;
    }
    
    public List<Product> getTopRatedProducts() {
        String cacheKey = "top_rated_products";
        List<Product> cachedProducts = (List<Product>) redisTemplate.opsForValue().get(cacheKey);
        
        if (cachedProducts != null) {
            return cachedProducts;
        }
        
        List<Product> products = productRepository.findTop10ByOrderByDiemDanhGiaDesc();
        redisTemplate.opsForValue().set(cacheKey, products, 30, TimeUnit.MINUTES);
        
        return products;
    }
    
    public List<Product> getProductsByCategory(String category) {
        return productRepository.findByDanhMucIgnoreCase(category);
    }
    
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByGiaBetween(minPrice, maxPrice);
    }
}