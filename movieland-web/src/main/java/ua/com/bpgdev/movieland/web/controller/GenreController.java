package ua.com.bpgdev.movieland.web.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.com.bpgdev.movieland.entity.Genre;
import ua.com.bpgdev.movieland.service.GenreService;

import java.util.List;

@RestController
public class GenreController {
    private GenreService genreService;

    public GenreController(@Qualifier("defaultGenreService") GenreService genreService){
        this.genreService = genreService;
    }

    @RequestMapping(path = "/genre", method = RequestMethod.GET)
    public List<Genre> getAll(){
        return genreService.getAll();
    }
}
