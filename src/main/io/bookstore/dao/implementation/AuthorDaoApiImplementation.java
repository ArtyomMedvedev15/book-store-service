package io.bookstore.dao.implementation;

import io.bookstore.dao.api.AuthorDaoApi;
import io.bookstore.domain.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@Slf4j
public class AuthorDaoApiImplementation implements AuthorDaoApi {

    private static final String INSERT_QUERY = "insert into author(nameauthor, sonameauthor, fanameauthor, dateaddtoauthor) values(?,?,?,?)";
    private static final String UPDATE_QUERY = "update author set nameauthor=?, sonameauthor=?, fanameauthor=?, dateaddtoauthor=? where idauthor=?";
    private static final String SELECT_ONE_QUERY = "select * from author where idauthor=?";
    private static final String DELETE_ONE_QUERY = "delete from author where idauthor=?";
    private static final String SELECT_ALL_QUERY = "select * from author";

    @Autowired
    private JdbcTemplate databaseConnection;

    @Override
    public boolean saveAuthor(Author authorSave) {
        int save_result = databaseConnection.update(INSERT_QUERY,authorSave.getNameAuthor(),authorSave.getSonameAuthor(),
                authorSave.getFanameAuthor(),authorSave.getDateAddToAuthor());
        if(save_result>0) {
            log.info("Save new author to database with name {} in {}",
                    String.format("%s %s", authorSave.getSonameAuthor(), authorSave.getNameAuthor()), new Date());
            return true;
        }else{
            log.error("Error in save author, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public boolean updateAuthor(Author authorUpdate) {
        int update_result = databaseConnection.update(UPDATE_QUERY,authorUpdate.getNameAuthor(),authorUpdate.getSonameAuthor(),
                authorUpdate.getFanameAuthor(),authorUpdate.getDateAddToAuthor(),authorUpdate.getIdAuthor());
        if(update_result>0) {
            log.info("Update author with id {} in {}", authorUpdate.getIdAuthor(), new Date());
            return true;
        }else{
            log.error("Error update author, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public Author getById(Long idAuthor) {
        log.info("Get author by id - {} in {}",idAuthor,new Date());
        return databaseConnection.queryForObject(SELECT_ONE_QUERY, new BeanPropertyRowMapper<>(Author.class),idAuthor);
    }

    @Override
    public boolean deleteAuthor(Long idAuthor) {
        int delete_result = databaseConnection.update(DELETE_ONE_QUERY,idAuthor);
        if(delete_result>0) {
            log.info("Delete author with id {} in {}", idAuthor, new Date());
            return true;
        }else{
            log.error("Error delete author, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public List<Author> getAll() {
        log.info("Get all author in {}",new Date());
        return databaseConnection.query(SELECT_ALL_QUERY,new BeanPropertyRowMapper<>(Author.class));    }
}
