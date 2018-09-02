package pl.polsl.paweljaneta.databasebenchmark.benchmarks;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.ExecutionTimeLogger;

@Aspect
@Configuration
public class NeoDatabaseCleanBenchmarkAop {
    private ExecutionTimeLogger executionTimeLogger;

    @Autowired
    public NeoDatabaseCleanBenchmarkAop(ExecutionTimeLogger executionTimeLogger) {
        this.executionTimeLogger = executionTimeLogger;
        this.executionTimeLogger.setFileName("NeoDatabaseCleanBenchmark");
    }

    @Around("execution(* pl.polsl.paweljaneta.databasebenchmark.model.neo4j.repository.*.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        executionTimeLogger.logExecutionTime(pjp.getTarget().getClass().getName()+pjp.getSignature().getName(), (endTime - startTime));
        return result;
    }
}
