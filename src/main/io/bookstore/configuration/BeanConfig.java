package io.bookstore.configuration;

import io.bookstore.dao.DirectorDaoApi;
import io.bookstore.dao.DirectorDaoApiImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DirectorDaoApi getDirectorDaoApiImpl(){
        return new DirectorDaoApiImplementation();
    }
}
