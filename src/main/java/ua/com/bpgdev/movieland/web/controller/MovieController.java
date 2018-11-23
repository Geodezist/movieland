package ua.com.bpgdev.movieland.web.controller;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.movieland.common.RequestParameters;
import ua.com.bpgdev.movieland.common.SortingField;
import ua.com.bpgdev.movieland.common.SortingOrder;
import ua.com.bpgdev.movieland.common.SortingParameter;
import ua.com.bpgdev.movieland.entity.Movie;
import ua.com.bpgdev.movieland.service.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private MovieService movieService;

    public MovieController(@Qualifier("defaultMovieService") MovieService movieService) {
        this.movieService = movieService;
    }

    @RequestMapping(path = "/movie", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Movie> getAll(@RequestParam(value = "rating", required = false) SortingOrder ratingSortingOrder,
                              @RequestParam(value = "price", required = false) SortingOrder priceSortingOrder) {
        if (ratingSortingOrder == null && priceSortingOrder == null) {
            return movieService.getAll();
        }
        RequestParameters requestParameters = prepareRequestParameter(ratingSortingOrder, priceSortingOrder);
        return movieService.getAll(requestParameters);
    }

    @RequestMapping(path = "/movie/random", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Movie> getThreeRandom(@RequestParam(value = "rating", required = false) SortingOrder ratingSortingOrder,
                                      @RequestParam(value = "price", required = false) SortingOrder priceSortingOrder) {
        if (ratingSortingOrder == null && priceSortingOrder == null) {
            return movieService.getThreeRandom();
        }
        RequestParameters requestParameters = prepareRequestParameter(ratingSortingOrder, priceSortingOrder);
        return movieService.getThreeRandom(requestParameters);
    }

    @RequestMapping(path = "/movie/genre/{genreId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Movie> getByGenreId(@PathVariable int genreId,
                                    @RequestParam(value = "rating", required = false) SortingOrder ratingSortingOrder,
                                    @RequestParam(value = "price", required = false) SortingOrder priceSortingOrder) {
        if (ratingSortingOrder == null && priceSortingOrder == null) {
            return movieService.getByGenreId(genreId);
        }
        RequestParameters requestParameters = prepareRequestParameter(ratingSortingOrder, priceSortingOrder);
        return movieService.getByGenreId(genreId, requestParameters);
    }

    private RequestParameters prepareRequestParameter(SortingOrder ratingSortingOrder, SortingOrder priceSortingOrder) {
        SortingParameter sortingParameter;
        if (ratingSortingOrder != null) {
            sortingParameter = new SortingParameter(SortingField.RATING, ratingSortingOrder);
        } else {
            sortingParameter = new SortingParameter(SortingField.PRICE, priceSortingOrder);
        }
        return new RequestParameters(sortingParameter);
    }
}
