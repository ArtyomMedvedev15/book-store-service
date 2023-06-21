package io.bookstore.service.api;

import io.bookstore.domain.Author;

import java.util.List;

public interface AuthorServiceApi {
    Author saveAuthor(Author authorSave);
    Author updateAuthor(Author authorUpdate);
    Author getById(Long idAuthor);
    boolean deleteAuthor(Long idAuthor);
    List<Author> getAll();
}
