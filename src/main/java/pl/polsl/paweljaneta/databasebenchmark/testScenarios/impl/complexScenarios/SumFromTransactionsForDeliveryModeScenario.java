package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.model.DeliveryMode;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.SumFromTransactionsForDeliveryModeScenarioMethods;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class SumFromTransactionsForDeliveryModeScenario extends BaseScenario {
    Logger logger = Logger.getLogger(this.getClass().getName());

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

        logger.log(Level.INFO, deliveryModeSqlSumMap.toString());
        logger.log(Level.INFO, deliveryModeMongoSumMap.toString());
        logger.log(Level.INFO, deliveryModeNeoSumMap.toString());
    }

    @Override
    public void after() {

    }
}
