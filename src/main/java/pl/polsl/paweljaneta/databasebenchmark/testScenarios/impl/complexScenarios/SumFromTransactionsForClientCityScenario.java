package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoAddressRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionsForClientCityScenarioMethods;

import java.util.List;
import java.util.Map;

@Component
public class SumFromTransactionsForClientCityScenario extends BaseScenario {
    @Autowired
    private SumFromTransactionsForClientCityScenarioMethods methods;
    @Autowired
    private SqlClientRepository sqlClientRepository;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;

    @Autowired
    private MongoClientRepository mongoClientRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoAddressRepository mongoAddressRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;

    @Autowired
    private NeoClientRepository neoClientRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;

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

        System.out.println(citySqlSumMap);
        System.out.println(cityMongoSumMap);
        System.out.println(cityNeoSumMap);
    }

    @Override
    public void after() {

    }
}
