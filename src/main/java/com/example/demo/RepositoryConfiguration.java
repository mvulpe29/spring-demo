package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfiguration {

    @Bean
    PersonEventHandler personEventHandler() {
        return new PersonEventHandler();
    }
}