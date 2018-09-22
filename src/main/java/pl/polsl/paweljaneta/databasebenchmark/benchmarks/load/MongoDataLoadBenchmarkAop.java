package pl.polsl.paweljaneta.databasebenchmark.benchmarks.load;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.polsl.paweljaneta.databasebenchmark.benchmarks.AopLoggingBaseClass;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.ExecutionTimeLogger;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.simpleScenarios.LoadScenario;

@Aspect
@Configuration
public class MongoDataLoadBenchmarkAop {

    private ExecutionTimeLogger executionTimeLogger;
    private AopLoggingBaseClass logger;

    @Autowired
    private LoadScenario scenarioClass;

    @Autowired
    public MongoDataLoadBenchmarkAop(ExecutionTimeLogger executionTimeLogger, AopLoggingBaseClass logger) {
        this.executionTimeLogger = executionTimeLogger;
        this.executionTimeLogger.setFileName("MongoDataLoadBenchmark");
        this.logger = logger;
    }

    @Around("execution(* pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.MongoDataInsertor.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return logger.log(pjp, executionTimeLogger, scenarioClass.getIteration());
    }
}
