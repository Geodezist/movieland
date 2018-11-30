package ua.com.bpgdev.movieland.dao;

import ua.com.bpgdev.movieland.entity.Review;

import java.util.List;

public interface ReviewDao {
    List<Review> getByMovieId(int movieId);
}
