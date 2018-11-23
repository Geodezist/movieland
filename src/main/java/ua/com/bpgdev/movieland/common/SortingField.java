package ua.com.bpgdev.movieland.common;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SortingField {
    PRICE("PRICE"),
    RATING("RATING");

    private String sortingField;
    private static Map<String, SortingField> sortingFields = Stream.of(values()).
            collect(Collectors.toMap(SortingField::getSortingField, value -> value));

    SortingField(String sortingField) {
        this.sortingField = sortingField;
    }

    public static SortingField valueOfSortingField(String sortingField){
        SortingField result = sortingFields.get(sortingField.toUpperCase().trim());
        if (result == null){
            throw new IllegalArgumentException("Invalid value \"" + sortingField + "\" for enum " +
                    SortingField.class.getName());
        }
        return result;
    }

    public String getSortingField() {
        return sortingField;
    }
}
