package ua.com.bpgdev.movieland.dao.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.com.bpgdev.movieland.entity.Review;
import ua.com.bpgdev.movieland.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReviewRowMapper implements RowMapper<Review> {
    @Override
    public Review mapRow(ResultSet resultSet, int i) throws SQLException {
        int id = resultSet.getInt("id");
        int userId = resultSet.getInt("user_id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String text = resultSet.getString("review_text");

        String nickname = firstName + " " + lastName;
        User user = new User(userId, nickname);

        return new Review(id, user, text);
    }
}
