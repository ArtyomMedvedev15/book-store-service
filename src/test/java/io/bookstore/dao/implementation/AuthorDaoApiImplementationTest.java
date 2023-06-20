package io.bookstore.dao.implementation;

import io.bookstore.dao.api.AuthorDaoApi;
import io.bookstore.domain.Author;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@Sql(value = "classpath:/sqlScript/initDataBeforeScript.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:/sqlScript/afterDataScript.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class AuthorDaoApiImplementationTest {

    @Autowired
    private AuthorDaoApi authorDaoApi;

    @Container
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:9.6.18-alpine")
            .withDatabaseName("prop")
            .withUsername("postgres")
            .withPassword("postgres")
            .withExposedPorts(5432)
            .withInitScript("sqlScript/initDBBeforeScript.sql");


    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url",
                () -> String.format("jdbc:postgresql://localhost:%d/prop", postgreSQLContainer.getFirstMappedPort()));
        registry.add("spring.datasource.username", () -> "postgres");
        registry.add("spring.datasource.password", () -> "postgres");
    }

    @Test
    void SaveAuthorTest_ReturnTrue() {
        Author authorSave = Author.builder()
                .nameAuthor("Test")
                .sonameAuthor("Test")
                .fanameAuthor("Test")
                .dateAddToAuthor(new Date(new java.util.Date().getTime()))
                .build();

        boolean save_result = authorDaoApi.saveAuthor(authorSave);

        assertTrue(save_result);
    }

    @Test
    void UpdateAuthorTest_ReturnTrue() {
        Author authorUpdate = authorDaoApi.getById(7777L);
        authorUpdate.setNameAuthor("Updated");

        boolean update_result = authorDaoApi.updateAuthor(authorUpdate);

        Author authorById = authorDaoApi.getById(7777L);

        assertTrue(update_result);
        assertEquals("Updated", authorById.getNameAuthor());
    }

    @Test
    void GetByIdTest_ReturnTrue() {
        Author authorById = authorDaoApi.getById(7777L);
        assertNotNull(authorById);
    }

    @Test
    void DeleteAuthorTest_ReturnTrue() {
        boolean delete_result = authorDaoApi.deleteAuthor(8888L);
        assertTrue(delete_result);
    }

    @Test
    void GetAllAuthorTest_ReturnTrue() {
        var authorList = authorDaoApi.getAll();
        assertFalse(authorList.isEmpty());
    }
}