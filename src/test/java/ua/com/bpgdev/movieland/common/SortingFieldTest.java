package ua.com.bpgdev.movieland.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortingFieldTest {

    @Test
    public void valueOfSortingField() {
        String priceSortingFieldInLowerCase = "price";
        String priceSortingFieldInUpperCase = "PRICE";

        String ratingSortingFieldInLowerCase = "rating";
        String ratingSortingFieldInUpperCase = "RATING";

        String priceSortingFieldInLowerCaseWithWhitespaces = " price  ";

        assertEquals(SortingField.PRICE, SortingField.valueOfSortingField(priceSortingFieldInLowerCase));
        assertEquals(SortingField.PRICE, SortingField.valueOfSortingField(priceSortingFieldInUpperCase));

        assertEquals(SortingField.RATING, SortingField.valueOfSortingField(ratingSortingFieldInLowerCase));
        assertEquals(SortingField.RATING, SortingField.valueOfSortingField(ratingSortingFieldInUpperCase));

        assertEquals(SortingField.PRICE, SortingField.valueOfSortingField(priceSortingFieldInLowerCaseWithWhitespaces));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionSortingField(){
        String wrongSortingField = " unknown  ";
        SortingField.valueOfSortingField(wrongSortingField);
    }
}