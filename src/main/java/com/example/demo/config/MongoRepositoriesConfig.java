package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@EnableMongoRepositories(
        basePackages = "com.example.demo.repositories.mongo")
@EnableMongoAuditing
public class MongoRepositoriesConfig {
}
