package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.CashSpentForClientScenarioMethods;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class CashSpentForClientScenario extends BaseScenario {
    @Autowired
    private CashSpentForClientScenarioMethods methods;
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        List<SqlClient> sqlClients = methods.getSqlClients();
        Map<SqlClient, Float> sqlClientPays = methods.getSqlClientPays(sqlClients);

        List<MongoClient> mongoClients = methods.getMongoClients();
        Map<MongoClient, Float> mongoClientPays = methods.getMongoClientPays(mongoClients);

        Iterable<NeoClient> neoClients = methods.getNeoClients();
        Map<NeoClient, Float> neoClientPays = methods.getNeoClientPays(neoClients);

        logger.log(Level.INFO, sqlClientPays.toString());
        logger.log(Level.INFO, mongoClientPays.toString());
        logger.log(Level.INFO, neoClientPays.toString());
    }

    @Override
    public void after() {

    }
}
