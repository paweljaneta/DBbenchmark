package pl.polsl.paweljaneta.databasebenchmark.testRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.BaseScenario;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.LoadScenario;

import java.util.ArrayList;
import java.util.List;

@Component
public class TestRunner {

    private List<BaseScenario> scenarios = new ArrayList<>();

    @Autowired
    TestRunner(LoadScenario loadScenario){
        loadScenario.setNoOfRepeats(100);
        scenarios.add(loadScenario);

    }

    @EventListener(ApplicationReadyEvent.class)
    public void runTests(){
        for (BaseScenario scenario : scenarios) {
            scenario.executeScenario();
        }
    }
}
