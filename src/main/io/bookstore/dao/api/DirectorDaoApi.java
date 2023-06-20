package io.bookstore.dao.api;

import io.bookstore.domain.Director;

import java.util.List;

public interface DirectorDaoApi {
    boolean saveDirector(Director directorSave);
    boolean updateDirector(Director directorUpdate);
    Director getById(Long idDirector);
    boolean deleteDirector(Long idDirector);
    List<Director>getAll();
}
