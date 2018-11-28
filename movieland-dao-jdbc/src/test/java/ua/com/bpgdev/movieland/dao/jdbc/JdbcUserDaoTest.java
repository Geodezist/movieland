package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.bpgdev.movieland.entity.User;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcUserDaoTest {

    @Test
    public void testGetById() {
        User expectedUser = new User(1, "Pavlo Bakhmach");

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcUserDao mockJdbcUserDao = new JdbcUserDao(mockJdbcTemplate);
        when(mockJdbcTemplate.queryForObject(mockJdbcUserDao.getSqlGetUserById(),
                JdbcUserDao.USER_ROW_MAPPER, 1)).thenReturn(expectedUser);

        User actualUser = mockJdbcUserDao.getById(1);
        assertEquals(expectedUser, actualUser);
    }
}