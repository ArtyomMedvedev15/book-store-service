package io.bookstore.service;

import io.bookstore.domain.Director;

import java.util.List;

public interface DirectorServiceApi {
    boolean saveDirector(Director directorSave);
    boolean updateDirector(Director directorUpdate);
    Director getById(Long idDirector);
    boolean deleteDirector(Long idDirector);
    List<Director> getAll();
}
