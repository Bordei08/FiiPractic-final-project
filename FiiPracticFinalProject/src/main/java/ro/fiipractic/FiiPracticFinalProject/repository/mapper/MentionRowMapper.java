package ro.fiipractic.FiiPracticFinalProject.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.fiipractic.FiiPracticFinalProject.models.Mention;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MentionRowMapper implements RowMapper<Mention> {
    @Override
    public Mention mapRow(ResultSet rs, int rowNum) throws SQLException {
        Mention mention = new Mention();
        mention.setId(rs.getString("ID"));
        mention.setUerId(rs.getString("USER_ID"));
        mention.setPostId(rs.getString("POST_ID"));
        return mention;
    }
}
