package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.junit.Test;
import ua.com.bpgdev.movieland.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserRowMapperTest {

    @Test
    public void mapRow() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("first_name");
        when(mockResultSet.getString("last_name")).thenReturn("last_name");

        User expectedUser = new User(1, "first_name last_name");

        UserRowMapper userRowMapper = new UserRowMapper();
        User actualUser = userRowMapper.mapRow(mockResultSet, 1);

        assertEquals(expectedUser, actualUser);
    }
}