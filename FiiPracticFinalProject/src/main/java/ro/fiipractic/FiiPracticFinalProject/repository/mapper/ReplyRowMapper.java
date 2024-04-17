package ro.fiipractic.FiiPracticFinalProject.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.fiipractic.FiiPracticFinalProject.models.Reply;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReplyRowMapper implements RowMapper<Reply> {
    @Override
    public Reply mapRow(ResultSet rs, int rowNum) throws SQLException {
        Reply reply = new Reply();
        reply.setId(rs.getString("ID"));
        reply.setUserId(rs.getString("USER_ID"));
        reply.setPostId(rs.getString("POST_ID"));
        reply.setParentId(rs.getString("PARENT_ID"));
        reply.setMessage(rs.getString("MESSAGE"));
        reply.setVarPublic(rs.getBoolean("PUBLIC"));
        return reply;
    }
}
