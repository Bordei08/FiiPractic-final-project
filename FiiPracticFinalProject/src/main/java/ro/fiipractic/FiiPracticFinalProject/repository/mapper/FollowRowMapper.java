package ro.fiipractic.FiiPracticFinalProject.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.fiipractic.FiiPracticFinalProject.models.Follow;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FollowRowMapper implements RowMapper<Follow> {

    @Override
    public Follow mapRow(ResultSet rs, int rowNum) throws SQLException {
        final Follow  follow =  new Follow();
        follow.setId(rs.getString("ID"));
        follow.setUser1Id(rs.getString("USER1_ID"));
        follow.setUser2Id(rs.getString("USER2_ID"));
        follow.setTimestamp(rs.getTimestamp("TIMESTAMP"));
        return follow;
    }
}
