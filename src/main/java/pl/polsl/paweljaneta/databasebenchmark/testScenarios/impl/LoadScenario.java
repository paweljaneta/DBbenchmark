package pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.DataCreator;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;

@Component
public class LoadScenario extends BaseScenario {


    private DataCreator dataCreator;

    @Autowired
    public LoadScenario(DataCreator dataCreator) {
        this.dataCreator = dataCreator;
    }

    @Override
    public void before() {

    }

    @Override
    public void execute() {
        dataCreator.createData();
    }

    @Override
    public void after() {

    }
}
