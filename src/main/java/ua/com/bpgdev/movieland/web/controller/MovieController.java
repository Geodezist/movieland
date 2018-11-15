package ua.com.bpgdev.movieland.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.bpgdev.movieland.entity.Movie;
import ua.com.bpgdev.movieland.service.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(@Autowired MovieService movieService){
        this.movieService = movieService;
    }

    @RequestMapping(path="/movie", method = RequestMethod.GET)
    public List<Movie> getAll(){
        return movieService.getAll();
    }

    @RequestMapping(path = "/movie/random", method=RequestMethod.GET)
    public List<Movie> getThreeRandom(){
        return  movieService.getThreeRandom();
    }

}
