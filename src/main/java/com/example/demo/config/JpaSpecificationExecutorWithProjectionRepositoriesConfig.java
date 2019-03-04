package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import th.co.geniustree.springdata.jpa.repository.support.JpaSpecificationExecutorWithProjectionImpl;


@Configuration
@EnableJpaRepositories(basePackages = "com.example.demo.repositories.jpaSpecificationExecutorWithProjection", repositoryBaseClass = JpaSpecificationExecutorWithProjectionImpl.class)
public class JpaSpecificationExecutorWithProjectionRepositoriesConfig {
}
