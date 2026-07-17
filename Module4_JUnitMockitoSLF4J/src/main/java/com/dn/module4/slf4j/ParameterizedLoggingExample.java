package com.dn.module4.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 2: Parameterized Logging
 *
 * Task: Write a Java application that demonstrates parameterized logging
 * using SLF4J. Parameterized logging avoids string concatenation and
 * skips message construction entirely when the log level is disabled.
 */
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String username = "jdoe";
        int loginAttempts = 3;

        // Single placeholder
        logger.info("User '{}' logged in successfully", username);

        // Multiple placeholders
        logger.warn("User '{}' has failed to log in {} times", username, loginAttempts);

        // Placeholder plus an exception (logged with a stack trace)
        try {
            throw new IllegalStateException("Session expired");
        } catch (IllegalStateException ex) {
            logger.error("Login failed for user '{}'", username, ex);
        }
    }
}
