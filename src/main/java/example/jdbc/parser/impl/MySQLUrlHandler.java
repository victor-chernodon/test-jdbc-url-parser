package example.jdbc.parser.impl;

import example.jdbc.types.DatabaseType;

public class MySQLUrlHandler extends CommonUrlHandler {
    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.MYSQL;
    }
}
