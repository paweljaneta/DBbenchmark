package pl.polsl.paweljaneta.databasebenchmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.DataCreator;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "pl.polsl.paweljaneta.databasebenchmark.model.sql")
@EnableMongoRepositories(basePackages = "pl.polsl.paweljaneta.databasebenchmark.model.mongo")
@EnableNeo4jRepositories(basePackages = "pl.polsl.paweljaneta.databasebenchmark.model.neo4j")
@EnableAspectJAutoProxy
public class DemoApplication {

    @Autowired
    static ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
