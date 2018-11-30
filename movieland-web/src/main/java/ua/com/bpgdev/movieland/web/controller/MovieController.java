package ua.com.bpgdev.movieland.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import ua.com.bpgdev.movieland.common.*;
import ua.com.bpgdev.movieland.entity.Movie;
import ua.com.bpgdev.movieland.service.MovieService;

import java.util.List;

@RestController
public class MovieController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private MovieService movieService;

    public MovieController(MovieService movieService) {
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

    @RequestMapping(path = "/movie/{movieId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Movie getById(@PathVariable int movieId) {
        return movieService.getById(movieId);
    }

    @ExceptionHandler(/*{IllegalArgumentException.class}*/)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void handleBadRequests(Exception e) {
        LOGGER.error("Something goes wrong ...", e);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(SortingOrder.class, new SortingOrderConverter());
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
