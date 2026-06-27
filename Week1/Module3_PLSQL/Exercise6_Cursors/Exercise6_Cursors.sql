-- ============================================================
-- Exercise6_Cursors.sql
-- Scenario 1: GenerateMonthlyStatements — explicit cursor
-- Scenario 2: ApplyAnnualFee — deduct fee from all accounts
-- Scenario 3: UpdateLoanInterestRates — update rates by policy
-- ============================================================

SET SERVEROUTPUT ON;

-- ------------------------------------------------------------
-- Scenario 1: GenerateMonthlyStatements
-- Prints a transaction summary for each customer this month
-- ------------------------------------------------------------
DECLARE
    -- explicit cursor: fetch customer + transaction details for current month
    CURSOR GenerateMonthlyStatements IS
        SELECT c.Name, t.TransactionID, t.TransactionDate,
               t.Amount, t.TransactionType, a.AccountType
        FROM   Customers c
        JOIN   Accounts a    ON c.CustomerID = a.CustomerID
        JOIN   Transactions t ON a.AccountID  = t.AccountID
        WHERE  TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER  BY c.Name, t.TransactionDate;

    v_current_customer VARCHAR2(100) := '';
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: Monthly Statements ---');

    FOR rec IN GenerateMonthlyStatements LOOP
        -- print a header when we move to the next customer
        IF rec.Name != v_current_customer THEN
            DBMS_OUTPUT.PUT_LINE(CHR(10) || '== Statement for: ' || rec.Name || ' ==');
            v_current_customer := rec.Name;
        END IF;

        DBMS_OUTPUT.PUT_LINE(
            '  ' || TO_CHAR(rec.TransactionDate, 'DD-MON-YYYY')
            || ' | ' || rec.AccountType
            || ' | ' || rec.TransactionType
            || ' | $' || rec.Amount
        );
    END LOOP;

    DBMS_OUTPUT.PUT_LINE(CHR(10) || 'Monthly statements generated.');
END;
/

-- ------------------------------------------------------------
-- Scenario 2: ApplyAnnualFee
-- Deducts $50 maintenance fee from every account
-- ------------------------------------------------------------
DECLARE
    CURSOR ApplyAnnualFee IS
        SELECT AccountID, Balance FROM Accounts FOR UPDATE;

    v_fee       CONSTANT NUMBER := 50;
    v_processed NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 2: Apply Annual Maintenance Fee ---');

    OPEN ApplyAnnualFee;
    LOOP
        FETCH ApplyAnnualFee INTO ...;  -- replaced below with FOR loop for simplicity
        EXIT WHEN ApplyAnnualFee%NOTFOUND;
    END LOOP;
    CLOSE ApplyAnnualFee;

    -- cleaner equivalent using cursor FOR loop
    FOR rec IN (SELECT AccountID, Balance FROM Accounts) LOOP
        UPDATE Accounts
        SET    Balance = Balance - v_fee, LastModified = SYSDATE
        WHERE  AccountID = rec.AccountID;

        DBMS_OUTPUT.PUT_LINE(
            'Account #' || rec.AccountID
            || ': $' || rec.Balance
            || ' → $' || (rec.Balance - v_fee)
            || ' (fee $' || v_fee || ' deducted)'
        );
        v_processed := v_processed + 1;
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE(v_processed || ' account(s) charged annual fee.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error applying fee: ' || SQLERRM);
END;
/

-- ------------------------------------------------------------
-- Scenario 3: UpdateLoanInterestRates
-- New policy: loans > $7000 get 0.5% added, others get 0.5% off
-- ------------------------------------------------------------
DECLARE
    CURSOR UpdateLoanInterestRates IS
        SELECT LoanID, LoanAmount, InterestRate FROM Loans FOR UPDATE OF InterestRate;

    v_new_rate NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: Update Loan Interest Rates ---');

    FOR rec IN UpdateLoanInterestRates LOOP
        -- policy: larger loans pay a little more, smaller ones get relief
        IF rec.LoanAmount > 7000 THEN
            v_new_rate := rec.InterestRate + 0.5;
        ELSE
            v_new_rate := rec.InterestRate - 0.5;
        END IF;

        UPDATE Loans SET InterestRate = v_new_rate
        WHERE  CURRENT OF UpdateLoanInterestRates;

        DBMS_OUTPUT.PUT_LINE(
            'Loan #' || rec.LoanID
            || ' ($' || rec.LoanAmount || ')'
            || ': ' || rec.InterestRate || '% → ' || v_new_rate || '%'
        );
    END LOOP;

    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Loan interest rates updated.');

EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        DBMS_OUTPUT.PUT_LINE('Error updating rates: ' || SQLERRM);
END;
/
