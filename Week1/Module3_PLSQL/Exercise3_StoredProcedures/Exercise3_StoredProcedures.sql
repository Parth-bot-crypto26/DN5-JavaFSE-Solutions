-- ============================================================
-- Exercise3_StoredProcedures.sql  (MANDATORY)
-- Scenario 1: ProcessMonthlyInterest — add 1% to all savings accounts
-- Scenario 2: UpdateEmployeeBonus — add bonus % to a department
-- Scenario 3: TransferFunds — transfer between two accounts safely
-- ============================================================

SET SERVEROUTPUT ON;

-- ------------------------------------------------------------
-- Scenario 1: ProcessMonthlyInterest
-- Adds 1% interest to every savings account balance
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_updated NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Processing monthly interest for savings accounts ---');

    FOR rec IN (
        SELECT AccountID, Balance
        FROM   Accounts
        WHERE  AccountType = 'Savings'
    ) LOOP
        UPDATE Accounts
        SET    Balance       = Balance * 1.01,
               LastModified  = SYSDATE
        WHERE  AccountID = rec.AccountID;

        DBMS_OUTPUT.PUT_LINE(
            'Account #' || rec.AccountID
            || ': $' || rec.Balance
            || ' → $' || ROUND(rec.Balance * 1.01, 2)
        );
        v_updated := v_updated + 1;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(v_updated || ' savings account(s) updated.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error processing interest: ' || SQLERRM);
END ProcessMonthlyInterest;
/

-- run it
BEGIN
    ProcessMonthlyInterest;
END;
/

-- ------------------------------------------------------------
-- Scenario 2: UpdateEmployeeBonus
-- Adds a bonus % to salaries of everyone in a given department
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus (
    p_department   IN VARCHAR2,
    p_bonus_pct    IN NUMBER
) AS
    v_updated NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE(
        '--- Applying ' || p_bonus_pct || '% bonus to ' || p_department || ' dept ---'
    );

    FOR rec IN (
        SELECT EmployeeID, Name, Salary
        FROM   Employees
        WHERE  Department = p_department
    ) LOOP
        UPDATE Employees
        SET    Salary = Salary + (Salary * p_bonus_pct / 100)
        WHERE  EmployeeID = rec.EmployeeID;

        DBMS_OUTPUT.PUT_LINE(
            rec.Name
            || ': $' || rec.Salary
            || ' → $' || ROUND(rec.Salary * (1 + p_bonus_pct / 100), 2)
        );
        v_updated := v_updated + 1;
    END LOOP;

    IF v_updated = 0 THEN
        DBMS_OUTPUT.PUT_LINE('No employees found in department: ' || p_department);
    ELSE
        COMMIT;
        DBMS_OUTPUT.PUT_LINE(v_updated || ' employee(s) updated.');
    END IF;

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating bonuses: ' || SQLERRM);
END UpdateEmployeeBonus;
/

-- give IT dept a 15% bonus
BEGIN
    UpdateEmployeeBonus('IT', 15);
END;
/

-- give HR dept a 10% bonus
BEGIN
    UpdateEmployeeBonus('HR', 10);
END;
/

-- ------------------------------------------------------------
-- Scenario 3: TransferFunds
-- Moves money between two accounts after checking balance
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE TransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_from_balance NUMBER;
    v_to_exists    NUMBER;
BEGIN
    -- make sure amount is positive
    IF p_amount <= 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Transfer amount must be greater than zero.');
        RETURN;
    END IF;

    -- check source account balance
    SELECT Balance INTO v_from_balance
    FROM   Accounts
    WHERE  AccountID = p_from_account
    FOR UPDATE;

    IF v_from_balance < p_amount THEN
        DBMS_OUTPUT.PUT_LINE(
            'Transfer denied. Account #' || p_from_account
            || ' has $' || v_from_balance
            || ' but $' || p_amount || ' was requested.'
        );
        RETURN;
    END IF;

    -- confirm destination account exists
    SELECT COUNT(*) INTO v_to_exists
    FROM   Accounts WHERE AccountID = p_to_account;

    IF v_to_exists = 0 THEN
        DBMS_OUTPUT.PUT_LINE('Error: Destination account #' || p_to_account || ' not found.');
        RETURN;
    END IF;

    -- do the transfer
    UPDATE Accounts
    SET    Balance = Balance - p_amount, LastModified = SYSDATE
    WHERE  AccountID = p_from_account;

    UPDATE Accounts
    SET    Balance = Balance + p_amount, LastModified = SYSDATE
    WHERE  AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'Success: $' || p_amount
        || ' transferred from Account #' || p_from_account
        || ' to Account #' || p_to_account
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: Source account #' || p_from_account || ' not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer error: ' || SQLERRM);
END TransferFunds;
/

-- test transfers
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: TransferFunds ---');
    TransferFunds(2, 1, 1000);    -- valid transfer
    TransferFunds(1, 2, 99999);   -- insufficient funds
    TransferFunds(1, 99, 100);    -- destination does not exist
END;
/
