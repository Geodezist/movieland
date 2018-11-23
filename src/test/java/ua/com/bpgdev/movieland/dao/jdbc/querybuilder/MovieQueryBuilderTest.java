package ua.com.bpgdev.movieland.dao.jdbc.querybuilder;

import org.junit.Test;
import ua.com.bpgdev.movieland.common.RequestParameters;
import ua.com.bpgdev.movieland.common.SortingField;
import ua.com.bpgdev.movieland.common.SortingOrder;
import ua.com.bpgdev.movieland.common.SortingParameter;

import static org.junit.Assert.*;

public class MovieQueryBuilderTest {

    @Test
    public void build() {
        String expectedQueryPriceAsc = "SELECT price FROM movie ORDER BY PRICE ASC, id ASC";
        String expectedQueryPriceDesc = "SELECT price FROM movie ORDER BY PRICE DESC, id ASC";
        String expectedQueryRatingDesc = "SELECT rating FROM movie ORDER BY RATING DESC, id ASC";

        String queryTemplatePrice = "SELECT price FROM movie";
        String queryTemplateRating = "SELECT rating FROM movie";

        SortingParameter sortingParameter = new SortingParameter(SortingField.PRICE, SortingOrder.ASC);
        RequestParameters requestParameters = new RequestParameters(sortingParameter);
        MovieQueryBuilder movieQueryBuilder = new MovieQueryBuilder();
        String actualQueryPriceAsc = movieQueryBuilder.build(queryTemplatePrice, requestParameters);

        sortingParameter = new SortingParameter(SortingField.PRICE, SortingOrder.DESC);
        requestParameters = new RequestParameters(sortingParameter);
        movieQueryBuilder = new MovieQueryBuilder();
        String actualQueryPriceDesc = movieQueryBuilder.build(queryTemplatePrice, requestParameters);


        sortingParameter = new SortingParameter(SortingField.RATING, SortingOrder.DESC);
        requestParameters = new RequestParameters(sortingParameter);
        movieQueryBuilder = new MovieQueryBuilder();
        String actualQueryRatingDesc = movieQueryBuilder.build(queryTemplateRating, requestParameters);

        assertEquals(expectedQueryPriceAsc, actualQueryPriceAsc);
        assertEquals(expectedQueryPriceDesc, actualQueryPriceDesc);
        assertEquals(expectedQueryRatingDesc, actualQueryRatingDesc);
    }
}