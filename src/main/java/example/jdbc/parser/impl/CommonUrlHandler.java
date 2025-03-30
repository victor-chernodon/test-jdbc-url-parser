package example.jdbc.parser.impl;

import example.jdbc.parser.JDBCUrlHandler;
import example.jdbc.types.DatabaseType;
import example.jdbc.utils.ParameterUtils;
import example.jdbc.valueobject.JDBCUrlInfo;

import java.util.Map;

public abstract class CommonUrlHandler implements JDBCUrlHandler {
    @Override
    public JDBCUrlInfo parse(String jdbcUrl) {
        String strippedUrl = jdbcUrl.substring(jdbcUrl.indexOf("//") + 2);
        String[] parts = strippedUrl.split("\\?", 2);
        String baseUrl = parts[0];
        Map<String, String> queryParams = parseQueryParams(parts.length > 1 ? parts[1] : "");

        String[] hostParts = baseUrl.split("/", 2);
        String hostPort = hostParts[0];
        String databaseName = hostParts.length > 1 ? hostParts[1] : "";

        String host = hostPort.contains(":") ? hostPort.split(":")[0] : hostPort;
        String port = hostPort.contains(":") ? hostPort.split(":")[1] : "default";

        return new JDBCUrlInfo(getDatabaseType(), host, port, databaseName, queryParams);
    }

    public abstract DatabaseType getDatabaseType();

    private Map<String, String> parseQueryParams(String queryParams) {
        return ParameterUtils.getParametersMap(queryParams, "&");
    }
}
