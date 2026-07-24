package com.dn.module4.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Using Different Appenders
 *
 * Task: Write a Java application that demonstrates using different appenders
 * with SLF4J. The console and file appenders themselves are configured in
 * src/main/resources/logback.xml — this class just logs, and Logback routes
 * the output to every appender attached to the root logger.
 */
public class AppenderExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        logger.debug("Debug message - visible on console and in app.log");
        logger.info("Application started");
        logger.warn("Low disk space warning");
        logger.error("Unhandled exception in worker thread");

        System.out.println("Check app.log in the project root for the file-appender output.");
    }
}
