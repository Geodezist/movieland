package ua.com.bpgdev.movieland.common;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SortingOrder {
    ASC("ASC"),
    DESC("DESC");

    private String sortingOrder;
    private static Map<String, SortingOrder> sortingOrders = Stream.of(values()).
            collect(Collectors.toMap(SortingOrder::getSortingOrder, value -> value));

    SortingOrder(String sortingOrder) {
        this.sortingOrder = sortingOrder;
    }

    public static SortingOrder valueOfSortingOrder(String sortingOrder){
        SortingOrder result = sortingOrders.get(sortingOrder.toUpperCase().trim());
        if (result == null){
            throw new IllegalArgumentException("Invalid value \"" + sortingOrder + "\" for enum " +
                    SortingOrder.class.getName());
        }
        return result;
    }

    public String getSortingOrder() {
        return sortingOrder;
    }
}
