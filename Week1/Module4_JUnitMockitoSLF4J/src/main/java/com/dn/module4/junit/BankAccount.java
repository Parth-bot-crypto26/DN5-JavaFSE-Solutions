package com.dn.module4.junit;

/**
 * Used for Exercise 4 - Arrange-Act-Assert pattern, fixtures,
 * @BeforeEach / @AfterEach setup and teardown.
 */
public class BankAccount {

    private double balance;

    public BankAccount(double openingBalance) {
        this.balance = openingBalance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount > balance) {
            throw new IllegalStateException("Insufficient funds");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}
