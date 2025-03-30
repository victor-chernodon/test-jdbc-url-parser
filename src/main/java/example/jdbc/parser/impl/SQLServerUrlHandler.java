package example.jdbc.parser.impl;

import example.jdbc.parser.JDBCUrlHandler;
import example.jdbc.types.DatabaseType;
import example.jdbc.valueobject.JDBCUrlInfo;

import java.util.HashMap;
import java.util.Map;

public class SQLServerUrlHandler implements JDBCUrlHandler {
    @Override
    public JDBCUrlInfo parse(String jdbcUrl) {
        String strippedUrl = jdbcUrl.replace("jdbc:sqlserver://", "");
        String[] parts = strippedUrl.split(";", 2);

        String hostPort = parts[0];
        Map<String, String> queryParams = parseQueryParams(parts.length > 1 ? parts[1] : "");

        String[] hostPortSplit = hostPort.split(":");
        String host = hostPortSplit[0];
        String port = hostPortSplit.length > 1 ? hostPortSplit[1] : "1433";

        String databaseName = queryParams.remove("databaseName");

        return new JDBCUrlInfo(DatabaseType.SQLSERVER, host, port, databaseName, queryParams);
    }

    private Map<String, String> parseQueryParams(String params) {
        Map<String, String> queryMap = new HashMap<>();
        for (String param : params.split(";")) {
            String[] pair = param.split("=");
            if (pair.length == 2) {
                queryMap.put(pair[0], pair[1]);
            }
        }
        return queryMap;
    }
}
