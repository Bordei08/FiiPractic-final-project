package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityAlreadyExistsException;
import ro.fiipractic.FiiPracticFinalProject.exception.EntityNotFoundException;
import ro.fiipractic.FiiPracticFinalProject.models.Mention;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.MentionRowMapper;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.PostRowMapper;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.UserRowMapper;
import ro.fiipractic.FiiPracticFinalProject.service.id.MentionIdGenerator;
import ro.fiipractic.FiiPracticFinalProject.service.id.MentionIdGeneratorImpl;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MentionDAO {
    private JdbcTemplate jdbcTemplate;
    private MentionIdGenerator mentionIdGenerator;

    @Autowired
    public MentionDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.mentionIdGenerator = new MentionIdGeneratorImpl(true);
    }

    public int createMention(String userId, String postId) {
        String id = mentionIdGenerator.generateMentionId(userId, postId);
        try {
            return jdbcTemplate.update("INSERT INTO \"MENTIONS\"(\"ID\", \"USER_ID\", \"POST_ID\") VALUES(?,?,?)", id, userId, postId);
        } catch (Exception e) {
            throw new EntityAlreadyExistsException("Already exist a mention with id: " + id);
        }
    }

    public int deleteMention(String id) {
        return jdbcTemplate.update("DELETE \"MENTIONS\" WHERE \"ID\" = ?", id);
    }

    public Mention getMentionById(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM \"MENTIONS\" WHERE \"ID\" = ?", new MentionRowMapper(), id);
        }catch (Exception e){
            throw new EntityNotFoundException("Not exist a mention with id : " + id);
        }
    }

    public List<User> getAllUsersByPostMentions(String postId){
        return jdbcTemplate.query("SELECT * FROM \"USERS\" WHERE \"ID\" IN ( " +
                " SELECT * FROM \"MENTIONS\" WHERE \"POST_ID\" = ?)", new UserRowMapper(), postId);
    }

    public List<Post> getAllPostsByUserMentions(String userId){
        return jdbcTemplate.query("SELECT * FROM \"POSTS\" WHERE \"ID\" IN ( " +
                " SELECT * FROM \"MENTIONS\" WHERE \"USER_ID\" = ?)", new PostRowMapper(), userId);
    }

}
