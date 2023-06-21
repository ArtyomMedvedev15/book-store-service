package io.bookstore.configuration;

import io.bookstore.dao.api.AuthorDaoApi;
import io.bookstore.dao.api.BookDaoApi;
import io.bookstore.dao.api.DirectorDaoApi;
import io.bookstore.dao.api.StoreDaoApi;
import io.bookstore.dao.implementation.AuthorDaoApiImplementation;
import io.bookstore.dao.implementation.BookDaoApiImplementation;
import io.bookstore.dao.implementation.DirectorDaoApiImplementation;
import io.bookstore.dao.implementation.StoreDaoApiImplementation;
import io.bookstore.service.api.AuthorServiceApi;
import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.service.api.StoreServiceApi;
import io.bookstore.service.implementation.AuthorServiceApiImplementation;
import io.bookstore.service.implementation.DirectorServiceApiImplementation;
import io.bookstore.service.implementation.StoreServiceApiImplementation;
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

    @Bean
    public AuthorDaoApi getAuthorDaoApiImpl(){
        return new AuthorDaoApiImplementation();
    }

    @Bean
    public BookDaoApi getBookDaoApiImpl(){
        return new BookDaoApiImplementation();
    }

    @Bean
    public DirectorServiceApi getDirectorServiceApiImpl(){
        return new DirectorServiceApiImplementation(getDirectorDaoApiImpl());
    }

    @Bean
    public StoreServiceApi getStoreServiceApiImpl(){
        return new StoreServiceApiImplementation(getStoreDaoApiImpl(),getDirectorServiceApiImpl());
    }

    @Bean
    public AuthorServiceApi getAuthorServiceApiImpl(){
        return new AuthorServiceApiImplementation(getAuthorDaoApiImpl(),getBookDaoApiImpl());
    }
}
