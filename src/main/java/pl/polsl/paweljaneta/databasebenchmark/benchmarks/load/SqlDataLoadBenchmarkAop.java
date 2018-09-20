package pl.polsl.paweljaneta.databasebenchmark.benchmarks.load;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.ExecutionTimeLogger;

import java.util.Date;

@Aspect
@Configuration
public class SqlDataLoadBenchmarkAop {

    private ExecutionTimeLogger executionTimeLogger;

    @Autowired
    public SqlDataLoadBenchmarkAop(ExecutionTimeLogger executionTimeLogger) {
        this.executionTimeLogger = executionTimeLogger;
        this.executionTimeLogger.setFileName("SqlDataLoadBenchmark");
    }

    @Around("execution(* pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.SqlDataInsertor.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println(new Date() + " START: " + pjp.getTarget().getClass().getCanonicalName() + "." + pjp.getSignature().getName() + "()");
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        executionTimeLogger.logExecutionTime(pjp.getSignature().getName(), (endTime - startTime));
        System.out.println(new Date() + " " + pjp.getTarget().getClass().getCanonicalName() + '.' + pjp.getSignature().getName() + "(): " + (endTime - startTime) + "ms");
        return result;
    }
}