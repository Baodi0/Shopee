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
        String cacheKey = CACHE_KEY_PREFIX + id;
        String product_id = id.replaceAll("[{}]", "");

        Product cachedProduct = (Product) redisTemplate.opsForValue().get(cacheKey);
        if (cachedProduct != null) {
            return Optional.of(cachedProduct);
        }

        Optional<Product> optionalProduct = productRepository.findById(product_id);
        
        optionalProduct.ifPresent(product -> 
            redisTemplate.opsForValue().set(cacheKey, product, 1, TimeUnit.HOURS)
        );

        return optionalProduct;
    }

    
    @SuppressWarnings("unchecked")
	public List<Product> searchProducts(String keyword, String category, 
                                      Double minPrice, Double maxPrice, 
                                      int page, int size, String sortBy) {

    	String searchCacheKey = SEARCH_CACHE_PREFIX + keyword + ":" + category + ":" + 
                               minPrice + ":" + maxPrice + ":" + page + ":" + size + ":" + sortBy;
        
    	System.out.println(searchCacheKey);

        // Kiểm tra cache
        List<Product> cachedResult = (List<Product>) redisTemplate.opsForValue().get(searchCacheKey);
        if (cachedResult != null) {
            return cachedResult;
        }
        
        if (minPrice == null) minPrice = 0.0;
        if (maxPrice == null) maxPrice = 1000000000.0;
        
    	String tukhoa = "";
    	if (keyword != null)
    		tukhoa = "'tenSanPham': { $regex: " + keyword + ", $options: 'i' },";
    	String danhMuc = "";
        if(category != null)
        	danhMuc = "'danhMuc': '" + category + "' ,";

        String query = "{" + tukhoa + danhMuc + " 'gia': { $gte: " + minPrice + ", $lte: " + maxPrice + " } }";
        List<Product> result = productRepository.searchProducts(query);      
        
        redisTemplate.opsForValue().set(searchCacheKey, result, 5, TimeUnit.MINUTES);
        
        return (List<Product>) result;
    }
    
    
}