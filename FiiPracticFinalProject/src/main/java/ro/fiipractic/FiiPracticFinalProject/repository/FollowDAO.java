package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityAlreadyExistsException;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityNotFoundException;
import ro.fiipractic.FiiPracticFinalProject.models.Follow;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.FollowRowMapper;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.UserRowMapper;
import ro.fiipractic.FiiPracticFinalProject.service.id.FollowIdGenerator;
import ro.fiipractic.FiiPracticFinalProject.service.id.FollowIdGeneratorImpl;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class FollowDAO {

    private JdbcTemplate jdbcTemplate;
    private FollowIdGenerator followIdGenerator;


    public FollowDAO(final DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.followIdGenerator = new FollowIdGeneratorImpl(true);
    }

    public int createNewFollower(String user1Id, String user2Id, Timestamp timestamp) {
        String id = followIdGenerator.generateFollowId(user1Id, user2Id, timestamp);
        try {
            return jdbcTemplate.update("INSERT INTO \"FOLLOW\"(\"ID\", \"USER1_ID\", \"USER2_ID\", \"TIMESTAMP\") VALUES(?,?,?,?) ", id, user1Id, user2Id, timestamp);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException("Already exist a foolow with id : " + id);
        }
    }

    public Follow getFollowById(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"FOLLOW\" WHERE \"ID\" = ?", new FollowRowMapper(), id);
        }catch(Exception e){
            throw new EntityNotFoundException("Not exist a follow with id : " +id);
        }
    }

    public int deleteFollower(String id) {
        return jdbcTemplate.update("DELETE FROM \"FOLLOW\" WHERE \"ID\" = ?", id);
    }

    public boolean getFollowByUser1IdAndUser2Id(String user1Id, String user2Id) {

        return jdbcTemplate.query("SELECT * FROM  \"FOLLOW\" WHERE \"USER1_ID\" = ? AND  \"USER2_ID\" = ? ", new FollowRowMapper(), user1Id, user2Id).size() == 1;

    }

    public List<User> getFollowers(String user1Id) {
        return jdbcTemplate.query("SELECT * FROM \"USERS\" WHERE \"ID\" IN " +
                "(SELECT \"USER2_ID\" FROM \"FOLLOW\" WHERE \"USER1_ID\" = ?) ", new UserRowMapper(), user1Id);
    }

    public List<User> getFollow(String user2Id) {
        return jdbcTemplate.query("SELECT * FROM \"USERS\" WHERE \"ID\" IN " +
                "(SELECT \"USER1_ID\" FROM \"FOLLOW\" WHERE \"USER2_ID\" = ?) ", new UserRowMapper(), user2Id);
    }

}
