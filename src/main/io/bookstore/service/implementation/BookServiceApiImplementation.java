package io.bookstore.service.implementation;

import io.bookstore.dao.api.BookDaoApi;
import io.bookstore.domain.Author;
import io.bookstore.domain.Book;
import io.bookstore.domain.Store;
import io.bookstore.service.api.AuthorServiceApi;
import io.bookstore.service.api.BookServiceApi;
import io.bookstore.service.api.StoreServiceApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;

import java.util.Date;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class BookServiceApiImplementation implements BookServiceApi {

    private final BookDaoApi bookDaoApi;
    private final StoreServiceApi storeServiceApi;
    private final AuthorServiceApi authorServiceApi;

    @Override
    public Book saveBook(Book bookSave) throws Exception {
        Book checkExistsBook = bookDaoApi.getAll().stream().filter(o1 -> o1.getNameBook()
                .equals(bookSave.getNameBook())).findFirst().orElse(null);
        Store checkExistsStore = storeServiceApi.getById(bookSave.getIdStoreBook());
        Author checkExistsAuthor = authorServiceApi.getById(bookSave.getIdAuthorBook());

        if(checkExistsBook==null){
            log.error("Book with name {} already exists in {}", bookSave.getNameBook(),new Date());
            throw new Exception(String.format("Book with name %s already exists!",bookSave.getNameBook()));
        }

        if(checkExistsAuthor!=null && checkExistsStore!=null){
            boolean book_save_result = bookDaoApi.saveBook(bookSave);
            if(book_save_result){
                log.info("Save new book in {}",new Date());
                return bookSave;
            }else{
                log.error("Error in save new book in {}", new Date());
                return null;
            }
        }else {
            log.error("Check author id or store id in {}",new Date());
            return null;
        }
    }

    @Override
    public Book updateBook(Book bookUpdate) {
        Store checkExistsStore = storeServiceApi.getById(bookUpdate.getIdStoreBook());
        Author checkExistsAuthor = authorServiceApi.getById(bookUpdate.getIdAuthorBook());

        if(checkExistsAuthor!=null && checkExistsStore!=null){
            boolean book_update_result = bookDaoApi.updateBook(bookUpdate);
            if(book_update_result){
                log.info("Update book with id {} in {}",bookUpdate.getIdBook(),new Date());
                return bookUpdate;
            }else{
                log.error("Error in update in {}", new Date());
                return null;
            }
        }else {
            log.error("Check author id or store id in {}",new Date());
            return null;
        }
    }

    @Override
    public Book getById(Long idBook) {
        try {
            log.info("Get store by id {} in {}", idBook, new Date());
            return bookDaoApi.getById(idBook);
        } catch (DataAccessException exception) {
            log.error("Cannot find store with id {} in {}", idBook, new Date());
            return null;
        }
    }

    @Override
    public boolean deleteBook(Long idBook) {
        Book bookCheckExists = bookDaoApi.getById(idBook);
        if (bookCheckExists != null) {
            boolean delete_book_result = bookDaoApi.deleteBook(idBook);
            if (delete_book_result) {
                log.info("Delete book with id {} in {}", idBook, new Date());
                return true;
            } else {
                log.error("Error in delete book in {}", new Date());
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public List<Book> getAll() {
        log.info("Get all book in {}", new Date());
        return bookDaoApi.getAll();
    }
}
