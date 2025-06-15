package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
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
        String product_id = id.replaceAll("[{}]", "");
        //if (cachedProduct != null) {
        //    return Optional.of(cachedProduct);
        //}
        
        // Nếu không có trong cache, lấy từ database
        List<Product> productList = productRepository.findAll();

        Optional<Product> optionalProduct = java.util.Optional.empty();
        for (Product prod : productList) {
            if (prod.getId().equals(product_id)) { 
            	optionalProduct = Optional.of(prod);
                redisTemplate.opsForValue().set(cacheKey, optionalProduct.get(), 1, TimeUnit.HOURS);
            }

        }

        return optionalProduct;
    }
    
    
    @SuppressWarnings("unchecked")
	public List<Product> searchProducts(String keyword, String category, 
                                      Double minPrice, Double maxPrice, 
                                      int page, int size, String sortBy) {
        // Tạo cache key cho tìm kiếm
        String searchCacheKey = SEARCH_CACHE_PREFIX + keyword + ":" + category + ":" + 
                               minPrice + ":" + maxPrice + ":" + page + ":" + size + ":" + sortBy;
        
    	System.out.println(searchCacheKey);

        // Kiểm tra cache
        //List<Product> cachedResult = (List<Product>) redisTemplate.opsForValue().get(searchCacheKey);
        //if (cachedResult != null) {
        //    return cachedResult;
        //}
        
        // Xử lý giá trị mặc định
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = 1000000000.0;
        
    	String tukhoa = "";
    	if (keyword != null)
    		tukhoa = "'tenSanPham': { $regex: " + keyword + ", $options: 'i' },";
    	String danhMuc = "";
        if(category != null)
        	danhMuc = "'danhMuc': '" + category + "' ,";
        // Thực hiện tìm kiếm
    	String query = "{" + tukhoa + danhMuc + " 'gia': { $gte: " + minPrice + ", $lte: " + maxPrice + " } }";
        List<Product> result = productRepository.searchProducts(query);      
        
        // Lưu vào cache
        redisTemplate.opsForValue().set(searchCacheKey, result, 5, TimeUnit.MINUTES);
        
        return (List<Product>) result;
    }
    
    public List<Product> getTopRatedProducts() {
        String cacheKey = "top_rated_products";
        @SuppressWarnings("unchecked")
		List<Product> cachedProducts = (List<Product>) redisTemplate.opsForValue().get(cacheKey);
        
        if (cachedProducts != null) {
            return cachedProducts;
        }
        
        List<Product> products = productRepository.findTop10ByOrderByDiemDanhGiaDesc();
        redisTemplate.opsForValue().set(cacheKey, products, 30, TimeUnit.MINUTES);
        
        return products;
    }
    
}