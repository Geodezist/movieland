package ua.com.bpgdev.movieland.dao.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.com.bpgdev.movieland.dao.jdbc.mapper.ReviewRowMapper;
import ua.com.bpgdev.movieland.entity.Review;

import java.util.List;

@Repository
@Primary
public class JdbcReviewDao implements ua.com.bpgdev.movieland.dao.ReviewDao {
    static final ReviewRowMapper REVIEW_ROW_MAPPER = new ReviewRowMapper();
    @Value("${sql.review.getByMovieId}")
    private String sqlGetReviewsByMovieId;

    private JdbcTemplate jdbcTemplate;

    public JdbcReviewDao(@Autowired JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Review> getByMovieId(int movieId) {
        return jdbcTemplate.query(sqlGetReviewsByMovieId, REVIEW_ROW_MAPPER, movieId);
    }

    public String getSqlGetReviewsByMovieId() {
        return sqlGetReviewsByMovieId;
    }
}
