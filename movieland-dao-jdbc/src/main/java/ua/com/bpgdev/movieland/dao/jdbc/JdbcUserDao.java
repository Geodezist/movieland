package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.UserDao;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.UserRowMapper;
import ua.com.bpgdev.movieland.entity.User;

@Repository
@Primary
public class JdbcUserDao implements UserDao {
    static final UserRowMapper USER_ROW_MAPPER = new UserRowMapper();
    @Value(("${sql.user.getById}"))
    private String sqlGetUserById;

    private JdbcTemplate jdbcTemplate;

    public JdbcUserDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public User getById(int userId) {
        return jdbcTemplate.queryForObject(sqlGetUserById, USER_ROW_MAPPER, userId);
    }

    public String getSqlGetUserById() {
        return sqlGetUserById;
    }
}
