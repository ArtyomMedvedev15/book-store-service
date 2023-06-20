package io.bookstore.dao.api;

import io.bookstore.domain.Author;

import java.util.List;

public interface AuthorDaoApi {
    boolean saveAuthor(Author authorSave);
    boolean updateAuthor(Author authorUpdate);
    Author getById(Long idAuthor);
    boolean deleteAuthor(Long idAuthor);
    List<Author> getAll();
}
