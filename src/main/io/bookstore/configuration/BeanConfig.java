package io.bookstore.configuration;

import io.bookstore.dao.api.DirectorDaoApi;
import io.bookstore.dao.api.StoreDaoApi;
import io.bookstore.dao.implementation.DirectorDaoApiImplementation;
import io.bookstore.dao.implementation.StoreDaoApiImplementation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public DirectorDaoApi getDirectorDaoApiImpl(){
        return new DirectorDaoApiImplementation();
    }

    @Bean
    public StoreDaoApi getStoreDaoApiImpl(){
        return new StoreDaoApiImplementation();
    }
}
