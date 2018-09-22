package pl.polsl.paweljaneta.databasebenchmark.testScenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.exceptionHandler.ExceptionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public abstract class BaseScenario {
    @Autowired
    private ExceptionHandler exceptionHandler;

    Logger logger = Logger.getLogger(this.getClass().getName());
    private int NO_OF_REPEATS = 1;
    private int iteration;

    public void executeScenario() {

        before();
        for (iteration = 0; iteration <= NO_OF_REPEATS; iteration++) {
            try {
                logger.log(Level.INFO, "--------------------------------------------------------------------------------------------");
                logger.log(Level.INFO, "Scenario: " + this.getClass().getCanonicalName() + " iteration " + iteration + " of " + NO_OF_REPEATS + " start");
                execute();
                logger.log(Level.INFO, "Scenario: " + this.getClass().getCanonicalName() + " iteration " + iteration + " of " + NO_OF_REPEATS + " finish");
                logger.log(Level.INFO, "--------------------------------------------------------------------------------------------");
            } catch (Exception e) {
                exceptionHandler.addException(iteration, e);
            }
        }
        after();

    }

    public abstract void before();

    public abstract void execute();

    public abstract void after();

    public void setNoOfRepeats(int noOfRepeats) {
        NO_OF_REPEATS = noOfRepeats;
    }

    public int getIteration() {
        return iteration;
    }
}
