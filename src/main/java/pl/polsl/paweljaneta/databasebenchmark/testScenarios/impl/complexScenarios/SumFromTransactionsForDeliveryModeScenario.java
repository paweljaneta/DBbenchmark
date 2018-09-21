package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoDiscountRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.mongo.repository.MongoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.NeoTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlProductRepository;
import pl.polsl.paweljaneta.databasebenchmark.model.sql.repository.SqlTransactionRepository;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionsForDeliveryModeScenarioMethods;

import java.util.Map;

@Component
public class SumFromTransactionsForDeliveryModeScenario extends BaseScenario {
    @Autowired
    private SumFromTransactionsForDeliveryModeScenarioMethods methods;
    @Autowired
    private SqlTransactionRepository sqlTransactionRepository;
    @Autowired
    private SqlProductRepository sqlProductRepository;
    @Autowired
    private MongoTransactionRepository mongoTransactionRepository;
    @Autowired
    private MongoDiscountRepository mongoDiscountRepository;
    @Autowired
    private NeoTransactionRepository neoTransactionRepository;

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        Map<DeliveryMode, Float> deliveryModeSqlSumMap = methods.sqlGetTransactionsSumForDeliveryMode();
        Map<DeliveryMode, Float> deliveryModeMongoSumMap = methods.mongoGetTransactionsSumForDeliveryMode();
        Map<DeliveryMode, Float> deliveryModeNeoSumMap = methods.neoGetTransactionsSumForDeliveryMode();

        System.out.println(deliveryModeSqlSumMap);
        System.out.println(deliveryModeMongoSumMap);
        System.out.println(deliveryModeNeoSumMap);
    }

    @Override
    public void after() {

    }
}
