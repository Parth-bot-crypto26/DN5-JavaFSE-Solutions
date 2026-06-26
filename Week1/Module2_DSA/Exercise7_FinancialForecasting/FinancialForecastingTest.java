// FinancialForecastingTest.java - MANDATORY Exercise
// Uses RECURSION to predict future value of an investment

public class FinancialForecastingTest {

    // ===== RECURSIVE METHOD =====
    // Formula: futureValue = presentValue * (1 + growthRate)^years
    //
    // Recursive thinking:
    // - Base case: if years == 0, return the current value (no more growth)
    // - Recursive case: apply one year of growth, then recurse for remaining years
    //
    // Time Complexity: O(n) - n recursive calls for n years
    // Space Complexity: O(n) - n frames on the call stack

    public static double calculateFutureValue(double presentValue,
                                               double growthRate,
                                               int years) {
        // BASE CASE - stop recursion when no years remain
        if (years == 0) {
            return presentValue;
        }

        // RECURSIVE CASE - apply growth for this year, recurse for rest
        double valueAfterOneYear = presentValue * (1 + growthRate);
        return calculateFutureValue(valueAfterOneYear, growthRate, years - 1);
    }

    // ===== OPTIMIZED VERSION: Memoization (avoids recalculation) =====
    // For repeated queries with same inputs, cache results in a HashMap
    private static java.util.HashMap<String, Double> cache = new java.util.HashMap<>();

    public static double calculateFutureValueMemo(double presentValue,
                                                   double growthRate,
                                                   int years) {
        String key = presentValue + "_" + growthRate + "_" + years;

        if (cache.containsKey(key)) {
            return cache.get(key);  // Return cached result
        }

        if (years == 0) return presentValue;

        double result = calculateFutureValueMemo(
                presentValue * (1 + growthRate), growthRate, years - 1);

        cache.put(key, result);  // Store in cache
        return result;
    }

    public static void main(String[] args) {

        System.out.println("=== Financial Forecasting Tool (Recursion) ===\n");

        double initialInvestment = 100000.00;  // ₹1,00,000
        double annualGrowthRate  = 0.10;       // 10% per year

        System.out.printf("Initial Investment : ₹%.2f%n", initialInvestment);
        System.out.printf("Annual Growth Rate : %.0f%%%n", annualGrowthRate * 100);
        System.out.println();

        // Forecast for multiple years
        System.out.println("Year-wise Forecast:");
        System.out.println("+------+-------------------+");
        System.out.println("| Year | Future Value      |");
        System.out.println("+------+-------------------+");

        for (int year = 1; year <= 10; year++) {
            double futureValue = calculateFutureValue(initialInvestment, annualGrowthRate, year);
            System.out.printf("| %-4d | ₹%,16.2f |%n", year, futureValue);
        }
        System.out.println("+------+-------------------+");

        System.out.println("\n=== Recursion Explanation ===");
        System.out.println("calculateFutureValue(100000, 0.10, 3) calls:");
        System.out.println("  → calculateFutureValue(110000, 0.10, 2)");
        System.out.println("      → calculateFutureValue(121000, 0.10, 1)");
        System.out.println("          → calculateFutureValue(133100, 0.10, 0)");
        System.out.println("              → returns 133100  [BASE CASE]");

        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Time Complexity  : O(n) — one call per year");
        System.out.println("Space Complexity : O(n) — n frames on call stack");
        System.out.println("\nOptimization with Memoization:");
        System.out.println("→ Cache results of repeated subproblems");
        System.out.println("→ Reduces redundant computation in complex scenarios");

        // Demonstrate memoized version
        System.out.println("\n--- Memoized Version (same result, cached) ---");
        double memoResult = calculateFutureValueMemo(initialInvestment, annualGrowthRate, 5);
        System.out.printf("Future value after 5 years: ₹%,.2f%n", memoResult);
    }
}
