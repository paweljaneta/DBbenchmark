package pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Scope("prototype")
public class ExecutionTimeLogger {
    private PrintWriter printWriter;
    private String separator = ";";

    public void setFileName(String fileName) {
        try {
            DateFormat df = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
            String date = df.format(new Date());
            FileWriter fileWriter = new FileWriter("benchmarkResults\\" + date + fileName + ".csv");
            printWriter = new PrintWriter(fileWriter, true);
            printWriter.println("Time" + separator + "method" + separator + "exec time" + separator + "iteration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logExecutionTime(String methodName, Long durationMs, int iteration) {
        printWriter.println(new Date().getTime() + separator + methodName + separator + durationMs + separator + iteration);
    }
}
