package io.bookstore.dao.implementation;

import io.bookstore.dao.api.BookDaoApi;
import io.bookstore.domain.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@Slf4j
public class BookDaoApiImplementation implements BookDaoApi {

    private static final String INSERT_QUERY = "insert into book(namebook, describebook, idauthorbook, idstorebook, datestartsalebook)  values(?,?,?,?,?)";
    private static final String UPDATE_QUERY = "update book set namebook=?, describebook=?, idauthorbook=?, idstorebook=?, datestartsalebook=? where idbook=?";
    private static final String SELECT_ONE_QUERY = "select * from book where idbook=?";
    private static final String DELETE_ONE_QUERY = "delete from book where idbook=?";
    private static final String SELECT_ALL_QUERY = "select * from book";

    @Autowired
    private JdbcTemplate databaseConnection;

    @Override
    public boolean saveBook(Book bookSave) {
        int save_result = databaseConnection.update(INSERT_QUERY,bookSave.getNameBook(),bookSave.getDescribeBook(),
                bookSave.getIdAuthorBook(),bookSave.getIdStoreBook(),bookSave.getDateStartSaleBook());
        if(save_result>0) {
            log.info("Save new book to database with name {} in {}",
                    bookSave.getNameBook(), new Date());
            return true;
        }else{
            log.error("Error in save book, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public boolean updateBook(Book bookUpdate) {
        int update_result = databaseConnection.update(UPDATE_QUERY,bookUpdate.getNameBook(),bookUpdate.getDescribeBook(),
                bookUpdate.getIdAuthorBook(),bookUpdate.getIdStoreBook(),bookUpdate.getDateStartSaleBook(),bookUpdate.getIdBook());
        if(update_result>0) {
            log.info("Update book with id {} in {}",
                    bookUpdate.getIdBook(), new Date());
            return true;
        }else{
            log.error("Error in update book, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public Book getById(Long idBook) {
        log.info("Get book by id - {} in {}",idBook,new Date());
        return databaseConnection.queryForObject(SELECT_ONE_QUERY, new BeanPropertyRowMapper<>(Book.class),idBook);
    }

    @Override
    public boolean deleteBook(Long idBook) {
        int delete_result = databaseConnection.update(DELETE_ONE_QUERY,idBook);
        if(delete_result>0) {
            log.info("Delete book with id {} in {}", idBook, new Date());
            return true;
        }else{
            log.error("Error delete book, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public List<Book> getAll() {
        log.info("Get all book in {}",new Date());
        return databaseConnection.query(SELECT_ALL_QUERY,new BeanPropertyRowMapper<>(Book.class));
    }
}
