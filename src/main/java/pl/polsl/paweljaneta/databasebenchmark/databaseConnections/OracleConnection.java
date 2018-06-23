package pl.polsl.paweljaneta.databasebenchmark.databaseConnections;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class OracleConnection { //TODO: NOT WORKING!!!
    private Connection connection = null;

    OracleConnection() {
        try {
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "admin");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
