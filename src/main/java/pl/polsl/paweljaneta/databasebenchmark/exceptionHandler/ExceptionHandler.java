package pl.polsl.paweljaneta.databasebenchmark.exceptionHandler;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExceptionHandler {
    private List<ExceptionInIteration> exceptions = new ArrayList<>();

    public void addException(int iteration, Exception exception) {
        exceptions.add(new ExceptionInIteration(iteration, exception));
    }

    public List<ExceptionInIteration> getExceptions() {
        return exceptions;
    }
}
