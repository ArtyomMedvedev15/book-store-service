package io.bookstore.dao.implementation;

import io.bookstore.dao.api.BookDaoApi;
import io.bookstore.domain.Book;
import junit.framework.TestCase;
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
import org.junit.jupiter.api.Test;

import java.sql.Date;


@RunWith(SpringRunner.class)
@SpringBootTest
@Testcontainers
@Sql(value = "classpath:/sqlScript/initDataBeforeScript.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "classpath:/sqlScript/afterDataScript.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class BookDaoApiImplementationTest extends TestCase {

    @Autowired
    private BookDaoApi bookDaoApi;

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
    void SaveBookTest_ReturnTrue() {
        Book bookSave = Book.builder()
                .nameBook("Test")
                .describeBook("Test")
                .idAuthorBook(7777L)
                .idStoreBook(7777L)
                .dateStartSaleBook(new Date(new java.util.Date().getTime()))
                .build();

        boolean save_result = bookDaoApi.saveBook(bookSave);

        assertTrue(save_result);
    }

    @Test
    void UpdateBookTest_ReturnTrue() {
        Book bookUpdate = bookDaoApi.getById(7777L);

        bookUpdate.setNameBook("Updated");

        boolean update_result = bookDaoApi.updateBook(bookUpdate);

        Book updatedBook = bookDaoApi.getById(7777L);

        assertTrue(update_result);
        assertEquals("Updated",updatedBook.getNameBook());
    }

    @Test
    void GetByIdTest_ReturnTrue() {
        Book bookbyid = bookDaoApi.getById(7777L);
        assertNotNull(bookbyid);
    }

    @Test
    void DeleteBookTest_ReturnTrue() {
        boolean delete_result = bookDaoApi.deleteBook(8888L);
        assertTrue(delete_result);
    }


    @Test
    void GetAllTest_ReturnTrue() {
        var bookList = bookDaoApi.getAll();
        assertFalse(bookList.isEmpty());
    }
}