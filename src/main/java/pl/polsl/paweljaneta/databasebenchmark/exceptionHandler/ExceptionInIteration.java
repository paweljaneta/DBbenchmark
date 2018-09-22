package pl.polsl.paweljaneta.databasebenchmark.exceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionInIteration {
    private int iteration;
    private Exception exception;

    @Override
    public String toString() {
        return "Exception in iteration: " + iteration + System.lineSeparator() + exception + System.lineSeparator();
    }
}
