package io.bookstore.dao;

import io.bookstore.domain.Director;

import java.util.List;

public interface DirectorDaoApi {
    Director saveDicrector(Director directorSave);
    Director updateDirector(Director directorUpdate);
    Director getById(Long idDirector);
    void DeleteDerector(Long idDirector);
    List<Director>getAll();
}
