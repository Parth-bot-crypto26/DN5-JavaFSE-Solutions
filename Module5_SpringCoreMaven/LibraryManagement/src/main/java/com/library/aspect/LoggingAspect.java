package com.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Implementing Logging with Spring AOP
 *   -> logExecutionTime(): @Around advice that times every service method call.
 *
 * Exercise 8: Implementing Basic AOP with Spring
 *   -> logBefore()/logAfter(): simple before/after advice showing the two
 *      other common advice types, kept separate from the timing logic so
 *      each exercise's requirement is easy to point to.
 */
@Aspect
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    /** Exercise 3: log how long each com.library.service method takes. */
    @Around("execution(* com.library.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long elapsed = System.currentTimeMillis() - start;
        logger.info("[AOP] {} executed in {} ms", joinPoint.getSignature(), elapsed);
        return result;
    }

    /** Exercise 8: before advice - log method entry with arguments. */
    @Before("execution(* com.library.service..*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("[AOP] Entering {} with args {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    /** Exercise 8: after advice - log method exit (runs whether it returns or throws). */
    @After("execution(* com.library.service..*(..))")
    public void logAfter(JoinPoint joinPoint) {
        logger.info("[AOP] Exiting {}", joinPoint.getSignature());
    }
}
