package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Product;
import com.example.demo.repository.elasticsearch.ProductSearchRepository;
import com.example.demo.repository.mongodb.ProductRepository;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductSearchRepository productSearchRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductSearchRepository productSearchRepository) {
        this.productRepository = productRepository;
        this.productSearchRepository = productSearchRepository;
    }

    public List<Product> searchProducts(String keyword, String category) {
        if (keyword != null && !keyword.isEmpty()) {
            return productSearchRepository.findByTenSanPhamContainingIgnoreCase(keyword);
        } else if (category != null && !category.isEmpty()) {
            return productSearchRepository.findByDanhMuc(category);
        }
        return productRepository.findAll(); 
    }

    public Product getProductDetails(String productId) {
        return productRepository.findById(productId).orElse(null);
    }
}