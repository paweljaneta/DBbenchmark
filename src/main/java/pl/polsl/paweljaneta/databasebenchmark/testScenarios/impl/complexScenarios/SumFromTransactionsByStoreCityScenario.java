package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionsByStoreCityScenarioMethods;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SumFromTransactionsByStoreCityScenario extends BaseScenario {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private SumFromTransactionsByStoreCityScenarioMethods methods;

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        List<SqlStore> sqlStores = methods.sqlGetAllStores();
        Map<String, List<SqlStore>> citySqlStoresMap = methods.sqlGetAllStoreCities(sqlStores);
        Map<String, Float> citySqlSumMap = methods.sqlCalculateSumForCity(citySqlStoresMap);

        List<MongoStore> mongoStores = methods.mongoGetAllStores();
        Map<String, List<MongoStore>> cityMongoStoresMap = methods.mongoGetAllStoreCities(mongoStores);
        Map<String, Float> cityMongoSumMap = methods.mongoCalculateSumForCity(cityMongoStoresMap);

        List<NeoStore> neoStores = methods.neoGetAllStores();
        Map<String, List<NeoStore>> cityNeoStoresMap = methods.neoGetAllStoreCities(neoStores);
        Map<String, Float> cityNeoSumMap = methods.neoCalculateSumForCity(cityNeoStoresMap);

        logger.log(Level.INFO, cityMongoSumMap.toString());
        logger.log(Level.INFO, citySqlSumMap.toString());
        logger.log(Level.INFO, cityNeoSumMap.toString());
    }

    @Override
    public void after() {

    }
}
