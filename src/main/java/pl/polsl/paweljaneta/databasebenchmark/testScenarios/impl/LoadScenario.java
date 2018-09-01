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
       this.dataCreator=dataCreator;
    }

    @Override
    protected void before() {

    }

    @Override
    protected void execute() {
        for (int i = 0; i < NO_OF_REPEATS; i++) {
            dataCreator.createData();
        }
    }

    @Override
    protected void after() {

    }
}
