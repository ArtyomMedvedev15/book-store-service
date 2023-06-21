package io.bookstore.service.implementation;

import io.bookstore.dao.api.AuthorDaoApi;
import io.bookstore.dao.api.BookDaoApi;
import io.bookstore.domain.Author;
import io.bookstore.domain.Book;
import io.bookstore.service.api.AuthorServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class AuthorServiceApiImplementation implements AuthorServiceApi {

    private final AuthorDaoApi authorDaoApi;

    private final BookDaoApi bookDaoApi;

    @Override
    public Author saveAuthor(Author authorSave) {
        boolean author_save_result = authorDaoApi.saveAuthor(authorSave);
        if (author_save_result) {
            log.info("Save new author in {}", new Date());
            return authorSave;
        } else {
            log.error("Error in save new author in {}", new Date());
            return null;
        }
    }

    @Override
    public Author updateAuthor(Author authorUpdate) {
        boolean author_update_result = authorDaoApi.updateAuthor(authorUpdate);
        if (author_update_result) {
            log.info("Update author with id {} in {}", authorUpdate.getIdAuthor(), new Date());
            return authorUpdate;
        } else {
            log.error("Error in update author in {}", new Date());
            return null;
        }
    }

    @Override
    public Author getById(Long idAuthor) {
        try {
            log.info("Get author by id {} in {}", idAuthor, new Date());
            return authorDaoApi.getById(idAuthor);
        } catch (DataAccessException exception) {
            log.error("Cannot find author with id {} in {}", idAuthor, new Date());
            return null;
        }
    }

    @Override
    public boolean deleteAuthor(Long idAuthor) {
        Author authorCheckExists = authorDaoApi.getById(idAuthor);
        if (authorCheckExists != null) {
            boolean delete_author_result = authorDaoApi.deleteAuthor(idAuthor);
            if (delete_author_result) {
                log.info("Delete author with id {} in {}", idAuthor, new Date());
                return true;
            } else {
                log.error("Error in delete author in {}", new Date());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Author> getAll() {
        log.info("Get all authors in {}", new Date());
        return authorDaoApi.getAll();
    }

    @Override
    public List<Book> getAllAuthorBook(Long idAuthor) {
        var authorsBook = bookDaoApi.getAll().stream()
                        .filter(o1 -> o1.getIdAuthorBook().equals(idAuthor))
                        .toList();
        log.info("Get all author book with id {} in {}",idAuthor,new Date());
        return authorsBook;
    }
}
