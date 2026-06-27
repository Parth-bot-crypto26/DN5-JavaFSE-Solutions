# Module 3 — PL/SQL Programming
## DN 5.0 Deep Skilling - Java FSE

---

## ✅ Mandatory Exercises
- **Exercise 1:** Control Structures
- **Exercise 3:** Stored Procedures

## 📚 All Exercises
| # | Topic | Scenarios |
|---|-------|-----------|
| 1 ⭐ | Control Structures | Age-based discount, VIP flag, loan reminders |
| 2 | Error Handling | SafeTransferFunds, UpdateSalary, AddNewCustomer |
| 3 ⭐ | Stored Procedures | Monthly interest, employee bonus, fund transfer |
| 4 | Functions | CalculateAge, EMI calculator, HasSufficientBalance |
| 5 | Triggers | LastModified auto-update, audit log, transaction rules |
| 6 | Cursors | Monthly statements, annual fee, interest rate update |
| 7 | Packages | CustomerManagement, EmployeeManagement, AccountOperations |

---

## 🚀 How to Run

### Prerequisites
You need Oracle Database (or Oracle Live SQL at https://livesql.oracle.com — free!)

### Step 1 — Run the schema setup first (always!)
```sql
@schema_setup.sql
```

### Step 2 — Run any exercise
```sql
@Exercise1_ControlStructures/Exercise1_ControlStructures.sql
@Exercise3_StoredProcedures/Exercise3_StoredProcedures.sql
```

### Using Oracle Live SQL (no installation needed)
1. Go to https://livesql.oracle.com
2. Sign in with a free Oracle account
3. Paste schema_setup.sql → Run
4. Paste each exercise file → Run

---

## 💡 Key Concepts

| Concept | What it does |
|---------|-------------|
| `FOR rec IN (SELECT...)` | Implicit cursor loop — simplest way to iterate rows |
| `CURSOR name IS SELECT...` | Explicit cursor — more control over fetch/close |
| `EXCEPTION WHEN NO_DATA_FOUND` | Handles queries that return nothing |
| `EXCEPTION WHEN DUP_VAL_ON_INDEX` | Handles primary key violations |
| `RAISE_APPLICATION_ERROR(-20001, '...')` | Throw a custom error (codes -20000 to -20999) |
| `COMMIT / ROLLBACK` | Save or undo the transaction |
| `FOR UPDATE` | Lock rows so no one else modifies them mid-transaction |
| `SQL%ROWCOUNT` | How many rows the last DML statement affected |
| `:NEW.column` | Inside a trigger — the new value being written |
| `PACKAGE` | Groups related procedures/functions into one unit |
