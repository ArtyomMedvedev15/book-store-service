package io.bookstore.service.api;

import io.bookstore.domain.Store;

import java.util.List;

public interface StoreServiceApi {
    Store saveStore(Store storeSave);
    Store updateStore(Store storeUpdate);
    Store getById(Long idStore);
    boolean deleteStore(Long idStore);
    List<Store> getAll();
}
