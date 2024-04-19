package ro.fiipractic.FiiPracticFinalProject.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.fiipractic.FiiPracticFinalProject.models.Post;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PostRowMapper implements RowMapper<Post> {
    @Override
    public Post mapRow(ResultSet rs, int rowNum) throws SQLException {
        Post post = new Post();
        post.setId(rs.getString("ID"));
        post.setMessage(rs.getString("MESSAGE"));
        post.setTimestamp(rs.getTimestamp("TIMESTAMP"));
        post.setCreatorId(rs.getString("CREATOR_ID"));
        post.setRepostId(rs.getString("REPOST_ID"));
        post.setSharerId(rs.getString("SHARER_ID"));
        return post;
    }
}
