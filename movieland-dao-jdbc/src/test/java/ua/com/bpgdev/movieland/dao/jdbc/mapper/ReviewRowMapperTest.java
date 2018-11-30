package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.junit.Test;
import ua.com.bpgdev.movieland.entity.Review;
import ua.com.bpgdev.movieland.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReviewRowMapperTest {

    @Test
    public void testMapRow() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getInt("user_id")).thenReturn(1);
        when(mockResultSet.getString("first_name")).thenReturn("first_name");
        when(mockResultSet.getString("last_name")).thenReturn("last_name");
        when(mockResultSet.getString("review_text")).thenReturn("review_text");

        User expectedUser = new User(1, "first_name last_name");
        Review expectedReview = new Review(1, expectedUser, "review_text");

        ReviewRowMapper reviewRowMapper = new ReviewRowMapper();
        Review actualReview = reviewRowMapper.mapRow(mockResultSet, 1);

        assertEquals(expectedReview, actualReview);
    }
}