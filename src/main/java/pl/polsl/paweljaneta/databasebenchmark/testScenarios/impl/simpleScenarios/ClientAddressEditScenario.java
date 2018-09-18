package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.simpleScenarios.ClientAddressEditScenarioMethods;

import java.util.Random;

@Component
public class ClientAddressEditScenario extends BaseScenario {
    @Autowired
    private ClientAddressEditScenarioMethods clientAddressEditScenarioMethods;
    @Autowired
    private DataConfig dataConfig;

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        Random random = new Random();
        Long entityId = Long.valueOf(random.nextInt(dataConfig.NO_OF_CLIENTS));
        clientAddressEditScenarioMethods.changeSqlClientAddressData(entityId);
        clientAddressEditScenarioMethods.changeMongoClientAddressData(entityId);
        clientAddressEditScenarioMethods.changeNeoClientAddressData(entityId);
    }

    @Override
    public void after() {

    }
}
