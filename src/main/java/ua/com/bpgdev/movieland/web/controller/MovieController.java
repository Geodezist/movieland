package ua.com.bpgdev.movieland.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.movieland.common.ParameterInfo;
import ua.com.bpgdev.movieland.common.SortingField;
import ua.com.bpgdev.movieland.common.SortingOrder;
import ua.com.bpgdev.movieland.entity.Movie;
import ua.com.bpgdev.movieland.service.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(@Autowired MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(path = "/movie", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Movie> getAll(@RequestParam(value = "rating") @Nullable String ratingSortingOrder,
                              @RequestParam(value = "price") @Nullable String priceSortingOrder) {
        if (ratingSortingOrder == null && priceSortingOrder == null) {
            return movieService.getAll();
        }
        ParameterInfo parameterInfo = prepareParameterInfo(ratingSortingOrder, priceSortingOrder);
        return movieService.getAll(parameterInfo);
    }

    @RequestMapping(path = "/movie/random", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Movie> getThreeRandom(@RequestParam(value = "rating") @Nullable String ratingSortingOrder,
                                      @RequestParam(value = "price") @Nullable String priceSortingOrder) {
        if (ratingSortingOrder == null && priceSortingOrder == null) {
            return movieService.getThreeRandom();
        }
        ParameterInfo parameterInfo = prepareParameterInfo(ratingSortingOrder, priceSortingOrder);
        return movieService.getThreeRandom(parameterInfo );
    }

    @RequestMapping(path = "/movie/genre/{genreId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Movie> getByGenreId(@PathVariable int genreId,
                                    @RequestParam(value = "rating") @Nullable String ratingSortingOrder,
                                    @RequestParam(value = "price") @Nullable String priceSortingOrder) {
        if (ratingSortingOrder == null && priceSortingOrder == null) {
            return movieService.getByGenreId(genreId );
        }
        ParameterInfo parameterInfo = prepareParameterInfo(ratingSortingOrder, priceSortingOrder);
        return movieService.getByGenreId(genreId, parameterInfo);
    }

    private ParameterInfo prepareParameterInfo(String ratingSortingOrder, String priceSortingOrder){
        ParameterInfo parameterInfo = new ParameterInfo();

        if (ratingSortingOrder != null) {
            parameterInfo.getParameters().put(SortingField.RATING, SortingOrder.valueOf(ratingSortingOrder.toUpperCase()));
        } else {
            parameterInfo.getParameters().put(SortingField.PRICE, SortingOrder.valueOf(priceSortingOrder.toUpperCase()));
        }
        return parameterInfo;
    }
}
