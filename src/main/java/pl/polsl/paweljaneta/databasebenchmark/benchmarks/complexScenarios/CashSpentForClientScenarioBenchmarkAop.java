package pl.polsl.paweljaneta.databasebenchmark.benchmarks.complexScenarios;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.polsl.paweljaneta.databasebenchmark.benchmarks.AopLoggingBaseClass;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.ExecutionTimeLogger;
import pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.complexScenarios.CashSpentForClientScenario;

@Aspect
@Configuration
public class CashSpentForClientScenarioBenchmarkAop {
    private ExecutionTimeLogger executionTimeLogger;
    private AopLoggingBaseClass logger;

    @Autowired
    private CashSpentForClientScenario scenarioClass;

    @Autowired
    public CashSpentForClientScenarioBenchmarkAop(ExecutionTimeLogger executionTimeLogger, AopLoggingBaseClass logger) {
        this.executionTimeLogger = executionTimeLogger;
        this.executionTimeLogger.setFileName("CashSpentForClientScenarioBenchmark");
        this.logger = logger;
    }

    @Around("execution(* pl.polsl.paweljaneta.databasebenchmark.testScenarios.impl.timeMeasure.complexScenarios.CashSpentForClientScenarioMethods.*(..))&&@annotation(pl.polsl.paweljaneta.databasebenchmark.annotations.ExecTimeMeasure)")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        return logger.log(pjp, executionTimeLogger, scenarioClass.getIteration());
    }
}
