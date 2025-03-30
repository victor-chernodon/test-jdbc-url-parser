package example.jdbc.types;

import example.jdbc.exception.UnknownDatabaseTypeException;

public enum DatabaseType {
    MYSQL("mysql"),
    POSTGRESQL("postgresql"),
    SQLSERVER("sqlserver"),
    ORACLE("oracle"),
    SQLITE("sqlite");

    private final String identifier;

    DatabaseType(String identifier) {
        this.identifier = identifier;
    }

    public String getIdentifier() {
        return identifier;
    }

    public static DatabaseType fromUrl(String url) {
        for (DatabaseType type : values()) {
            if (url.startsWith("jdbc:" + type.identifier)) {
                return type;
            }
        }
        throw new UnknownDatabaseTypeException("Unsupported database type in URL");
    }
}
