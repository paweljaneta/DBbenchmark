package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionsForClientCityScenarioMethods;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SumFromTransactionsForClientCityScenario extends BaseScenario {
    @Autowired
    private SumFromTransactionsForClientCityScenarioMethods methods;

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        List<SqlClient> sqlClients = methods.getAllSqlClients();
        Map<String, List<SqlClient>> citySqlClientsMap = methods.sqlGetAllClientCities(sqlClients);
        Map<String, Float> citySqlSumMap = methods.sqlCalculateSumForCity(citySqlClientsMap);

        List<MongoClient> mongoClients = methods.getAllMongoClients();
        Map<String, List<MongoClient>> cityMongoClientsMap = methods.mongoGetAllClientCities(mongoClients);
        Map<String, Float> cityMongoSumMap = methods.mongoCalculateSumForCity(cityMongoClientsMap);

        Iterable<NeoClient> neoClients = methods.getAllNeoClients();
        Map<String, List<NeoClient>> cityClientsMap = methods.neoGetAllClientCities(neoClients);
        Map<String, Float> cityNeoSumMap = methods.neoCalculateSumForCity(cityClientsMap);

        logger.log(Level.INFO, citySqlSumMap.toString());
        logger.log(Level.INFO, cityMongoSumMap.toString());
        logger.log(Level.INFO, cityNeoSumMap.toString());
    }

    @Override
    public void after() {

    }
}
