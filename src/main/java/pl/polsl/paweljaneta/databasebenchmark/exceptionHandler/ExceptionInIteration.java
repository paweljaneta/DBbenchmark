package pl.polsl.paweljaneta.databasebenchmark.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionInIteration {
    private int iteration;
    private Exception exception;

    @Override
    public String toString() {
        return "Exception in iteration: " + iteration + System.lineSeparator() + ExceptionUtils.getStackTrace(exception) + System.lineSeparator();
    }
}
