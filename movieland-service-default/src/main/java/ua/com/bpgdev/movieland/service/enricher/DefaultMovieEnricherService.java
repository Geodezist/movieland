package ua.com.bpgdev.movieland.service.enricher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.bpgdev.movieland.entity.*;
import ua.com.bpgdev.movieland.service.CountryService;
import ua.com.bpgdev.movieland.service.GenreService;
import ua.com.bpgdev.movieland.service.ReviewService;

import java.util.List;

@Service
public class DefaultMovieEnricherService implements MovieEnricherService {
    private GenreService genreService;
    private CountryService countryService;
    private ReviewService reviewService;

    public DefaultMovieEnricherService(@Autowired GenreService genreService,
                                       @Autowired CountryService countryService,
                                       @Autowired ReviewService reviewService
    ) {
        this.genreService = genreService;
        this.countryService = countryService;
        this.reviewService = reviewService;
    }

    @Override
    public void enrich(Movie movie) {
        int movieId = movie.getId();

        List<Genre> genres = genreService.getByMovieId(movieId);
        List<Country> countries = countryService.getByMovieId(movieId);
        List<Review> reviews = reviewService.getByMovieID(movieId);

        movie.setGenres(genres);
        movie.setCountries(countries);
        movie.setReviews(reviews);
    }
}
