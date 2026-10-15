package bits.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {
    private DBConnection() { }
    private static final String DEFAULT_URL = "jdbc:mysql://localhost:3306/bits_inventory?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    public static Connection getConnection() throws SQLException {
        String url = System.getenv().getOrDefault("BITS_DB_URL", DEFAULT_URL);
        String user = System.getenv().getOrDefault("BITS_DB_USER", "root");
        String password = System.getenv().getOrDefault("BITS_DB_PASSWORD", "Your_password");
        return DriverManager.getConnection(url, user, password);
    }
}
