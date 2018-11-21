package ua.com.bpgdev.movieland.common;

import java.util.HashMap;
import java.util.Map;

public class ParameterInfo {
    private Map<SortingField, SortingOrder> parameters = new HashMap<>();

    public Map<SortingField, SortingOrder> getParameters() {
        return parameters;
    }
}
