package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.AddProductToStoreScenarioMethods;

import java.util.Random;

@Component
public class AddProductToStoreScenario extends BaseScenario {
    private DataConfig dataConfig;
    private AddProductToStoreScenarioMethods addProductToStoreScenarioMethods;

    @Autowired
    public AddProductToStoreScenario(DataConfig dataConfig, AddProductToStoreScenarioMethods addProductToStoreScenarioMethods) {
        this.dataConfig = dataConfig;
        this.addProductToStoreScenarioMethods = addProductToStoreScenarioMethods;
    }

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        Long storeEntityId = getStoreEntityId();

        addProductToStoreScenarioMethods.sqlGetStoreByEntityId(storeEntityId);
        addProductToStoreScenarioMethods.mongoGetStoreByEntityId(storeEntityId);
        addProductToStoreScenarioMethods.neoGetStoreByEntityId(storeEntityId);

    }

    @Override
    public void after() {

    }

    private Long getStoreEntityId() {
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }
}
