package ua.com.bpgdev.movieland.common;

import java.util.HashMap;
import java.util.Map;

public enum SortingOrder {
    ASC("ASC"),
    DESC("DESC");

    private String sortingOrder;
    private static Map<String, SortingOrder> sortingOrders = null;

    SortingOrder(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public static SortingOrder valueOfSortingOrder(String sortingOrder){
        synchronized(SortingOrder.class) {
            if (sortingOrders == null) {
                sortingOrders = new HashMap<>();
                for (SortingOrder value : values()) {
                    sortingOrders.put(value.sortingOrder, value);
                }
            }
        }

        SortingOrder result = sortingOrders.get(sortingOrder.toUpperCase().trim());
        if (result == null){
            throw new IllegalArgumentException("");
        }
        return result;
    }
}
