package ua.com.bpgdev.movieland.service;

import ua.com.bpgdev.movieland.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getByMovieID(int movieId);
}
