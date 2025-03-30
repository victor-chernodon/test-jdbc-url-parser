package example.jdbc.parser;

import example.jdbc.valueobject.JDBCUrlInfo;

public interface JDBCUrlHandler {
    JDBCUrlInfo parse(String jdbcUrl);
}