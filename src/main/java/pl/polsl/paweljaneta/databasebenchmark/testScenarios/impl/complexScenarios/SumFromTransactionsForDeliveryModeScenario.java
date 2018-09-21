package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionsForDeliveryModeScenarioMethods;

import java.util.Map;

@Component
public class SumFromTransactionsForDeliveryModeScenario extends BaseScenario {
    @Autowired
    private SumFromTransactionsForDeliveryModeScenarioMethods methods;

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
