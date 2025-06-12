package com.example.demo.repository.elasticsearch;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Product;

@Repository
public interface ProductSearchRepository extends ElasticsearchRepository<Product, String> {
    List<Product> findByTenSanPhamContainingIgnoreCase(String keyword);
    List<Product> findByDanhMuc(String category);
}