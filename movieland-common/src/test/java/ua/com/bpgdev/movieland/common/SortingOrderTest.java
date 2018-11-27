package ua.com.bpgdev.movieland.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class SortingOrderTest {

    @Test
    public void testValueOfSortingOrder() {
        String ascSortingOrderInLowerCase = "asc";
        String ascSortingOrderInUpperCase = "ASC";

        String descSortingOrderInLowerCase = "desc";
        String descSortingOrderInUpperCase = "DESC";

        String ascSortingOrderInLowerCaseWithWhitespaces = " asc  ";

        assertEquals(SortingOrder.ASC, SortingOrder.valueOfSortingOrder(ascSortingOrderInLowerCase));
        assertEquals(SortingOrder.ASC, SortingOrder.valueOfSortingOrder(ascSortingOrderInUpperCase));

        assertEquals(SortingOrder.DESC, SortingOrder.valueOfSortingOrder(descSortingOrderInLowerCase));
        assertEquals(SortingOrder.DESC, SortingOrder.valueOfSortingOrder(descSortingOrderInUpperCase));

        assertEquals(SortingOrder.ASC, SortingOrder.valueOfSortingOrder(ascSortingOrderInLowerCaseWithWhitespaces));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalArgumentExceptionSortingField(){
        String wrongSortingOrder = " unknown  ";
        SortingField.valueOfSortingField(wrongSortingOrder);
    }
}