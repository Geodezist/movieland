package ua.com.bpgdev.movieland.dao.jdbc.querybuilder;

import ua.com.bpgdev.movieland.common.RequestParameters;

public class MovieQueryBuilder implements QueryBuilder {
    @Override
    public String build(String queryTemplate, RequestParameters requestParameters) {
        return queryTemplate + " ORDER BY " +
                requestParameters.getSortingParameter().getSortingParameterForSQL() +
                ", id ASC";
    }
}
