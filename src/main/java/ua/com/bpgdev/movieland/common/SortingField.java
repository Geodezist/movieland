package ua.com.bpgdev.movieland.common;

import java.util.HashMap;
import java.util.Map;

public enum SortingField {
    PRICE("PRICE"),
    RATING("RATING");

    private String sortingField;
    private static Map<String, SortingField> sortingFields = null;

    SortingField(String sortingField) {
        this.sortingField = sortingField;
    }

    public static SortingField valueOfSortingField(String sortingField){
        synchronized(SortingField.class) {
            if (sortingFields == null) {
                sortingFields = new HashMap<>();
                for (SortingField value : values()) {
                    sortingFields.put(value.sortingField, value);
                }
            }
        }

        SortingField result = sortingFields.get(sortingField.toUpperCase().trim());
        if (result == null){
            throw new IllegalArgumentException("");
        }
        return result;
    }

}
