package io.bookstore.configuration;

import io.bookstore.dao.api.DirectorDaoApi;
import io.bookstore.dao.implementation.DirectorDaoApiImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DirectorDaoApi getDirectorDaoApiImpl(){
        return new DirectorDaoApiImplementation();
    }
}
