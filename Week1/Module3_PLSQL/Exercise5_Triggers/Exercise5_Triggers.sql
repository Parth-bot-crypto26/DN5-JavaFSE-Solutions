-- ============================================================
-- Exercise5_Triggers.sql
-- Scenario 1: UpdateCustomerLastModified — auto-update timestamp
-- Scenario 2: LogTransaction — audit log on every insert
-- Scenario 3: CheckTransactionRules — enforce deposit/withdrawal rules
-- ============================================================

SET SERVEROUTPUT ON;

-- ------------------------------------------------------------
-- Scenario 1: UpdateCustomerLastModified
-- Fires BEFORE any UPDATE on Customers and refreshes LastModified
-- ------------------------------------------------------------
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    :NEW.LastModified := SYSDATE;
END;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: UpdateCustomerLastModified ---');
    UPDATE Customers SET Balance = Balance + 100 WHERE CustomerID = 1;
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer #1 updated. LastModified auto-set to now.');
END;
/

SELECT CustomerID, Name, LastModified FROM Customers WHERE CustomerID = 1;

-- ------------------------------------------------------------
-- Scenario 2: LogTransaction
-- Inserts a row into AuditLog every time a transaction is created
-- ------------------------------------------------------------
CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, TransactionType, LogDate)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.Amount, :NEW.TransactionType, SYSDATE);
END;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 2: LogTransaction ---');

    INSERT INTO Transactions VALUES (4, 1, SYSDATE, 500, 'Deposit');
    INSERT INTO Transactions VALUES (5, 2, SYSDATE, 250, 'Withdrawal');
    COMMIT;

    DBMS_OUTPUT.PUT_LINE('2 transactions inserted. Check AuditLog below:');
END;
/

SELECT * FROM AuditLog;

-- ------------------------------------------------------------
-- Scenario 3: CheckTransactionRules
-- Rejects negative deposits and withdrawals that exceed balance
-- ------------------------------------------------------------
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_balance NUMBER;
BEGIN
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Deposit amount must be positive.');
    END IF;

    IF :NEW.TransactionType = 'Withdrawal' THEN
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20003, 'Withdrawal amount must be positive.');
        END IF;

        SELECT Balance INTO v_balance
        FROM   Accounts
        WHERE  AccountID = :NEW.AccountID;

        IF :NEW.Amount > v_balance THEN
            RAISE_APPLICATION_ERROR(-20004,
                'Withdrawal of $' || :NEW.Amount
                || ' exceeds available balance of $' || v_balance
            );
        END IF;
    END IF;
END;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: CheckTransactionRules ---');

    -- valid deposit
    BEGIN
        INSERT INTO Transactions VALUES (6, 1, SYSDATE, 300, 'Deposit');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Valid deposit inserted successfully.');
    EXCEPTION WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Deposit error: ' || SQLERRM);
    END;

    -- invalid — withdrawal exceeds balance
    BEGIN
        INSERT INTO Transactions VALUES (7, 1, SYSDATE, 999999, 'Withdrawal');
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('This should not print.');
    EXCEPTION WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Withdrawal blocked: ' || SQLERRM);
    END;

    -- invalid — negative deposit
    BEGIN
        INSERT INTO Transactions VALUES (8, 1, SYSDATE, -100, 'Deposit');
        COMMIT;
    EXCEPTION WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Negative deposit blocked: ' || SQLERRM);
    END;
END;
/
