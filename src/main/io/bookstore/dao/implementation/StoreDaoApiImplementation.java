package io.bookstore.dao.implementation;

import io.bookstore.dao.api.StoreDaoApi;
import io.bookstore.domain.Store;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@Slf4j
public class StoreDaoApiImplementation implements StoreDaoApi {

    @Autowired
    private JdbcTemplate databaseConnection;

    private static final String INSERT_QUERY = "insert into store(namestore, citystore, streetstore," +
            " iddirectorstore, dateopenstore) values(?,?,?,?,?)";

    private static final String UPDATE_QUERY = "update store set namestore=?, citystore=?, streetstore=?," +
            " iddirectorstore=?, dateopenstore=? where idstore=?";

    private static final String SELECT_ONE_QUERY = "select * from store where idstore=?";
    private static final String DELETE_ONE_QUERY = "delete from store where idstore=?";
    private static final String SELECT_ALL_QUERY = "select * from store";

    @Override
    public boolean saveStore(Store storeSave) {
        int save_result = databaseConnection.update(INSERT_QUERY, storeSave.getNameStore(), storeSave.getCityStore(),
                storeSave.getStreetStore(), storeSave.getIdDirectorStore(), storeSave.getDateOpenStore());
        if (save_result > 0) {
            log.info("Save new store in database with name {} in {}", storeSave.getNameStore(), new Date());
            return true;
        } else {
            log.error("Error with update store, check db connection in {}", new Date());
            return false;
        }
    }

    @Override
    public boolean updateStore(Store storeUpdate) {
        int update_result = databaseConnection.update(UPDATE_QUERY, storeUpdate.getNameStore(), storeUpdate.getCityStore(),
                storeUpdate.getStreetStore(), storeUpdate.getIdDirectorStore(), storeUpdate.getDateOpenStore(), storeUpdate.getIdStore());
        if (update_result > 0) {
            log.info("Update store with id {} in {}", storeUpdate.getIdStore(), new Date());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Store getById(Long idStore) {
        log.info("Get one store by id - {} in {}", idStore, new Date());
        return databaseConnection.queryForObject(SELECT_ONE_QUERY, new BeanPropertyRowMapper<>(Store.class),idStore);
    }

    @Override
    public boolean deleteStore(Long idStore) {
        int delete_result = databaseConnection.update(DELETE_ONE_QUERY, idStore);
        if (delete_result > 0) {
            log.info("Delete store with id {} in {}", idStore, new Date());
            return true;
        } else {
            log.error("Error delete store, check db connection to database in {}", new Date());
            return false;
        }
    }

    @Override
    public List<Store> getAll() {
        log.info("Get all store in {}",new Date());
        return databaseConnection.query(SELECT_ALL_QUERY,new BeanPropertyRowMapper<>(Store.class));
    }
}
