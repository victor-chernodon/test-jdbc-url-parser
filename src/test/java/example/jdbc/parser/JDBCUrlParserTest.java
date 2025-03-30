package example.jdbc.parser;

import static org.junit.jupiter.api.Assertions.*;

import example.jdbc.exception.UnknownDatabaseTypeException;
import example.jdbc.exception.UnknownUrlTypeException;
import example.jdbc.types.DatabaseType;
import example.jdbc.valueobject.JDBCUrlInfo;
import org.junit.jupiter.api.Test;

class JDBCUrlParserTest {

    @Test
    void testMySQLUrl() {
        String url = "jdbc:mysql://localhost:3306/testdb?user=root&password=pass";
        JDBCUrlInfo info = JDBCUrlParser.parseJDBCUrl(url);

        assertEquals(DatabaseType.MYSQL, info.databaseType());
        assertEquals("localhost", info.host());
        assertEquals("3306", info.port());
        assertEquals("testdb", info.databaseName());

        var jdbcInfoParams = info.queryParams();
        assertNotNull(jdbcInfoParams);
        assertEquals("root", jdbcInfoParams.get("user"));
        assertEquals("pass", jdbcInfoParams.get("password"));
    }

    @Test
    void testPostgreSQLUrl() {
        String url = "jdbc:postgresql://db.example.com:5432/testdb?ssl=true&user=admin&password=secret";
        JDBCUrlInfo info = JDBCUrlParser.parseJDBCUrl(url);

        assertEquals(DatabaseType.POSTGRESQL, info.databaseType());
        assertEquals("db.example.com", info.host());
        assertEquals("5432", info.port());
        assertEquals("testdb", info.databaseName());

        var jdbcInfoParams = info.queryParams();
        assertNotNull(jdbcInfoParams);
        assertEquals("true", jdbcInfoParams.get("ssl"));
        assertEquals("admin", jdbcInfoParams.get("user"));
        assertEquals("secret", jdbcInfoParams.get("password"));
    }

    @Test
    void testSQLServerUrl() {
        String url = "jdbc:sqlserver://server:1433;databaseName=mydb;user=sa;password=pass";
        JDBCUrlInfo info = JDBCUrlParser.parseJDBCUrl(url);

        assertEquals(DatabaseType.SQLSERVER, info.databaseType());
        assertEquals("server", info.host());
        assertEquals("1433", info.port());
        assertEquals("mydb", info.databaseName());

        var jdbcInfoParams = info.queryParams();
        assertNotNull(jdbcInfoParams);
        assertEquals("sa", jdbcInfoParams.get("user"));
        assertEquals("pass", jdbcInfoParams.get("password"));
    }

    @Test
    void testOracleUrl() {
        String url = "jdbc:oracle:thin:@dbhost:1521:orcl?user=myuser&password=mypass&oracle.jdbc.ReadTimeout=30000";
        JDBCUrlInfo info = JDBCUrlParser.parseJDBCUrl(url);

        assertEquals(DatabaseType.ORACLE, info.databaseType());
        assertEquals("dbhost", info.host());
        assertEquals("1521", info.port());
        assertEquals("orcl", info.databaseName());

        var jdbcInfoParams = info.queryParams();
        assertNotNull(jdbcInfoParams);
        assertEquals("myuser", jdbcInfoParams.get("user"));
        assertEquals("mypass", jdbcInfoParams.get("password"));
        assertEquals("30000", jdbcInfoParams.get("oracle.jdbc.ReadTimeout"));
    }

    @Test
    void testSQLiteUrl() {
        String url = "jdbc:sqlite:/data/testdatabase.db?cache=shared&mode=ro";
        JDBCUrlInfo info = JDBCUrlParser.parseJDBCUrl(url);

        assertEquals(DatabaseType.SQLITE, info.databaseType());
        assertEquals("/data/testdatabase.db", info.databaseName());

        var jdbcInfoParams = info.queryParams();
        assertNotNull(jdbcInfoParams);
        assertEquals("shared", jdbcInfoParams.get("cache"));
        assertEquals("ro", jdbcInfoParams.get("mode"));
    }

    @Test
    void testInvalidUrl() {
        String url = "invalid:url";
        Exception exception = assertThrows(UnknownUrlTypeException.class, () -> {
            JDBCUrlParser.parseJDBCUrl(url);
        });

        assertEquals("Invalid JDBC URL", exception.getMessage());
    }

    @Test
    void testInvalidDbType() {
        String url = "jdbc:invaliddbtype:type:testdatabase.db";
        Exception exception = assertThrows(UnknownDatabaseTypeException.class, () -> {
            JDBCUrlParser.parseJDBCUrl(url);
        });

        assertEquals("Unsupported database type in URL", exception.getMessage());
    }
}