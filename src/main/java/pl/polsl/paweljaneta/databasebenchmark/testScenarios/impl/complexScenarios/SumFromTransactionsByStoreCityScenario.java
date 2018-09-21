package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoStore;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlStore;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlStoreRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionsByStoreCityScenarioMethods;

import java.util.List;
import java.util.Map;

@Component
public class SumFromTransactionsByStoreCityScenario extends BaseScenario {
    @Autowired
    private SumFromTransactionsByStoreCityScenarioMethods methods;
    @Autowired
    private SqlStoreRepository sqlStoreRepository;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;
    @Autowired
    private MongoStoreRepository mongoStoreRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;
    @Autowired
    private NeoStoreRepository neoStoreRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;

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

        System.out.println(cityMongoSumMap);
        System.out.println(citySqlSumMap);
        System.out.println(cityNeoSumMap);
    }

    @Override
    public void after() {

    }
}
