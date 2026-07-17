package com.dn.module4.junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 1: Setting Up JUnit
 *
 * Scenario: You need to set up JUnit in your Java project to start
 * writing unit tests. This class proves the setup works end-to-end
 * (dependency on the classpath + a test runner picking it up).
 *
 * Run:  mvn -Dtest=Exercise1_SettingUpJUnitTest test
 */
public class Exercise1_SettingUpJUnitTest {

    @Test
    @DisplayName("JUnit is correctly configured and can run a trivial test")
    void junitIsConfigured() {
        Calculator calculator = new Calculator();
        assertEquals(4, calculator.add(2, 2), "2 + 2 should equal 4");
    }
}
