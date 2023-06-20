package io.bookstore.dao.api;

import io.bookstore.domain.Book;

import java.util.List;

public interface BookDaoApi {
    boolean saveBook(Book bookSave);
    boolean updateBook(Book bookUpdate);
    Book getById(Long idBook);
    boolean deleteBook(Long idBook);
    List<Book> getAll();
}
