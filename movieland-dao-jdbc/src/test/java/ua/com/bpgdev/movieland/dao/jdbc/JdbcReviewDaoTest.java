package ua.com.bpgdev.movieland.dao.jdbc;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.com.bpgdev.movieland.entity.Review;
import ua.com.bpgdev.movieland.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class JdbcReviewDaoTest {

    @Test
    public void getByMovieId() {
        List<Review> expectedReviews = new ArrayList<>();
        expectedReviews.add(new Review(1, new User(1, "Pavlo Bakhmach"), "Test Review"));
        expectedReviews.add(new Review(1, new User(2, "Vasyliy Poopkin"), "Test Review by Poopkin"));

        JdbcTemplate mockJdbcTemplate = mock(JdbcTemplate.class);
        JdbcReviewDao mockJdbcReviewDao = new JdbcReviewDao(mockJdbcTemplate);
        when(mockJdbcTemplate.query(mockJdbcReviewDao.getSqlGetReviewsByMovieId(),
                JdbcReviewDao.REVIEW_ROW_MAPPER, 1)).thenReturn(expectedReviews);

        List<Review> actualReviews = mockJdbcReviewDao.getByMovieId(1);
        assertEquals(expectedReviews, actualReviews);

    }
}