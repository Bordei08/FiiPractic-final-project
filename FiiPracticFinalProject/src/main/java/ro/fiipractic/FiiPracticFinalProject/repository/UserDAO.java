package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.exception.UserNotFoundException;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.UserRowMapper;
import ro.fiipractic.FiiPracticFinalProject.service.UserIdGenerator;
import ro.fiipractic.FiiPracticFinalProject.service.UserIdGeneratorImpl;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;


@Repository
public class UserDAO {

    private JdbcTemplate jdbcTemplate;
    private UserIdGenerator userIdGenerator;

    @Autowired
    public void setDataSource(final DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        this.userIdGenerator = new UserIdGeneratorImpl(true);

    }

    public int createUser(String username, String firstName, String lastName, String email, String password) {
        String id = userIdGenerator.generateUserId(username, firstName, lastName);
        return jdbcTemplate.update("INSERT INTO \"USERS\"( \"ID\",\"USERNAME\",\"FIRST_NAME\", \"LAST_NAME\", \"EMAIL\", \"PASSWORD\") VALUES (?,?,?, ?, ?, ?)",id, username, firstName, lastName, email, password);
    }

    public List<User> getAllUsers() {
        return jdbcTemplate.query("SELECT * FROM \"USERS\" ", new UserRowMapper());
    }

    public User getUserByUsername(String username) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"USERS\" WHERE \"USERNAME\" = ? ", new UserRowMapper(), username);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(String.format("User with username %s was not found", username));
        }
    }

    public User getUserById(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"USERS\" WHERE \"ID\" = ? ", new UserRowMapper(), id);
        } catch (EmptyResultDataAccessException ex) {
            throw new UserNotFoundException(String.format("User with id %s was not found", id));
        }
    }

    public List<User> getUsersByFirstName(String firstName){
        return jdbcTemplate.query("SELECT * FROM \"USERS\" WHERE \"FIRST_NAME\" = ? ", new UserRowMapper(), firstName);
    }

    public List<User> getUsersByLastName(String lastName){
        return jdbcTemplate.query("SELECT * FROM \"USERS\" WHERE \"LAST_NAME\" = ? ", new UserRowMapper(), lastName);
    }

    public int updateUser(String username, String firstName, String lastName, String email, String password, String id){
        return jdbcTemplate.update("UPDATE \"USERS\" SET \"USERNAME\" = ?, \"FIRST_NAME\" = ?, \"LAST_NAME\" = ?, \"EMAIL\" = ?, \"PASSWORD\" ? WHERE \"ID\" = ?", username, firstName, lastName, email, password, id );
    }

    public int deleteUser(String id){
        return jdbcTemplate.update("DELETE FROM \"USERS\" WHERE \"ID\" = ?", id);
    }


    //TO DO
    //FollowIdGenerator
    //new function in UserService
    public int createNewFollower(String user1Id, String user2ID, Timestamp timestamp){
        return 1;
    }

    //TO DO
    //new function in UserService
    public int deleteFollower(String user1Id, String user2Id){
        return 1;
    }
}
