package ro.fiipractic.FiiPracticFinalProject.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class ReplyDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ReplyDAO(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public int createReply(Integer postId, Integer parentId, Integer userId, String message){
        return jdbcTemplate.update("INSERT INTO  \"REPLIES\"(\"ID_POST\", \"ID_PARENT\", \"MESSAGE\", \"ID_USER\") VALUES(?,?,?,?,)",postId, parentId, message, userId);
    }
}
