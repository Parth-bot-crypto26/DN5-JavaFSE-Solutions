-- ============================================================
-- Exercise1_ControlStructures.sql  (MANDATORY)
-- Scenario 1: Discount on loan interest for customers above 60
-- Scenario 2: Mark customers as VIP if balance > 10000
-- Scenario 3: Send reminders for loans due in next 30 days
-- ============================================================

-- make sure output is visible
SET SERVEROUTPUT ON;

-- ------------------------------------------------------------
-- Scenario 1: Apply 1% interest discount for customers > 60
-- ------------------------------------------------------------
DECLARE
    v_age     NUMBER;
    v_name    VARCHAR2(100);
    v_loan_id NUMBER;
    v_rate    NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: Loan Interest Discount for Senior Customers ---');

    -- loop over every customer who has a loan
    FOR rec IN (
        SELECT c.CustomerID, c.Name, c.DOB, l.LoanID, l.InterestRate
        FROM   Customers c
        JOIN   Loans l ON c.CustomerID = l.CustomerID
    ) LOOP
        -- calculate age in full years
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, rec.DOB) / 12);

        IF v_age > 60 THEN
            -- knock 1% off their interest rate
            UPDATE Loans
            SET    InterestRate = InterestRate - 1
            WHERE  LoanID = rec.LoanID;

            DBMS_OUTPUT.PUT_LINE(
                rec.Name || ' (age ' || v_age || ') — rate reduced from '
                || rec.InterestRate || '% to ' || (rec.InterestRate - 1) || '%'
            );
        ELSE
            DBMS_OUTPUT.PUT_LINE(
                rec.Name || ' (age ' || v_age || ') — no discount applied'
            );
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Scenario 1 complete.' || CHR(10));

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in Scenario 1: ' || SQLERRM);
END;
/

-- ------------------------------------------------------------
-- Scenario 2: Flag customers as VIP if balance > 10,000
-- ------------------------------------------------------------
DECLARE
    v_count NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 2: Promote Customers to VIP ---');

    FOR rec IN (SELECT CustomerID, Name, Balance FROM Customers) LOOP
        IF rec.Balance > 10000 THEN
            UPDATE Customers
            SET    IsVIP = 'TRUE'
            WHERE  CustomerID = rec.CustomerID;

            v_count := v_count + 1;
            DBMS_OUTPUT.PUT_LINE(
                rec.Name || ' — balance $' || rec.Balance || ' → marked as VIP'
            );
        ELSE
            DBMS_OUTPUT.PUT_LINE(
                rec.Name || ' — balance $' || rec.Balance || ' → not VIP'
            );
        END IF;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(v_count || ' customer(s) upgraded to VIP.');
    DBMS_OUTPUT.PUT_LINE('Scenario 2 complete.' || CHR(10));

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error in Scenario 2: ' || SQLERRM);
END;
/

-- ------------------------------------------------------------
-- Scenario 3: Print reminders for loans due within 30 days
-- ------------------------------------------------------------
DECLARE
    v_days_left NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: Upcoming Loan Due-Date Reminders ---');

    FOR rec IN (
        SELECT c.Name, l.LoanID, l.LoanAmount, l.EndDate
        FROM   Loans l
        JOIN   Customers c ON l.CustomerID = c.CustomerID
        WHERE  l.EndDate BETWEEN SYSDATE AND SYSDATE + 30
        ORDER  BY l.EndDate
    ) LOOP
        v_days_left := TRUNC(rec.EndDate - SYSDATE);

        DBMS_OUTPUT.PUT_LINE(
            'REMINDER → ' || rec.Name
            || ' | Loan #' || rec.LoanID
            || ' | Amount: $' || rec.LoanAmount
            || ' | Due in ' || v_days_left || ' day(s)'
            || ' (' || TO_CHAR(rec.EndDate, 'DD-MON-YYYY') || ')'
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE('Scenario 3 complete.');

EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error in Scenario 3: ' || SQLERRM);
END;
/
