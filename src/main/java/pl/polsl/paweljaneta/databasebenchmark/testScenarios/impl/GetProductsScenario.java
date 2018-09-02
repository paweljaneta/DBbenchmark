package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataConfig.DataConfig;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;

import java.util.Random;

@Component
public class GetProductsScenario extends BaseScenario {
    @Autowired
    DataConfig dataConfig;

    @Override
    protected void before() {

    }

    @Override
    protected void execute() {

    }

    @Override
    protected void after() {

    }

    private Long getStoreEntityId(){
        Random random = new Random();
        return Long.valueOf(random.nextInt(dataConfig.NO_OF_STORES));
    }
}
