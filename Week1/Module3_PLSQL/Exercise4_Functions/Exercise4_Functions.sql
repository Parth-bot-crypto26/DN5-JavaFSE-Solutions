-- ============================================================
-- Exercise4_Functions.sql
-- Scenario 1: CalculateAge
-- Scenario 2: CalculateMonthlyInstallment (EMI)
-- Scenario 3: HasSufficientBalance
-- ============================================================

SET SERVEROUTPUT ON;

-- ------------------------------------------------------------
-- Scenario 1: CalculateAge
-- Returns age in full years given a date of birth
-- ------------------------------------------------------------
CREATE OR REPLACE FUNCTION CalculateAge (
    p_dob IN DATE
) RETURN NUMBER AS
BEGIN
    RETURN TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
END CalculateAge;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 1: CalculateAge ---');
    FOR rec IN (SELECT Name, DOB FROM Customers) LOOP
        DBMS_OUTPUT.PUT_LINE(
            rec.Name || ' — Age: ' || CalculateAge(rec.DOB) || ' years'
        );
    END LOOP;
END;
/

-- ------------------------------------------------------------
-- Scenario 2: CalculateMonthlyInstallment (standard EMI formula)
-- EMI = P * r * (1+r)^n / ((1+r)^n - 1)
-- where r = monthly rate, n = total months
-- ------------------------------------------------------------
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment (
    p_loan_amount    IN NUMBER,
    p_annual_rate    IN NUMBER,   -- e.g. pass 8 for 8%
    p_years          IN NUMBER
) RETURN NUMBER AS
    v_monthly_rate   NUMBER;
    v_months         NUMBER;
    v_emi            NUMBER;
BEGIN
    v_monthly_rate := p_annual_rate / 100 / 12;
    v_months       := p_years * 12;

    IF v_monthly_rate = 0 THEN
        -- edge case: interest-free loan
        v_emi := p_loan_amount / v_months;
    ELSE
        v_emi := p_loan_amount
                 * v_monthly_rate
                 * POWER(1 + v_monthly_rate, v_months)
                 / (POWER(1 + v_monthly_rate, v_months) - 1);
    END IF;

    RETURN ROUND(v_emi, 2);
END CalculateMonthlyInstallment;
/

-- test it
DECLARE
    v_emi NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 2: CalculateMonthlyInstallment ---');
    v_emi := CalculateMonthlyInstallment(500000, 8.5, 10);
    DBMS_OUTPUT.PUT_LINE('Loan $500,000 @ 8.5% for 10 years → EMI: $' || v_emi);

    v_emi := CalculateMonthlyInstallment(100000, 12, 5);
    DBMS_OUTPUT.PUT_LINE('Loan $100,000 @ 12% for 5 years  → EMI: $' || v_emi);
END;
/

-- ------------------------------------------------------------
-- Scenario 3: HasSufficientBalance
-- Returns 1 (true) or 0 (false) — PL/SQL doesn't return booleans to SQL
-- ------------------------------------------------------------
CREATE OR REPLACE FUNCTION HasSufficientBalance (
    p_account_id IN NUMBER,
    p_amount     IN NUMBER
) RETURN NUMBER AS
    v_balance NUMBER;
BEGIN
    SELECT Balance INTO v_balance
    FROM   Accounts
    WHERE  AccountID = p_account_id;

    IF v_balance >= p_amount THEN
        RETURN 1;   -- sufficient
    ELSE
        RETURN 0;   -- insufficient
    END IF;

EXCEPTION
    WHEN NO_DATA_FOUND THEN
        DBMS_OUTPUT.PUT_LINE('Account #' || p_account_id || ' not found.');
        RETURN 0;
END HasSufficientBalance;
/

-- test it
BEGIN
    DBMS_OUTPUT.PUT_LINE('--- Scenario 3: HasSufficientBalance ---');

    IF HasSufficientBalance(2, 5000) = 1 THEN
        DBMS_OUTPUT.PUT_LINE('Account #2 has sufficient balance for $5000.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account #2 does NOT have sufficient balance for $5000.');
    END IF;

    IF HasSufficientBalance(1, 50000) = 1 THEN
        DBMS_OUTPUT.PUT_LINE('Account #1 has sufficient balance for $50000.');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account #1 does NOT have sufficient balance for $50000.');
    END IF;
END;
/
