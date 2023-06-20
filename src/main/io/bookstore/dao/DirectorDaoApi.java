package io.bookstore.dao;

import io.bookstore.domain.Director;

import java.util.List;

public interface DirectorDaoApi {
    boolean saveDicrector(Director directorSave);
    boolean updateDirector(Director directorUpdate);
    Director getById(Long idDirector);
    boolean DeleteDerector(Long idDirector);
    List<Director>getAll();
}
