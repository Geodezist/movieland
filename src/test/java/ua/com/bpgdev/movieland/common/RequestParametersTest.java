package ua.com.bpgdev.movieland.common;

import org.junit.Test;

public class RequestParametersTest {

    @Test
    public void testValidationOfSortingParameter(){
        SortingParameter sortingParameterRatingDesc = new SortingParameter(SortingField.RATING, SortingOrder.DESC);
        SortingParameter sortingParameterPriceAsc = new SortingParameter(SortingField.PRICE, SortingOrder.ASC);
        SortingParameter sortingParameterPriceDesc = new SortingParameter(SortingField.PRICE, SortingOrder.DESC);

        RequestParameters requestParametersRatingDesc = new RequestParameters(sortingParameterRatingDesc);
        RequestParameters requestParametersPriceAsc = new RequestParameters(sortingParameterPriceAsc);
        RequestParameters requestParametersPriceDesc = new RequestParameters(sortingParameterPriceDesc);
    }

    @Test(expected = RuntimeException.class)
    public void testRatingWrongOrder(){
        SortingParameter sortingParameterRatingAsc = new SortingParameter(SortingField.RATING, SortingOrder.ASC);

        RequestParameters requestParametersRatingAsc = new RequestParameters(sortingParameterRatingAsc);
    }
}