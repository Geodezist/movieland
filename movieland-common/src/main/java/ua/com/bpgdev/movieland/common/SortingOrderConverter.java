package ua.com.bpgdev.movieland.common;

import java.beans.PropertyEditorSupport;

public class SortingOrderConverter extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        SortingOrder sortingOrder = SortingOrder.valueOfSortingOrder(text);
        setValue(sortingOrder);
    }
}
