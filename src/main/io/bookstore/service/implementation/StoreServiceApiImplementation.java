package io.bookstore.service.implementation;

import io.bookstore.dao.api.StoreDaoApi;
import io.bookstore.domain.Director;
import io.bookstore.domain.Store;
import io.bookstore.service.api.DirectorServiceApi;
import io.bookstore.service.api.StoreServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class StoreServiceApiImplementation implements StoreServiceApi {

    private final StoreDaoApi storeDaoApi;
    private final DirectorServiceApi directorServiceApi;

    @Override
    public Store saveStore(Store storeSave) {
        Director checkExistsDirector = directorServiceApi.getById(storeSave.getIdDirectorStore());
        if (checkExistsDirector != null) {
            boolean save_store_result = storeDaoApi.saveStore(storeSave);
            if (save_store_result) {
                log.info("Save new store in {}", new Date());
                return storeSave;
            } else {
                log.error("Error in save new store, check logs in {}", new Date());
                return null;
            }
        } else {
            log.error("Director id doesn't exists in {}", new Date());
            return null;
        }
    }

    @Override
    public Store updateStore(Store storeUpdate) {
        Director checkExistsDirector = directorServiceApi.getById(storeUpdate.getIdDirectorStore());
        if (checkExistsDirector != null) {
            boolean update_store_result = storeDaoApi.updateStore(storeUpdate);
            if (update_store_result) {
                log.info("Update store with id {} in {}", storeUpdate.getIdStore(), new Date());
                return storeUpdate;
            } else {
                log.error("Error in update store, check logs in {}", new Date());
                return null;
            }
        } else {
            log.error("Director id doesn't exists in {}", new Date());
            return null;
        }
    }

    @Override
    public Store getById(Long idStore) {
        try {
            log.info("Get store by id {} in {}", idStore, new Date());
            return storeDaoApi.getById(idStore);
        } catch (DataAccessException exception) {
            log.error("Cannot find store with id {} in {}", idStore, new Date());
            return null;
        }
    }

    @Override
    public boolean deleteStore(Long idStore) {
        Store storeCheckExists = storeDaoApi.getById(idStore);
        if (storeCheckExists != null) {
            boolean delete_store_result = storeDaoApi.deleteStore(idStore);
            if (delete_store_result) {
                log.info("Delete store with id {} in {}", idStore, new Date());
                return true;
            } else {
                log.error("Error in delete store, check logs in {}", new Date());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Store> getAll() {
        log.info("Get all store in {}", new Date());
        return storeDaoApi.getAll();
    }
}
