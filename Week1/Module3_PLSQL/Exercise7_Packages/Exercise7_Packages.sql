-- ============================================================
-- Exercise7_Packages.sql
-- Scenario 1: CustomerManagement package
-- Scenario 2: EmployeeManagement package
-- Scenario 3: AccountOperations package
-- ============================================================

SET SERVEROUTPUT ON;

-- ============================================================
-- Scenario 1: CustomerManagement Package
-- ============================================================

-- Package specification (public interface)
CREATE OR REPLACE PACKAGE CustomerManagement AS
    PROCEDURE AddCustomer(
        p_id      IN NUMBER,
        p_name    IN VARCHAR2,
        p_dob     IN DATE,
        p_balance IN NUMBER
    );
    PROCEDURE UpdateCustomerDetails(
        p_id      IN NUMBER,
        p_name    IN VARCHAR2,
        p_balance IN NUMBER
    );
    FUNCTION GetCustomerBalance(p_id IN NUMBER) RETURN NUMBER;
END CustomerManagement;
/

-- Package body (implementation)
CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_id      IN NUMBER,
        p_name    IN VARCHAR2,
        p_dob     IN DATE,
        p_balance IN NUMBER
    ) AS
    BEGIN
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_id, p_name, p_dob, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer added: ' || p_name);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Customer ID ' || p_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Add failed: ' || SQLERRM);
    END AddCustomer;

    PROCEDURE UpdateCustomerDetails(
        p_id      IN NUMBER,
        p_name    IN VARCHAR2,
        p_balance IN NUMBER
    ) AS
    BEGIN
        UPDATE Customers
        SET    Name = p_name, Balance = p_balance, LastModified = SYSDATE
        WHERE  CustomerID = p_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Customer #' || p_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Customer #' || p_id || ' updated.');
        END IF;
    END UpdateCustomerDetails;

    FUNCTION GetCustomerBalance(p_id IN NUMBER) RETURN NUMBER AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance
        FROM   Customers WHERE CustomerID = p_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: CustomerManagement Package ---');
    CustomerManagement.AddCustomer(7, 'New Customer', TO_DATE('1992-04-10','YYYY-MM-DD'), 5000);
    CustomerManagement.UpdateCustomerDetails(7, 'Updated Customer', 6000);
    DBMS_OUTPUT.PUT_LINE('Balance for #7: $' || CustomerManagement.GetCustomerBalance(7));
END;
/

-- ============================================================
-- Scenario 2: EmployeeManagement Package
-- ============================================================
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    PROCEDURE HireEmployee(
        p_id         IN NUMBER,
        p_name       IN VARCHAR2,
        p_position   IN VARCHAR2,
        p_salary     IN NUMBER,
        p_department IN VARCHAR2
    );
    PROCEDURE UpdateEmployeeDetails(
        p_id       IN NUMBER,
        p_position IN VARCHAR2,
        p_salary   IN NUMBER
    );
    FUNCTION GetAnnualSalary(p_id IN NUMBER) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_id         IN NUMBER,
        p_name       IN VARCHAR2,
        p_position   IN VARCHAR2,
        p_salary     IN NUMBER,
        p_department IN VARCHAR2
    ) AS
    BEGIN
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_id, p_name, p_position, p_salary, p_department, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Hired: ' || p_name || ' as ' || p_position);
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Hire failed: ' || SQLERRM);
    END HireEmployee;

    PROCEDURE UpdateEmployeeDetails(
        p_id       IN NUMBER,
        p_position IN VARCHAR2,
        p_salary   IN NUMBER
    ) AS
    BEGIN
        UPDATE Employees
        SET    Position = p_position, Salary = p_salary
        WHERE  EmployeeID = p_id;

        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Employee #' || p_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Employee #' || p_id || ' details updated.');
        END IF;
    END UpdateEmployeeDetails;

    -- annual salary is just monthly * 12 here (salary stored as annual in schema)
    FUNCTION GetAnnualSalary(p_id IN NUMBER) RETURN NUMBER AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_id;
        RETURN v_salary;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN RETURN NULL;
    END GetAnnualSalary;

END EmployeeManagement;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 2: EmployeeManagement Package ---');
    EmployeeManagement.HireEmployee(5, 'Eve Turner', 'DevOps', 65000, 'IT');
    EmployeeManagement.UpdateEmployeeDetails(5, 'Senior DevOps', 75000);
    DBMS_OUTPUT.PUT_LINE('Annual salary for #5: $' || EmployeeManagement.GetAnnualSalary(5));
END;
/

-- ============================================================
-- Scenario 3: AccountOperations Package
-- ============================================================
CREATE OR REPLACE PACKAGE AccountOperations AS
    PROCEDURE OpenAccount(
        p_account_id  IN NUMBER,
        p_customer_id IN NUMBER,
        p_type        IN VARCHAR2,
        p_balance     IN NUMBER
    );
    PROCEDURE CloseAccount(p_account_id IN NUMBER);
    FUNCTION GetTotalBalance(p_customer_id IN NUMBER) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_account_id  IN NUMBER,
        p_customer_id IN NUMBER,
        p_type        IN VARCHAR2,
        p_balance     IN NUMBER
    ) AS
    BEGIN
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_customer_id, p_type, p_balance, SYSDATE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE(
            p_type || ' account #' || p_account_id
            || ' opened for Customer #' || p_customer_id
        );
    EXCEPTION
        WHEN DUP_VAL_ON_INDEX THEN
            DBMS_OUTPUT.PUT_LINE('Account #' || p_account_id || ' already exists.');
        WHEN OTHERS THEN
            ROLLBACK;
            DBMS_OUTPUT.PUT_LINE('Open account failed: ' || SQLERRM);
    END OpenAccount;

    PROCEDURE CloseAccount(p_account_id IN NUMBER) AS
    BEGIN
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        IF SQL%ROWCOUNT = 0 THEN
            DBMS_OUTPUT.PUT_LINE('Account #' || p_account_id || ' not found.');
        ELSE
            COMMIT;
            DBMS_OUTPUT.PUT_LINE('Account #' || p_account_id || ' closed.');
        END IF;
    END CloseAccount;

    FUNCTION GetTotalBalance(p_customer_id IN NUMBER) RETURN NUMBER AS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM   Accounts WHERE CustomerID = p_customer_id;
        RETURN v_total;
    END GetTotalBalance;

END AccountOperations;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: AccountOperations Package ---');
    AccountOperations.OpenAccount(5, 2, 'Savings', 3000);
    DBMS_OUTPUT.PUT_LINE(
        'Total balance for Customer #2: $'
        || AccountOperations.GetTotalBalance(2)
    );
    AccountOperations.CloseAccount(5);
END;
/
