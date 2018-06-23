package pl.polsl.paweljaneta.databasebenchmark.app;

import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.polsl.paweljaneta.databasebenchmark.databaseConnections.PostgreSQLConnection;

@SpringBootApplication(scanBasePackages = "pl.polsl.paweljaneta.databasebenchmark")
public class DemoApplication {
    @Autowired
    static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
