package pl.polsl.paweljaneta.databasebenchmark.benchmarks;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.polsl.paweljaneta.databasebenchmark.dataInsertion.utils.ExecutionTimeLogger;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope("prototype")
public class AopLoggingBaseClass {
    Logger logger = Logger.getLogger(this.getClass().getName());

    public Object log(ProceedingJoinPoint pjp, ExecutionTimeLogger executionTimeLogger, int iteration) throws Throwable {
        logger.log(Level.INFO, new Date() + " START: " + pjp.getTarget().getClass().getCanonicalName() + "." + pjp.getSignature().getName() + "() iteration: " + iteration);
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        executionTimeLogger.logExecutionTime(pjp.getSignature().getName(), (endTime - startTime), iteration);
        logger.log(Level.INFO, new Date() + " " + pjp.getTarget().getClass().getCanonicalName() + '.' + pjp.getSignature().getName() + "(): " + (endTime - startTime) + "ms iteration: " + iteration);
        return result;
    }
}
