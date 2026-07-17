package com.dn.module4.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercise 2: Writing Basic JUnit Tests
 *
 * Scenario: You need to write basic JUnit tests for a simple Java class.
 */
public class Exercise2_BasicTestsTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd() {
        assertEquals(7, calculator.add(3, 4));
    }

    @Test
    void testSubtract() {
        assertEquals(-1, calculator.subtract(3, 4));
    }

    @Test
    void testMultiply() {
        assertEquals(12, calculator.multiply(3, 4));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(8, 4));
    }

    @Test
    void testDivideByZeroThrows() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(8, 0));
    }
}
