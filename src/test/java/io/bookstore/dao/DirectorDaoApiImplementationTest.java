package io.bookstore.dao;

import io.bookstore.domain.Director;
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
class DirectorDaoApiImplementationTest{

    @Autowired
    private DirectorDaoApi directorDaoApi;

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
    void SaveDirectorTest_ReturnTrue() {
        Director directorSave = Director.builder()
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        boolean save_result = directorDaoApi.saveDicrector(directorSave);

        assertTrue(save_result);

    }

    @Test
    void UpdateDirectorTest_ReturnTrue() {
        Director directorUpdate = directorDaoApi.getById(9999L);
        directorUpdate.setNameDirector("Updated");
        boolean update_result = directorDaoApi.updateDirector(directorUpdate);
        Director directorUpdated = directorDaoApi.getById(9999L);

        assertTrue(update_result);
        assertEquals("Updated", directorUpdated.getNameDirector());
    }

    @Test
    void GetByIdTest_ReturnTrue() {
        Director directorById = directorDaoApi.getById(9999L);
        assertNotNull(directorById);
    }

    @Test
    void DeleteDerectorTest_ReturnTrue() {
        boolean delete_result = directorDaoApi.DeleteDerector(7777L);
        assertTrue(delete_result);
    }

    @Test
    void GetAllTest_ReturnTrue() {
        var directorList = directorDaoApi.getAll();
        assertFalse(directorList.isEmpty());
    }
}