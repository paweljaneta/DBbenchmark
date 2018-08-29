package pl.polsl.paweljaneta.databasebenchmark.benchmarks;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class MongoDataLoadBenchmerkAop {
    @Around("execution(* pl.polsl.paweljaneta.databasebenchmark.dataInsertion.dataInsertors.impl.MongoDataInsertor.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println("mongo_"+pjp.getSignature().getName()+": "+(endTime-startTime)+"ms");

        return result;
    }
}