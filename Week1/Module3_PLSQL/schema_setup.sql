-- ============================================================
-- schema_setup.sql
-- Run this FIRST before any exercise!
-- Creates all tables and inserts sample data
-- ============================================================

-- Drop tables if they exist (clean slate)
DROP TABLE Transactions CASCADE CONSTRAINTS;
DROP TABLE Loans       CASCADE CONSTRAINTS;
DROP TABLE Accounts    CASCADE CONSTRAINTS;
DROP TABLE Employees   CASCADE CONSTRAINTS;
DROP TABLE Customers   CASCADE CONSTRAINTS;
DROP TABLE AuditLog;

-- ===== CREATE TABLES =====

CREATE TABLE Customers (
    CustomerID   NUMBER PRIMARY KEY,
    Name         VARCHAR2(100),
    DOB          DATE,
    Balance      NUMBER,
    LastModified DATE,
    IsVIP        VARCHAR2(5) DEFAULT 'FALSE'
);

CREATE TABLE Accounts (
    AccountID    NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    AccountType  VARCHAR2(20),
    Balance      NUMBER,
    LastModified DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Transactions (
    TransactionID   NUMBER PRIMARY KEY,
    AccountID       NUMBER,
    TransactionDate DATE,
    Amount          NUMBER,
    TransactionType VARCHAR2(10),
    FOREIGN KEY (AccountID) REFERENCES Accounts(AccountID)
);

CREATE TABLE Loans (
    LoanID       NUMBER PRIMARY KEY,
    CustomerID   NUMBER,
    LoanAmount   NUMBER,
    InterestRate NUMBER,
    StartDate    DATE,
    EndDate      DATE,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Employees (
    EmployeeID NUMBER PRIMARY KEY,
    Name       VARCHAR2(100),
    Position   VARCHAR2(50),
    Salary     NUMBER,
    Department VARCHAR2(50),
    HireDate   DATE
);

CREATE TABLE AuditLog (
    LogID         NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TransactionID NUMBER,
    AccountID     NUMBER,
    Amount        NUMBER,
    TransactionType VARCHAR2(10),
    LogDate       DATE DEFAULT SYSDATE
);

-- ===== INSERT SAMPLE DATA =====

-- Customers (mix of ages and balances for testing)
INSERT INTO Customers VALUES (1, 'John Doe',      TO_DATE('1955-05-15','YYYY-MM-DD'), 1000,  SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (2, 'Jane Smith',    TO_DATE('1990-07-20','YYYY-MM-DD'), 15000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (3, 'Bob Senior',    TO_DATE('1950-03-10','YYYY-MM-DD'), 5000,  SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (4, 'Alice Young',   TO_DATE('2000-01-25','YYYY-MM-DD'), 25000, SYSDATE, 'FALSE');
INSERT INTO Customers VALUES (5, 'Charlie Old',   TO_DATE('1948-11-30','YYYY-MM-DD'), 8000,  SYSDATE, 'FALSE');

-- Accounts
INSERT INTO Accounts VALUES (1, 1, 'Savings',  1000,  SYSDATE);
INSERT INTO Accounts VALUES (2, 2, 'Checking', 15000, SYSDATE);
INSERT INTO Accounts VALUES (3, 3, 'Savings',  5000,  SYSDATE);
INSERT INTO Accounts VALUES (4, 4, 'Savings',  25000, SYSDATE);

-- Transactions
INSERT INTO Transactions VALUES (1, 1, SYSDATE,       200, 'Deposit');
INSERT INTO Transactions VALUES (2, 2, SYSDATE,       300, 'Withdrawal');
INSERT INTO Transactions VALUES (3, 1, SYSDATE - 5,   150, 'Deposit');

-- Loans (one due within 30 days for testing)
INSERT INTO Loans VALUES (1, 1, 5000,  5.0, SYSDATE, ADD_MONTHS(SYSDATE, 60));
INSERT INTO Loans VALUES (2, 3, 10000, 7.5, SYSDATE, SYSDATE + 20);   -- Due in 20 days!
INSERT INTO Loans VALUES (3, 5, 8000,  6.0, SYSDATE, SYSDATE + 10);   -- Due in 10 days!

-- Employees
INSERT INTO Employees VALUES (1, 'Alice Johnson', 'Manager',   70000, 'HR', TO_DATE('2015-06-15','YYYY-MM-DD'));
INSERT INTO Employees VALUES (2, 'Bob Brown',     'Developer', 60000, 'IT', TO_DATE('2017-03-20','YYYY-MM-DD'));
INSERT INTO Employees VALUES (3, 'Carol White',   'Analyst',   55000, 'IT', TO_DATE('2019-08-10','YYYY-MM-DD'));
INSERT INTO Employees VALUES (4, 'Dave Green',    'Designer',  50000, 'HR', TO_DATE('2020-01-05','YYYY-MM-DD'));

COMMIT;

SELECT 'Schema setup complete! All tables created and populated.' AS Status FROM DUAL;
