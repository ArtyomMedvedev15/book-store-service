package io.bookstore.service.implementation;

import io.bookstore.dao.api.DirectorDaoApi;
import io.bookstore.dao.api.StoreDaoApi;
import io.bookstore.domain.Director;
import io.bookstore.domain.Store;
import io.bookstore.service.api.StoreServiceApi;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class StoreServiceApiImplementationTest extends TestCase {

    @MockBean
    private DirectorDaoApi directorDaoApi;

    @MockBean
    private StoreDaoApi storeDaoApi;

    @Autowired
    private StoreServiceApi storeServiceApi;


    @Test
    void SaveStoreTest_ReturnTrue() {
        Director director = Director.builder()
                .id(8989L)
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.getById(8989L)).thenReturn(director);

        Store storeSave = Store.builder()
                .nameStore("TestSave")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(8989L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(storeDaoApi.saveStore(storeSave)).thenReturn(true);

        Store store_save_result = storeServiceApi.saveStore(storeSave);

        assertEquals("TestSave", store_save_result.getNameStore());
        Mockito.verify(directorDaoApi, Mockito.times(1)).getById(8989L);
        Mockito.verify(storeDaoApi, Mockito.times(1)).saveStore(storeSave);

    }


    @Test
    void SaveStoreTest_WithNullDirector_ReturnTrue() {
        Store storeSave = Store.builder()
                .nameStore("TestSave")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(8989L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(storeDaoApi.saveStore(storeSave)).thenReturn(false);

        Store store_save_result = storeServiceApi.saveStore(storeSave);

        Assert.assertNull(store_save_result);
        Mockito.verify(directorDaoApi, Mockito.times(1)).getById(8989L);
        Mockito.verify(storeDaoApi, Mockito.times(0)).saveStore(storeSave);

    }

    @Test
    void UpdateStoreTest_ReturnTrue() {
        Director director = Director.builder()
                .id(8989L)
                .nameDirector("Timmi")
                .sonameDirector("Jonson")
                .ageDirector(33)
                .dateGetPositionDirector(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(directorDaoApi.getById(8989L)).thenReturn(director);

        Store storeUpdate = Store.builder()
                .idStore(7781L)
                .nameStore("TestUpdate")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(8989L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(storeDaoApi.getById(7781L)).thenReturn(storeUpdate);

        Store updatedStore = storeServiceApi.getById(7781L);
        updatedStore.setNameStore("Updated");
        Mockito.when(storeDaoApi.updateStore(updatedStore)).thenReturn(true);

        Store store_update_result = storeServiceApi.updateStore(updatedStore);

        assertEquals("Updated", store_update_result.getNameStore());
        Mockito.verify(directorDaoApi, Mockito.times(1)).getById(8989L);
        Mockito.verify(storeDaoApi, Mockito.times(1)).updateStore(updatedStore);
        Mockito.verify(storeDaoApi, Mockito.times(1)).getById(7781L);
    }

    @Test
    void UpdateStoreTest_WithNullDirector_ReturnTrue() {
        Store storeUpdate = Store.builder()
                .idStore(71822L)
                .nameStore("TestSave")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(8989L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(storeDaoApi.updateStore(storeUpdate)).thenReturn(false);

        Store store_update_result = storeServiceApi.updateStore(storeUpdate);

        Assert.assertNull(store_update_result);
        Mockito.verify(directorDaoApi, Mockito.times(1)).getById(8989L);
        Mockito.verify(storeDaoApi, Mockito.times(0)).updateStore(storeUpdate);

    }


    @Test
    void GetStoreByIdTest_ReturnTrue() {
        Store storeById = Store.builder()
                .idStore(7781L)
                .nameStore("TestUpdate")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(8989L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(storeDaoApi.getById(7781L)).thenReturn(storeById);

        Store store_by_id = storeServiceApi.getById(7781L);
        Assert.assertNotNull(store_by_id);
        Mockito.verify(storeDaoApi, Mockito.times(1)).getById(7781L);
    }

    @Test
    void GetStoreByIdTest_WithNonExistsId_ReturnTrue() {
        Store store_by_id = storeServiceApi.getById(81929L);
        Assert.assertNull(store_by_id);
        Mockito.verify(storeDaoApi, Mockito.times(1)).getById(81929L);
    }

    @Test
    void DeleteStoreTest_ReturnTrue() {
        Store storeDelete = Store.builder()
                .idStore(7781L)
                .nameStore("TestUpdate")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(8989L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(storeDaoApi.getById(7781L)).thenReturn(storeDelete);
        Mockito.when(storeDaoApi.deleteStore(7781L)).thenReturn(true);

        boolean store_delete_result = storeServiceApi.deleteStore(7781L);

        assertTrue(store_delete_result);
        Mockito.verify(storeDaoApi,Mockito.times(1)).deleteStore(7781L);
        Mockito.verify(storeDaoApi, Mockito.times(1)).getById(7781L);
    }

    @Test
    void DeleteStoreTest_WithNonExistsStore_ReturnFalse() {
        Mockito.when(storeDaoApi.getById(81292L)).thenReturn(null);
        Mockito.when(storeDaoApi.deleteStore(81292L)).thenReturn(false);

        boolean store_delete_result = storeServiceApi.deleteStore(81292L);

        assertFalse(store_delete_result);
        Mockito.verify(storeDaoApi,Mockito.times(0)).deleteStore(81292L);
        Mockito.verify(storeDaoApi, Mockito.times(1)).getById(81292L);
    }

    @Test
    void GetAllStoreTest_ReturnTrue() {
        Store store = Store.builder()
                .idStore(7781L)
                .nameStore("TestUpdate")
                .cityStore("Test")
                .streetStore("Test")
                .idDirectorStore(8989L)
                .dateOpenStore(new Date(new java.util.Date().getTime()))
                .build();

        Mockito.when(storeDaoApi.getAll()).thenReturn(List.of(store));

        var storeList = storeServiceApi.getAll();
        assertFalse(storeList.isEmpty());
    }
}