package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.core.CassandraTemplate;

import com.datastax.oss.driver.api.core.CqlSession;

@Configuration
public class CassandraConfig {

    @Autowired
    private CqlSession session;

    @Bean
    public CassandraTemplate cassandraTemplate() {
        return new CassandraTemplate(session);
    }
}
