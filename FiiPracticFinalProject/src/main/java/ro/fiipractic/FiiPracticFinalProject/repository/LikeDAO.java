package ro.fiipractic.FiiPracticFinalProject.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class LikeDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public LikeDAO(DataSource dataSource){
        this.jdbcTemplate =  new JdbcTemplate(dataSource);
    }

    public int  createLike(Integer userId, Integer postId){
        return jdbcTemplate.update("INSERT INTO  \"LIKES\" (\"ID_USER\", \"ID_POST\") VALUES(?,?)", userId, postId);
    }

}
