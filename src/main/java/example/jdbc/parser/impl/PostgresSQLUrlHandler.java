package example.jdbc.parser.impl;

import example.jdbc.types.DatabaseType;

public class PostgresSQLUrlHandler extends CommonUrlHandler {
    @Override
    public DatabaseType getDatabaseType() {
        return DatabaseType.POSTGRESQL;
    }
}
