package io.bookstore.service.api;

import io.bookstore.domain.Book;

import java.util.List;

public interface BookServiceApi {
    Book saveBook(Book bookSave);
    Book updateBook(Book bookUpdate);
    Book getById(Long idBook);
    boolean deleteBook(Long idBook);
    List<Book> getAll();
}
