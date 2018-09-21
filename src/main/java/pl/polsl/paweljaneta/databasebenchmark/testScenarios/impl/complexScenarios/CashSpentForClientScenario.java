package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.entities.MongoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.entities.NeoClient;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.entities.SqlClient;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlClientRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.CashSpentForClientScenarioMethods;

import java.util.List;
import java.util.Map;

@Component
public class CashSpentForClientScenario extends BaseScenario {
    @Autowired
    private CashSpentForClientScenarioMethods methods;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlClientRepository sqlClientRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoClientRepository mongoClientRepository;
    @Autowired
    private MongoProductRepository mongoProductRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;
    @Autowired
    private NeoClientRepository neoClientRepository;
    @Autowired
    private NeoProductRepository neoProductRepository;

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

        System.out.println(sqlClientPays);
        System.out.println(mongoClientPays);
        System.out.println(neoClientPays);
    }

    @Override
    public void after() {

    }
}
