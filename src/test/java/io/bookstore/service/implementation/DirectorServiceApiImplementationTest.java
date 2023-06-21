package io.bookstore.service.implementation;

import io.bookstore.dao.api.DirectorDaoApi;
import io.bookstore.domain.Director;
import io.bookstore.service.api.DirectorServiceApi;
import junit.framework.TestCase;
import org.junit.runner.RunWith;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class DirectorServiceApiImplementationTest extends TestCase {

    @MockBean
    private DirectorDaoApi directorDaoApi;

    @Autowired
    private DirectorServiceApi directorServiceApi;

    @Test
    void SaveDirectorTest_ReturnTrue() {
        Director directorSave = Director.builder()
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.saveDirector(directorSave)).thenReturn(true);

        Director director_service_result = directorServiceApi.saveDirector(directorSave);

        assertEquals("Timmi", director_service_result.getNameDirector());
        Mockito.verify(directorDaoApi,Mockito.times(1)).saveDirector(directorSave);
    }

    @Test
    void SaveDirectorTest_ReturnNull() {
        Director directorSave = Director.builder()
                .nameDirector("Timmi")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.saveDirector(directorSave)).thenReturn(false);

        Director director_service_result = directorServiceApi.saveDirector(directorSave);

        assertNull(director_service_result);
        Mockito.verify(directorDaoApi,Mockito.times(1)).saveDirector(directorSave);
    }

    @Test
    void UpdateDirectorTest_WithId8989_ReturnTrue() {
        Director directorUpdate = Director.builder()
                .id(8989L)
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.getById(8989L)).thenReturn(directorUpdate);

        Director updatedDirector = directorServiceApi.getById(8989L);
        updatedDirector.setNameDirector("Updated");

        Mockito.when(directorDaoApi.updateDirector(updatedDirector)).thenReturn(true);

        Director director_service_result = directorServiceApi.updateDirector(updatedDirector);

        assertEquals("Updated", director_service_result.getNameDirector());
        Mockito.verify(directorDaoApi,Mockito.times(1)).updateDirector(directorUpdate);
        Mockito.verify(directorDaoApi,Mockito.times(1)).getById(8989L);

    }

    @Test
    void GetByIdTest_WithId8989_ReturnTrue() {
        Director directorUpdate = Director.builder()
                .id(8989L)
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.getById(8989L)).thenReturn(directorUpdate);

        Director directorById = directorServiceApi.getById(8989L);

        assertNotNull(directorById);
        Mockito.verify(directorDaoApi,Mockito.times(1)).getById(8989L);
    }

    @Test
    void GetByIdTest_WithIdNonExistsId_ReturnNull() {
        Director directorById = directorServiceApi.getById(7635L);
        assertNull(directorById);
        Mockito.verify(directorDaoApi,Mockito.times(1)).getById(7635L);
    }

    @Test
    void DeleteDirectorTest_Withid8989_ReturnTrue() {
        Director directorUpdate = Director.builder()
                .id(8989L)
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.getById(8989L)).thenReturn(directorUpdate);

        Mockito.when(directorDaoApi.deleteDirector(8989L)).thenReturn(true);

        boolean director_service_result = directorServiceApi.deleteDirector(8989L);

        assertTrue(director_service_result);
        Mockito.verify(directorDaoApi,Mockito.times(1)).deleteDirector(8989L);
        Mockito.verify(directorDaoApi,Mockito.times(1)).getById(8989L);

    }

    @Test
    void DeleteDirectorTest_WithNonExistsDirector_ReturnFalse() {
        Mockito.when(directorDaoApi.getById(8989L)).thenReturn(null);

        Mockito.when(directorDaoApi.deleteDirector(8989L)).thenReturn(false);

        boolean director_service_result = directorServiceApi.deleteDirector(8989L);

        assertFalse(director_service_result);
        Mockito.verify(directorDaoApi,Mockito.times(0)).deleteDirector(8989L);
        Mockito.verify(directorDaoApi,Mockito.times(1)).getById(8989L);

    }

    @Test
    void GetAllDirectorTest_ReturnTrue() {
        Director director = Director.builder()
                .id(8989L)
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.getAll()).thenReturn(List.of(director));

        var directorList = directorServiceApi.getAll();

        assertFalse(directorList.isEmpty());
        Mockito.verify(directorDaoApi,Mockito.times(1)).getAll();

    }
}