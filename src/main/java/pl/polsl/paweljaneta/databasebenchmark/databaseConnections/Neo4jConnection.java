package pl.polsl.paweljaneta.databasebenchmark.databaseConnections;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class Neo4jConnection {
    private Connection connection;

    public Neo4jConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:neo4j:bolt://localhost","neo4j","admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
