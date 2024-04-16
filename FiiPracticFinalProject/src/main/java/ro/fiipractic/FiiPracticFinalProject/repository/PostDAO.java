package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.service.PostIdGenerator;
import ro.fiipractic.FiiPracticFinalProject.service.PostIdGeneratorImpl;

import javax.sql.DataSource;
import java.sql.Timestamp;

@Repository
public class PostDAO {

    private JdbcTemplate jdbcTemplate;
    private PostIdGenerator postIdGenerator;
    @Autowired
    public PostDAO(DataSource dataSource){
        this.jdbcTemplate =  new JdbcTemplate(dataSource);
        this.postIdGenerator = new PostIdGeneratorImpl(true);
    }

    public int createPost(String creatorId, String message, Timestamp timestamp){
        String id = postIdGenerator.generatePostId(creatorId, timestamp);
        return  jdbcTemplate.update("INSERT INTO  \"POSTS\" (\"ID\",\"MESSAGE\", \"TIMESTAMP\", \"CREATOR_ID\") VALUES(?,?,?,?)", id,message, timestamp, creatorId);
    }

}
