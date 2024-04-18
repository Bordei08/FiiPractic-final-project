package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityAlreadyExistsException;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityNotFoundException;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.PostRowMapper;
import ro.fiipractic.FiiPracticFinalProject.service.id.PostIdGenerator;
import ro.fiipractic.FiiPracticFinalProject.service.id.PostIdGeneratorImpl;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class PostDAO {

    private JdbcTemplate jdbcTemplate;
    private PostIdGenerator postIdGenerator;

    @Autowired
    public PostDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.postIdGenerator = new PostIdGeneratorImpl(true);
    }

    public int createPost(String creatorId, String message, Timestamp timestamp) {
        String id = postIdGenerator.generatePostId(creatorId, timestamp);
        try {
            return jdbcTemplate.update("INSERT INTO  \"POSTS\" (\"ID\",\"MESSAGE\", \"TIMESTAMP\", \"CREATOR_ID\") VALUES(?,?,?,?)", id, message, timestamp, creatorId);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException("Already exist a post with id : " + id);
        }
    }


    public int deletePost(String id) {
        return jdbcTemplate.update("DELETE FROM \"POSTS\" WHERE \"ID\" = ? ", id);
    }

    public int updateMessage(String id, String message) {
        return jdbcTemplate.update("UPDATE  \"POSTS\" SET  \"MESSAGE\" = ? WHERE \"ID\" = ?  ", message, id);
    }

    public Post getPostById(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM  \"POSTS\" WHERE  \"ID\"  = ? ", new PostRowMapper(), id);
        }catch (Exception e){
            throw new EntityNotFoundException("Not exist a post with id : " + id);
        }
    }

    public List<Post> getAllPostByUserId(String userId) {
        return jdbcTemplate.query("SELECT * FROM \"POSTS\" WHERE \"CREATOR_ID\" = ?", new PostRowMapper(), userId);
    }

    public List<Post> getFeed(String userId) {
        return jdbcTemplate.query("SELECT * FROM \"POSTS\" WHERE \"CREATOR_ID\" IN (" +
                "SELECT \"USER2_ID\" FROM \"FOLLOW\" WHERE \"USER1_ID\" = ? )", new PostRowMapper(), userId);
    }

}
