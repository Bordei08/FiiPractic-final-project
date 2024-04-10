package ro.fiipractic.FiiPracticFinalProject.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ro.fiipractic.FiiPracticFinalProject.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(final ResultSet rs, final int rowNum) throws SQLException {
        final User user = new User();

        user.setId(rs.getString("ID"));
        user.setUsername(rs.getString("USERNAME"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setEmail(rs.getString("EMAIL"));
        user.setPassword(rs.getString("PASSWORD"));

        return user;
    }
}
