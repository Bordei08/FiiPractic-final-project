package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.models.Like;
import ro.fiipractic.FiiPracticFinalProject.models.Post;
import ro.fiipractic.FiiPracticFinalProject.models.User;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.LikeRowMapper;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.PostRowMapper;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.UserRowMapper;
import ro.fiipractic.FiiPracticFinalProject.service.id.LikeIdGenerator;
import ro.fiipractic.FiiPracticFinalProject.service.id.LikeIdGeneratorImpl;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class LikeDAO {
    private JdbcTemplate jdbcTemplate;
    private LikeIdGenerator likeIdGenerator;

    @Autowired
    public LikeDAO(final DataSource dataSource){
        this.jdbcTemplate =  new JdbcTemplate(dataSource);
        this.likeIdGenerator = new LikeIdGeneratorImpl(true);
    }

    public int  createLike(String userId, String postId){
        String id = likeIdGenerator.generateLikeId(userId, postId);
        return jdbcTemplate.update("INSERT INTO  \"LIKES\" (  \"ID\" ,\"USER_ID\", \"POST_ID\") VALUES(?,?,?)", id,userId, postId);
    }


    public Like getLikeById(String id){
        return jdbcTemplate.queryForObject("SELECT * FROM \"LIKES\" WHERE \"ID\" = ?", new LikeRowMapper(), id);
    }

    public int deleteLike(String id){
        return jdbcTemplate.update("DELETE FROM \"LIKES\" WHERE \"ID\" = ?", id);
    }

    public List<User>  getAllUsersForPost(String postId){
        return jdbcTemplate.query("SELECT * FROM \"USERS\" WHERE \"ID\" IN " +
                "(SELECT \"USER_ID\" FROM  \"LIKES\" WHERE \"POST_ID\" = ?)", new UserRowMapper(), postId);
    }

    public List<Post> getAllPostForUser(String userId){
        return jdbcTemplate.query("SELECT * FROM \"POSTS\" WHERE \"ID\" IN " +
                "(SELECT \"POST_ID\" FROM  \"LIKES\" WHERE \"USER_ID\" = ?)", new PostRowMapper(), userId);
    }

}
