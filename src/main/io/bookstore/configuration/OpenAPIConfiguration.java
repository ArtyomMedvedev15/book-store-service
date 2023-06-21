package io.bookstore.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfiguration {
    @Value("${book-store-service.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("artyommedvedev15@gmail.com");
        contact.setName("Tim");
        contact.setUrl("https://github.com/Tim01Bro");

        License myLicence = new License().name("Free").url("Free");

        Info info = new Info()
                .title("Book-store-service")
                .version("1.0")
                .contact(contact)
                .description("A project designed to practice all the knowledge gained.").termsOfService("https://github.com/Tim01Bro")
                .license(myLicence);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
