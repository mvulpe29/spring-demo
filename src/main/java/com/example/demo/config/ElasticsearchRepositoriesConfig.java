package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@Configuration
@EnableElasticsearchRepositories(
        basePackages = "com.example.demo.repositories.elasticsearch")
@EnableJpaAuditing
public class ElasticsearchRepositoriesConfig {
}
