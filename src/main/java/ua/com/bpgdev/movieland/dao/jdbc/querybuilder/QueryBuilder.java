package ua.com.bpgdev.movieland.dao.jdbc.querybuilder;

import ua.com.bpgdev.movieland.common.RequestParameters;

public interface QueryBuilder {
    String build(String queryTemplate, RequestParameters requestParameters);
}
