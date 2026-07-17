package com.dn.module4.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercise 4: Arrange-Act-Assert (AAA) Pattern, Test Fixtures,
 * Setup and Teardown Methods in JUnit  ⭐ MANDATORY
 *
 * Scenario: You need to organize your tests using the Arrange-Act-Assert
 * (AAA) pattern and use setup/teardown methods so each test starts from
 * a clean, predictable fixture.
 */
public class Exercise4_AAAPatternTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        // Fixture: a fresh account with a known opening balance before every test
        account = new BankAccount(100.0);
        System.out.println("[setUp] Fresh BankAccount created with balance 100.0");
    }

    @AfterEach
    void tearDown() {
        // Teardown: release/reset resources after every test
        account = null;
        System.out.println("[tearDown] BankAccount reference cleared");
    }

    @Test
    void depositIncreasesBalance() {
        // Arrange
        double depositAmount = 50.0;

        // Act
        account.deposit(depositAmount);

        // Assert
        assertEquals(150.0, account.getBalance());
    }

    @Test
    void withdrawDecreasesBalance() {
        // Arrange
        double withdrawAmount = 30.0;

        // Act
        account.withdraw(withdrawAmount);

        // Assert
        assertEquals(70.0, account.getBalance());
    }

    @Test
    void withdrawMoreThanBalanceThrows() {
        // Arrange
        double withdrawAmount = 500.0;

        // Act + Assert
        assertThrows(IllegalStateException.class, () -> account.withdraw(withdrawAmount));
    }
}
