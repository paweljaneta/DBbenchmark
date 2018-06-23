package pl.polsl.paweljaneta.databasebenchmark.databaseConnections;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class PostgreSQLConnection {

    private Connection connection = null;

    PostgreSQLConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:10000/benchmarkDB", "postgres", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
