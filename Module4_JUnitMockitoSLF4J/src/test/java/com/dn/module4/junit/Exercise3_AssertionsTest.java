package com.dn.module4.junit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 3: Assertions in JUnit  ⭐ MANDATORY
 *
 * Scenario: You need to use different assertions in JUnit to validate
 * your test results.
 */
public class Exercise3_AssertionsTest {

    @Test
    void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);

        // Assert true
        assertTrue(5 > 3);

        // Assert false
        assertFalse(5 < 3);

        // Assert null
        assertNull(null);

        // Assert not null
        assertNotNull(new Object());

        // Assert arrays are equal, element by element
        assertArrayEquals(new int[] {1, 2, 3}, new int[] {1, 2, 3});

        // Assert same reference vs. assert equal value
        String a = "reused";
        String b = a;
        assertSame(a, b);

        // Assert all of a group of checks together, reporting every failure
        Calculator calculator = new Calculator();
        assertAll("calculator",
                () -> assertEquals(4, calculator.add(2, 2)),
                () -> assertEquals(0, calculator.subtract(2, 2)),
                () -> assertEquals(4, calculator.multiply(2, 2))
        );
    }
}
