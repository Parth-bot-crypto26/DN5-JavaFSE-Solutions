package com.dn.module4.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 1: Logging Error Messages and Warning Levels  ⭐ MANDATORY
 *
 * Task: Write a Java application that demonstrates logging error messages
 * and warning levels using SLF4J.
 *
 * Run:  mvn compile exec:java -Dexec.mainClass=com.dn.module4.slf4j.LoggingExample
 * (or just run the class directly from your IDE)
 */
public class LoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {
        logger.trace("This is a trace message");
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");
    }
}
