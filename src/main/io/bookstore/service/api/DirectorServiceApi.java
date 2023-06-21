package io.bookstore.service.api;

import io.bookstore.domain.Director;

import java.util.List;

public interface DirectorServiceApi {
    Director saveDirector(Director directorSave);
    Director updateDirector(Director directorUpdate);
    Director getById(Long idDirector);
    boolean deleteDirector(Long idDirector);
    List<Director> getAll();
}
