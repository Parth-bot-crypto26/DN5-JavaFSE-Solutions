-- ============================================================
-- Exercise2_ErrorHandling.sql
-- Scenario 1: SafeTransferFunds — transfer with rollback on error
-- Scenario 2: UpdateSalary — handle non-existent employee
-- Scenario 3: AddNewCustomer — handle duplicate customer ID
-- ============================================================

SET SERVEROUTPUT ON;

-- ------------------------------------------------------------
-- Scenario 1: SafeTransferFunds
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE SafeTransferFunds (
    p_from_account IN NUMBER,
    p_to_account   IN NUMBER,
    p_amount       IN NUMBER
) AS
    v_balance NUMBER;
BEGIN
    -- check source account has enough money
    SELECT Balance INTO v_balance
    FROM   Accounts
    WHERE  AccountID = p_from_account
    FOR UPDATE;  -- lock the row during the transaction

    IF v_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20001,
            'Insufficient funds. Available: $' || v_balance
            || ', Requested: $' || p_amount);
    END IF;

    -- debit source
    UPDATE Accounts SET Balance = Balance - p_amount
    WHERE  AccountID = p_from_account;

    -- credit destination
    UPDATE Accounts SET Balance = Balance + p_amount
    WHERE  AccountID = p_to_account;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'Transfer complete: $' || p_amount
        || ' moved from Account #' || p_from_account
        || ' to Account #' || p_to_account
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error: One or both account IDs not found.');
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Transfer failed and rolled back. Reason: ' || SQLERRM);
END SafeTransferFunds;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: SafeTransferFunds ---');
    -- valid transfer
    SafeTransferFunds(2, 1, 500);
    -- this should fail — account 1 only has ~$1500 now
    SafeTransferFunds(1, 2, 99999);
END;
/

-- ------------------------------------------------------------
-- Scenario 2: UpdateSalary
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE UpdateSalary (
    p_employee_id  IN NUMBER,
    p_percentage   IN NUMBER
) AS
    v_current_salary NUMBER;
    v_new_salary     NUMBER;
BEGIN
    SELECT Salary INTO v_current_salary
    FROM   Employees
    WHERE  EmployeeID = p_employee_id;

    v_new_salary := v_current_salary + (v_current_salary * p_percentage / 100);

    UPDATE Employees SET Salary = v_new_salary
    WHERE  EmployeeID = p_employee_id;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(
        'Employee #' || p_employee_id
        || ' salary updated from $' || v_current_salary
        || ' to $' || v_new_salary
        || ' (' || p_percentage || '% raise)'
    );

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE(
            'Error: Employee #' || p_employee_id || ' does not exist. No changes made.'
        );
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Unexpected error: ' || SQLERRM);
END UpdateSalary;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 2: UpdateSalary ---');
    UpdateSalary(1, 10);    -- valid
    UpdateSalary(999, 15);  -- non-existent employee
END;
/

-- ------------------------------------------------------------
-- Scenario 3: AddNewCustomer
-- ------------------------------------------------------------
CREATE OR REPLACE PROCEDURE AddNewCustomer (
    p_id      IN NUMBER,
    p_name    IN VARCHAR2,
    p_dob     IN DATE,
    p_balance IN NUMBER
) AS
BEGIN
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name || ' (ID ' || p_id || ')');

EXCEPTION
    WHEN DUP_VAL_ON_INDEX THEN
        -- primary key violation — customer already exists
        DBMS_OUTPUT.PUT_LINE(
            'Error: Customer ID ' || p_id || ' already exists. Insertion skipped.'
        );
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error adding customer: ' || SQLERRM);
END AddNewCustomer;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: AddNewCustomer ---');
    AddNewCustomer(6, 'New Person', TO_DATE('1995-03-22','YYYY-MM-DD'), 3000);  -- valid
    AddNewCustomer(1, 'Duplicate',  TO_DATE('1980-01-01','YYYY-MM-DD'), 1000);  -- duplicate
END;
/
