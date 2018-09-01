package pl.polsl.paweljaneta.databasebenchmark.testScenarios;

public abstract class BaseScenario {
    protected int NO_OF_REPEATS=1;

    public void executeScenario() {
        before();
        execute();
        after();
    }

    protected abstract void before();

    protected abstract void execute();

    protected abstract void after();

    public void setNoOfRepeats(int noOfRepeats) {
        NO_OF_REPEATS = noOfRepeats;
    }
}
