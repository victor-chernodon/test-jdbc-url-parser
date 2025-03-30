package example.jdbc.valueobject;

import example.jdbc.types.DatabaseType;

import java.util.Map;

public record JDBCUrlInfo(DatabaseType databaseType, String host, String port, String databaseName,
                          Map<String, String> queryParams) {

    @Override
    public String toString() {
        return "DatabaseConnectionInfo{" +
                "databaseType=" + databaseType +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", databaseName='" + databaseName + '\'' +
                ", queryParams=" + queryParams +
                '}';
    }
}
