package example.jdbc.parser.impl;

import example.jdbc.parser.JDBCUrlHandler;
import example.jdbc.types.DatabaseType;
import example.jdbc.utils.ParameterUtils;
import example.jdbc.valueobject.JDBCUrlInfo;

import java.util.HashMap;
import java.util.Map;

public class SQLiteUrlHandler implements JDBCUrlHandler {
    @Override
    public JDBCUrlInfo parse(String jdbcUrl) {
        // Remove the SQLite JDBC prefix
        String strippedUrl = jdbcUrl.replace("jdbc:sqlite:", "");

        // Split parameters (if any)
        String[] urlParts = strippedUrl.split("\\?", 2);
        String databasePath = urlParts[0];
        Map<String, String> queryParams = urlParts.length > 1 ? parseQueryParams(urlParts[1]) : new HashMap<>();

        return new JDBCUrlInfo(DatabaseType.SQLITE, null, null, databasePath, queryParams);
    }

    private Map<String, String> parseQueryParams(String queryParams) {
        return ParameterUtils.getParametersMap(queryParams, "&");
    }
}
