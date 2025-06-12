package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.demo.repository.mongodb")
@EnableElasticsearchRepositories(basePackages = "com.example.demo.repository.elasticsearch")
@EnableCassandraRepositories(basePackages = "com.example.demo.repository.cassandra")
public class ShopeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopeeApplication.class, args);
    }
}