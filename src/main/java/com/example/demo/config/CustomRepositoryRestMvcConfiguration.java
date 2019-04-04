package com.example.demo.config;

import com.example.demo.common.QsFilterHandlerMethodArgumentResolver;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.rest.RepositoryRestProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.core.mapping.RepositoryDetectionStrategy;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;

import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableConfigurationProperties(RepositoryRestProperties.class)
public class CustomRepositoryRestMvcConfiguration extends RepositoryRestMvcConfiguration {
    @Autowired
    private RepositoryRestProperties properties;


    public CustomRepositoryRestMvcConfiguration(ApplicationContext context,
                                                ObjectFactory<ConversionService> conversionService) {
        super(context, conversionService);
    }

    @Bean
    public RepositoryRestConfiguration repositoryRestConfiguration() {
        RepositoryRestConfiguration config = super.repositoryRestConfiguration();
        this.properties.applyTo(config);

        config.setRepositoryDetectionStrategy(RepositoryDetectionStrategy.RepositoryDetectionStrategies.ANNOTATED);

        config.getCorsRegistry().addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE");
        return config;
    }


    @Override
    protected List<HandlerMethodArgumentResolver> defaultMethodArgumentResolvers() {
        List<HandlerMethodArgumentResolver> argumentResolvers =
                new ArrayList<>(super.defaultMethodArgumentResolvers());
        argumentResolvers.add(new SpecificationArgumentResolver());
        argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
        argumentResolvers.add(new QsFilterHandlerMethodArgumentResolver());
        return argumentResolvers;
    }



}
