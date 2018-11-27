package ua.com.bpgdev.movieland.common;

import java.util.Objects;

public class SortingParameter {
    private SortingField sortingField;
    private SortingOrder sortingOrder;

    public SortingParameter(SortingField sortingField, SortingOrder sortingOrder) {
        this.sortingField = sortingField;
        this.sortingOrder = sortingOrder;
    }

    public String getSortingParameterForSQL(){
        return sortingField + " " + sortingOrder;
    }

    SortingField getSortingField() {
        return sortingField;
    }

    SortingOrder getSortingOrder() {
        return sortingOrder;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SortingParameter parameter = (SortingParameter) object;
        return sortingField == parameter.sortingField &&
                sortingOrder == parameter.sortingOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sortingField, sortingOrder);
    }
}
