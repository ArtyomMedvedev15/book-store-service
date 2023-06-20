package io.bookstore.dao.implementation;

import io.bookstore.dao.api.StoreDaoApi;
import io.bookstore.domain.Store;
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
class StoreDaoApiImplementationTest{

    @Autowired
    private StoreDaoApi storeDaoApi;

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
    void SaveStoreTest_ReturnTrue() {
        Store storeSave = Store.builder()
                .nameStore("TestSave")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(9999L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        boolean save_result = storeDaoApi.saveStore(storeSave);

        assertTrue(save_result);
    }

    @Test
    void UpdateStoreTest_ReturnTrue() {
        Store storeUpdate = storeDaoApi.getById(7777L);
        storeUpdate.setNameStore("Updated");

        boolean update_result = storeDaoApi.updateStore(storeUpdate);

        Store storeUpdated = storeDaoApi.getById(7777L);
        assertTrue(update_result);
        assertEquals("Updated",storeUpdated.getNameStore());
    }

    @Test
    void GetByIdStoreTest_ReturnTrue() {
        Store storeGetById = storeDaoApi.getById(7777L);
        assertNotNull(storeGetById);
    }

    @Test
    void DeleteStoreByIdTest_ReturnTrue() {
        boolean delete_result = storeDaoApi.deleteStore(8888L);
        assertTrue(delete_result);
    }

    @Test
    void GetAllStoreTest_ReturnTrue() {
        var storeList = storeDaoApi.getAll();
        assertFalse(storeList.isEmpty());
    }
}