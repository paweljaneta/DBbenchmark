package pl.polsl.paweljaneta.databasebenchmark.testScenarios;

import org.springframework.stereotype.Component;

@Component
public abstract class BaseScenario {
    private int NO_OF_REPEATS = 1;

    public void executeScenario() {
        before();
        for (int i = 0; i < NO_OF_REPEATS; i++) {
            logger.log(Level.INFO, "--------------------------------------------------------------------------------------------");
            logger.log(Level.INFO, "Scenario: " + this.getClass().getCanonicalName() + " iteration " + (i + 1) + " of " + NO_OF_REPEATS + " start");
            execute();
            logger.log(Level.INFO, "Scenario: " + this.getClass().getCanonicalName() + " iteration " + (i + 1) + " of " + NO_OF_REPEATS + " finish");
            logger.log(Level.INFO, "--------------------------------------------------------------------------------------------");
        }
        after();
    }

    public abstract void before();

    public abstract void execute();

    public abstract void after();

    public void setNoOfRepeats(int noOfRepeats) {
        NO_OF_REPEATS = noOfRepeats;
    }
}
