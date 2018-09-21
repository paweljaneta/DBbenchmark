package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.InWhichShopClientMadeShoppingOftenScenarioMethods;

import java.util.List;
import java.util.Map;

@Component
public class InWhichShopClientMadeShoppingOftenScenario extends BaseScenario {
    @Autowired
    private InWhichShopClientMadeShoppingOftenScenarioMethods methods;

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        List<SqlClient> sqlClients = methods.getSqlClients();
        Map<SqlClient, SqlStore> mostPopularSqlStoreForClient = methods.getMostPopularSqlStoreForClient(sqlClients);

        List<MongoClient> mongoClients = methods.getMongoClients();
        Map<MongoClient, MongoStore> mostPopularMongoStoreForClient = methods.getMostPopularMongoStoreForClient(mongoClients);

        Iterable<NeoClient> neoClients = methods.getNeoClients();
        Map<NeoClient, NeoStore> mostPopularNeoStoreForClient = methods.getMostPopularNeoStoreForClient(neoClients);

        System.out.println(mostPopularSqlStoreForClient);
        System.out.println(mostPopularMongoStoreForClient);
        System.out.println(mostPopularNeoStoreForClient);
    }

    @Override
    public void after() {

    }
}
