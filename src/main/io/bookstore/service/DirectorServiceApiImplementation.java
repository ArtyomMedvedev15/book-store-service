package io.bookstore.service;

import io.bookstore.dao.api.DirectorDaoApi;
import io.bookstore.domain.Director;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class DirectorServiceApiImplementation implements DirectorServiceApi{

    private final DirectorDaoApi directorDaoApi;

    @Override
    public Director saveDirector(Director directorSave) {
        boolean save_director_result = directorDaoApi.saveDirector(directorSave);
        if(save_director_result){
            log.info("Save new director in {}", new Date());
            return directorSave;
        }else{
            log.error("Error in save new director, check logs in {}", new Date());
            return null;
        }
    }

    @Override
    public Director updateDirector(Director directorUpdate) {
        boolean update_director_result = directorDaoApi.updateDirector(directorUpdate);
        if(update_director_result){
            log.info("Update director with id {} in {}",directorUpdate.getId(), new Date());
            return directorUpdate;
        }else{
            log.error("Error in update director, check logs in {}", new Date());
            return null;
        }
    }

    @Override
    public Director getById(Long idDirector) {
        try {
            log.info("Get director by id {} in {}", idDirector, new Date());
            return directorDaoApi.getById(idDirector);
        }catch (DataAccessException exception){
            log.error("Cannot find director with id {} in {}",idDirector,new Date());
            return null;
        }

    }

    @Override
    public boolean deleteDirector(Long idDirector) {
        boolean delete_director_result = directorDaoApi.deleteDirector(idDirector);
        if(delete_director_result){
            log.info("Delete director with id {} in {}",idDirector, new Date());
            return true;
        }else{
            log.error("Error in delete director, check logs in {}", new Date());
            return false;
        }
    }

    @Override
    public List<Director> getAll() {
        log.info("Get all director in {}", new Date());
        return directorDaoApi.getAll();
    }
}
