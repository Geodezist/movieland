package ua.com.bpgdev.movieland.common;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RequestParameters {
    private static final HashSet<SortingParameter> ALLOWED_SORTING_PARAMETER = Stream.of(
            new SortingParameter(SortingField.RATING, SortingOrder.DESC),
            new SortingParameter(SortingField.PRICE, SortingOrder.ASC),
            new SortingParameter(SortingField.PRICE, SortingOrder.DESC))
            .collect(Collectors.toCollection(HashSet::new));

    private SortingParameter sortingParameter;
    private CurrencyCode currencyParameter;

    public RequestParameters() {
    }

    public RequestParameters(CurrencyCode currencyParameter) {
        this.currencyParameter = currencyParameter;
    }

    public RequestParameters(SortingParameter sortingParameter) {
        validateSortingParameter(sortingParameter);
        this.sortingParameter = sortingParameter;
    }

    public CurrencyCode getCurrencyParameter() {
        return currencyParameter;
    }

    public void setCurrencyParameter(CurrencyCode currencyParameter) {
        this.currencyParameter = currencyParameter;
    }

    public SortingParameter getSortingParameter() {
        return sortingParameter;
    }

    public void setSortingParameter(SortingParameter sortingParameter) {
        this.sortingParameter = sortingParameter;
    }

    private void validateSortingParameter(SortingParameter sortingParameter) {
        if (!ALLOWED_SORTING_PARAMETER.contains(sortingParameter)) {
            throw new RuntimeException("Such ordering condition " + sortingParameter.getSortingOrder() +
                    " for " + sortingParameter.getSortingField() + " is not allowed.");
        }
    }
}
