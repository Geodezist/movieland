package ua.com.bpgdev.movieland.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.dao.ReviewDao;
import ua.com.bpgdev.movieland.entity.Review;

import java.util.List;

@Service
public class DefaultReviewService implements ReviewService {
    private ReviewDao reviewDao;

    public DefaultReviewService(@Autowired ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getByMovieID(int movieId) {
        return reviewDao.getByMovieId(movieId);
    }
}
