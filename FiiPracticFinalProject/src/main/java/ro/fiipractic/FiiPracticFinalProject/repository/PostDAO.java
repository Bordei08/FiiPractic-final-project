package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityAlreadyExistsException;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityNotFoundException;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.PostRowMapper;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.UserRowMapper;
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

    public int createPost(String creatorId, String message, Timestamp timestamp, String repostId, String sharerId) {
        String id = postIdGenerator.generatePostId(creatorId, timestamp, repostId, sharerId);
        try {
            return jdbcTemplate.update("INSERT INTO  \"POSTS\" " +
                    "(\"ID\",\"MESSAGE\", \"TIMESTAMP\", \"CREATOR_ID\", \"REPOST_ID\", \"SHARER_ID\")" +
                    " VALUES(?,?,?,?,?,?)", id, message, timestamp, creatorId, repostId, sharerId);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException("Already exist a post with id : " + id);
        }
    }

    public int createRepost(Post repost) {
        repost.setId(postIdGenerator.generatePostId(repost.getCreatorId(), repost.getTimestamp(), repost.getRepostId(), repost.getSharerId()));
        try {
            return jdbcTemplate.update("INSERT INTO  \"POSTS\" " +
                    "(\"ID\",\"MESSAGE\", \"TIMESTAMP\", \"CREATOR_ID\", \"REPOST_ID\", \"SHARER_ID\")" +
                    " VALUES(?,?,?,?,?,?)", repost.getId(), repost.getMessage(), repost.getTimestamp(), repost.getCreatorId(), repost.getRepostId(), repost.getSharerId());
        } catch (Exception e) {
            throw new EntityAlreadyExistsException("Already exist a repost with id : " + repost.getId());
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
        } catch (Exception e) {
            throw new EntityNotFoundException("Not exist a post with id : " + id);
        }
    }

    public List<Post> getAllPostByUserId(String userId) {
        return jdbcTemplate.query("SELECT * FROM \"POSTS\" WHERE \"CREATOR_ID\" = ? ORDER BY \"TIMESTAMP\" DESC", new PostRowMapper(), userId);
    }

    public List<Post> getFeed(String userId) {
        return jdbcTemplate.query("SELECT * FROM \"POSTS\" WHERE \"CREATOR_ID\" IN (" +
                "SELECT \"USER2_ID\" FROM \"FOLLOW\" WHERE \"USER1_ID\" = ? )", new PostRowMapper(), userId);
    }

    public List<User> getAllUsersByRepost(String postId) {
        return jdbcTemplate.query("SELECT * FROM \"USERS\" WHERE \"ID\" IN (" +
                "SELECT \"SHARER_ID\" FROM \"POSTS\" WHERE \"REPOST_ID\" = ? )", new UserRowMapper(), postId);
    }

    public List<Post> getAllRepostsByUser(String userId) {
        return jdbcTemplate.query("SELECT * FROM \"POSTS\" WHERE \"ID\" IN (" +
                "SELECT \"ID\" FROM \"POSTS\" WHERE \"SHARER_ID\" = ? )", new PostRowMapper(), userId);
    }

}
