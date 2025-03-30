package example.jdbc.parser.impl;

import example.jdbc.parser.JDBCUrlHandler;
import example.jdbc.types.DatabaseType;
import example.jdbc.utils.ParameterUtils;
import example.jdbc.valueobject.JDBCUrlInfo;

import java.util.HashMap;

import java.util.Map;

public class OracleUrlHandler implements JDBCUrlHandler {
    @Override
    public JDBCUrlInfo parse(String jdbcUrl) {
        // Remove the Oracle JDBC prefix
        String strippedUrl = jdbcUrl.replace("jdbc:oracle:thin:@", "");

        // Split parameters (if any)
        String[] urlParts = strippedUrl.split("\\?", 2);
        String baseUrl = urlParts[0];
        Map<String, String> queryParams = urlParts.length > 1 ? parseQueryParams(urlParts[1]) : new HashMap<>();

        var port = "1521";

        String[] mainParts = baseUrl.split(":");
        var host = mainParts[0];
        if (mainParts.length > 1) {
            port = mainParts[1];
        }
        var databaseName = mainParts.length > 2 ? mainParts[2] : "";

        return new JDBCUrlInfo(DatabaseType.ORACLE, host, port, databaseName, queryParams);
    }

    private Map<String, String> parseQueryParams(String queryParams) {
        return ParameterUtils.getParametersMap(queryParams, "&");
    }

}
