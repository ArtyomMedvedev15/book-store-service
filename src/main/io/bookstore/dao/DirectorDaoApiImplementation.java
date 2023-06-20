package io.bookstore.dao;

import io.bookstore.domain.Director;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

@Slf4j
public class DirectorDaoApiImplementation implements DirectorDaoApi{

    @Autowired
    private JdbcTemplate databaseConnection;

    private static final String INSERT_QUERY = "insert into director(namedirector, sonamedirector, agedirector, dategetpositiondirector) values(?,?,?,?)";
    private static final String UPDATE_QUERY = "update director set namedirector=?, sonamedirector=?, agedirector=?, dategetpositiondirector=? where id=?";
    private static final String SELECT_ONE_QUERY = "select * from director where id = ?";
    private static final String DELETE_ONE_QUERY = "delete from director where id = ?";
    private static final String SELECT_ALL_QUERY = "select * from director";

    @Override
    public boolean saveDicrector(Director directorSave) {
        int save_result = databaseConnection.update(INSERT_QUERY,directorSave.getNameDirector(),directorSave.getSonameDirector(),
                directorSave.getAgeDirector(),directorSave.getDateGetPositionDirector());
        if(save_result>0) {
            log.info("Save new director to database with name {} in {}",
                    String.format("%s %s", directorSave.getNameDirector(), directorSave.getSonameDirector()), new Date());
            return true;
        }else{
            log.error("Error in save director, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public boolean updateDirector(Director directorUpdate) {
        int update_result = databaseConnection.update(UPDATE_QUERY,directorUpdate.getNameDirector(),directorUpdate.getSonameDirector(),
                directorUpdate.getAgeDirector(),directorUpdate.getDateGetPositionDirector(),directorUpdate.getId());
        if(update_result>0) {
            log.info("Update director with id {} in {}", directorUpdate.getId(), new Date());
            return true;
        }else{
            log.error("Error update director, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public Director getById(Long idDirector) {
        log.info("Get director by id - {} in {}",idDirector,new Date());
        return databaseConnection.queryForObject(SELECT_ONE_QUERY, new BeanPropertyRowMapper<>(Director.class),idDirector);
    }

    @Override
    public boolean DeleteDerector(Long idDirector) {
        int delete_result = databaseConnection.update(DELETE_ONE_QUERY,idDirector);
        if(delete_result>0) {
            log.info("Delete director with id {} in {}", idDirector, new Date());
            return true;
        }else{
            log.error("Error delete director, check db connection to database in {}",new Date());
            return false;
        }
    }

    @Override
    public List<Director> getAll() {
        log.info("Get all directors in {}",new Date());
        return databaseConnection.query(SELECT_ALL_QUERY,new BeanPropertyRowMapper<>(Director.class));
    }
}
