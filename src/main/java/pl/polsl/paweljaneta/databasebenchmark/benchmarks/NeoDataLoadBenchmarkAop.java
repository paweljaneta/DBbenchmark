package pl.polsl.paweljaneta.databasebenchmark.benchmarks;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.ExecutionTimeLogger;

@Aspect
@Configuration
public class NeoDataLoadBenchmarkAop {

    private ExecutionTimeLogger executionTimeLogger;

    @Autowired
    public NeoDataLoadBenchmarkAop(ExecutionTimeLogger executionTimeLogger) {
        this.executionTimeLogger = executionTimeLogger;
        this.executionTimeLogger.setFileName("NeoDataLoadBenchmark");
    }

    @Around("execution(* pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.NeoDataInsertor.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("START: "+pjp.getTarget().getClass().getCanonicalName());
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        executionTimeLogger.logExecutionTime(pjp.getSignature().getName(), (endTime - startTime));
        System.out.println(pjp.getTarget().getClass().getCanonicalName()+'.'+pjp.getSignature().getName() + ": " + (endTime - startTime) + "ms");
        return result;
    }
}
