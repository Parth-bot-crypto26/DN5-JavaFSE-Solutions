# Module 2 - Data Structures and Algorithms
## DN 5.0 Deep Skilling - Java FSE

---

## ✅ Mandatory Exercises
- **Exercise 2:** E-commerce Platform Search Function (Linear + Binary Search)
- **Exercise 7:** Financial Forecasting (Recursion + Memoization)

## 📚 All Exercises
| # | Problem | Key Concept | Time Complexity |
|---|---------|-------------|-----------------|
| 1 | Inventory Management | HashMap | O(1) avg |
| 2 ⭐ | E-commerce Search | Linear & Binary Search | O(n) vs O(log n) |
| 3 | Sorting Orders | Bubble Sort & Quick Sort | O(n²) vs O(n log n) |
| 4 | Employee Management | Arrays | O(1) add, O(n) search |
| 5 | Task Management | Singly Linked List | O(n) |
| 6 | Library Search | Linear & Binary Search | O(n) vs O(log n) |
| 7 ⭐ | Financial Forecasting | Recursion + Memoization | O(n) |

---

## 🚀 How to Run

### Exercise 2 - E-commerce Search (MANDATORY)
```bash
cd Exercise2_EcommerceSearch
javac Product.java SearchAlgorithms.java EcommerceSearchTest.java
java EcommerceSearchTest
```

### Exercise 7 - Financial Forecasting (MANDATORY)
```bash
cd Exercise7_FinancialForecasting
javac FinancialForecastingTest.java
java FinancialForecastingTest
```

### All other exercises (single file each)
```bash
cd Exercise1_Inventory
javac Product.java InventoryManager.java InventoryTest.java
java InventoryTest
```

---

## 💡 Key Concepts

### Big O Notation
- **O(1)** → Constant time (best) — HashMap lookup
- **O(log n)** → Logarithmic — Binary Search
- **O(n)** → Linear — Linear Search, Array traversal
- **O(n log n)** → Quick Sort, Merge Sort
- **O(n²)** → Quadratic (avoid) — Bubble Sort

### Data Structures Summary
| Structure | Access | Search | Insert | Delete |
|-----------|--------|--------|--------|--------|
| Array | O(1) | O(n) | O(1) | O(n) |
| LinkedList | O(n) | O(n) | O(1) | O(1) |
| HashMap | O(1) | O(1) | O(1) | O(1) |
