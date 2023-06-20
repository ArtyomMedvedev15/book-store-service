package io.bookstore.dao.api;

import io.bookstore.domain.Store;

import java.util.List;

public interface StoreDaoApi {
    boolean saveStore(Store storeSave);
    boolean updateStore(Store storeUpdate);
    Store getById(Long idStore);
    boolean deleteStore(Long idStore);
    List<Store> getAll();
}
