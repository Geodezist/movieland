package ua.com.bpgdev.movieland.common;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestParameters {
    private static final HashSet<SortingParameter> allowedParameter = Stream.of(
            new SortingParameter(SortingField.RATING, SortingOrder.DESC),
            new SortingParameter(SortingField.PRICE, SortingOrder.ASC),
            new SortingParameter(SortingField.PRICE, SortingOrder.DESC))
            .collect(Collectors.toCollection(HashSet::new));

    private SortingParameter sortingParameter;

    public RequestParameters(SortingParameter sortingParameter) {
        validateSortingParameter(sortingParameter);
        this.sortingParameter = sortingParameter;
    }

    public SortingParameter getSortingParameter() {
        return sortingParameter;
    }

    public void setSortingParameter(SortingParameter sortingParameter) {
        this.sortingParameter = sortingParameter;
    }

    private void validateSortingParameter(SortingParameter sortingParameter) {
        if (!allowedParameter.contains(sortingParameter)) {
            throw new RuntimeException("Such ordering condition " + sortingParameter.getSortingOrder() +
                    " for " + sortingParameter.getSortingField() + " is not allowed.");
        }
    }
}
