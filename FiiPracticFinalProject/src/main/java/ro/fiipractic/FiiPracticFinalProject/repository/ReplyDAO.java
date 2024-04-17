package ro.fiipractic.FiiPracticFinalProject.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ro.fiipractic.FiiPracticFinalProject.models.Reply;
import ro.fiipractic.FiiPracticFinalProject.repository.mapper.ReplyRowMapper;
import ro.fiipractic.FiiPracticFinalProject.service.id.ReplyIdGenerator;
import ro.fiipractic.FiiPracticFinalProject.service.id.ReplyIdGeneratorImpl;

import javax.sql.DataSource;
import java.time.temporal.ValueRange;
import java.util.List;

@Repository
public class ReplyDAO {
    private JdbcTemplate jdbcTemplate;
    private ReplyIdGenerator replyIdGenerator;

    @Autowired
    public ReplyDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.replyIdGenerator = new ReplyIdGeneratorImpl(true);
    }

    public int createReply(String postId, String parentId, String userId, String message, boolean varPublic){
        String id = replyIdGenerator.generateReplyId(userId,postId, parentId);
        return jdbcTemplate.update("INSERT INTO  \"REPLIES\"(  \"ID\" ,\"ID_POST\", \"ID_PARENT\", \"MESSAGE\", \"ID_USER\", \"PUBLIC\") VALUES(?,?,?,?,?,?)", id ,postId, parentId, message, userId, varPublic);
    }

    public int deleteReply(String id){
        return jdbcTemplate.update("DELETE FROM \"REPLIES\" WHERE  \"ID\" = ?", id);
    }

    public Reply getReplyById(String id){
        return jdbcTemplate.queryForObject("SELECT * FFROM \"REPLIES\" WHERE \"ID\" = ?", new ReplyRowMapper(), id);
    }

    public Reply getParentReplyByParentId(String parentId){
        return jdbcTemplate.queryForObject("SELECT * FROM \"REPLIES\" WHERE  \"ID\" = ? ", new ReplyRowMapper(), parentId);
    }

    public List<Reply>  getAllRepliesById(String id){
        return jdbcTemplate.query("SELECT * FROM \"REPLIES\" WHERE  \"PARENT_ID\" = ? ", new ReplyRowMapper(), id);
    }

    public List<Reply>  getAllRepliesByUserId(String userID){
        return jdbcTemplate.query("SELECT * FROM \"REPLIES\" WHERE  \"USER_ID\" = ? ", new ReplyRowMapper(), userID);
    }

    public List<Reply>  getAllRepliesByPostId(String postId){
        return jdbcTemplate.query("SELECT * FROM \"REPLIES\" WHERE  \"POST_ID\" = ? ", new ReplyRowMapper(), postId);
    }

    public int updateMessage(String id, String message){
        return jdbcTemplate.update("UPDATE  \"REPLICES\" SET  \"MESSAGE\" = ? WHERE \"ID\" = ?  ", message, id);
    }


}
