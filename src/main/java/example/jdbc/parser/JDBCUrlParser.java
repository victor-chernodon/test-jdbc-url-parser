package example.jdbc.parser;

import example.jdbc.exception.UnknownUrlTypeException;
import example.jdbc.parser.impl.*;
import example.jdbc.types.DatabaseType;
import example.jdbc.valueobject.JDBCUrlInfo;

public class JDBCUrlParser {
    public static JDBCUrlInfo parseJDBCUrl(String jdbcUrl) {
        if (!jdbcUrl.startsWith("jdbc:")) {
            throw new UnknownUrlTypeException("Invalid JDBC URL");
        }

        DatabaseType dbType = DatabaseType.fromUrl(jdbcUrl);
        JDBCUrlHandler handler = getHandlerForType(dbType);
        return handler.parse(jdbcUrl);
    }

    private static JDBCUrlHandler getHandlerForType(DatabaseType dbType) {
        return switch (dbType) {
            case MYSQL -> new MySQLUrlHandler();
            case POSTGRESQL -> new PostgresSQLUrlHandler();
            case SQLSERVER -> new SQLServerUrlHandler();
            case ORACLE -> new OracleUrlHandler();
            case SQLITE -> new SQLiteUrlHandler();
        };
    }
}
