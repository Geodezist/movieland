package ua.com.bpgdev.movieland.service.enricher;

import ua.com.bpgdev.movieland.entity.Movie;

public interface MovieEnricherService {
    void enrich(Movie movie);
}
