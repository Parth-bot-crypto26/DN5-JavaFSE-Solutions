// EcommerceSearchTest.java - MANDATORY Exercise

public class EcommerceSearchTest {

    public static void main(String[] args) {

        System.out.println("=== E-commerce Platform Search Function ===\n");

        // Unsorted array for Linear Search
        Product[] products = {
            new Product(105, "Headphones",      "Electronics"),
            new Product(102, "Running Shoes",   "Sports"),
            new Product(108, "Coffee Maker",    "Kitchen"),
            new Product(101, "Laptop",          "Electronics"),
            new Product(110, "Yoga Mat",        "Sports"),
            new Product(103, "Smartphone",      "Electronics"),
            new Product(107, "Blender",         "Kitchen"),
        };

        // Sorted array for Binary Search (sorted by productId)
        Product[] sortedProducts = {
            new Product(101, "Laptop",          "Electronics"),
            new Product(102, "Running Shoes",   "Sports"),
            new Product(103, "Smartphone",      "Electronics"),
            new Product(105, "Headphones",      "Electronics"),
            new Product(107, "Blender",         "Kitchen"),
            new Product(108, "Coffee Maker",    "Kitchen"),
            new Product(110, "Yoga Mat",        "Sports"),
        };

        int searchId = 107;
        System.out.println("Searching for Product ID: " + searchId + "\n");

        // --- Linear Search ---
        System.out.println("--- LINEAR SEARCH ---");
        long startTime = System.nanoTime();
        Product linearResult = SearchAlgorithms.linearSearch(products, searchId);
        long linearTime = System.nanoTime() - startTime;

        if (linearResult != null)
            System.out.println("Result: " + linearResult);
        else
            System.out.println("Result: Not Found");
        System.out.println("Time taken: " + linearTime + " ns\n");

        // --- Binary Search ---
        System.out.println("--- BINARY SEARCH ---");
        startTime = System.nanoTime();
        Product binaryResult = SearchAlgorithms.binarySearch(sortedProducts, searchId);
        long binaryTime = System.nanoTime() - startTime;

        if (binaryResult != null)
            System.out.println("Result: " + binaryResult);
        else
            System.out.println("Result: Not Found");
        System.out.println("Time taken: " + binaryTime + " ns\n");

        // --- Analysis ---
        System.out.println("=== Analysis: Big O Notation ===");
        System.out.println("+------------------+-------------+-------------+-----------+");
        System.out.println("| Algorithm        | Best Case   | Average     | Worst     |");
        System.out.println("+------------------+-------------+-------------+-----------+");
        System.out.println("| Linear Search    | O(1)        | O(n)        | O(n)      |");
        System.out.println("| Binary Search    | O(1)        | O(log n)    | O(log n)  |");
        System.out.println("+------------------+-------------+-------------+-----------+");
        System.out.println();
        System.out.println("Recommendation for E-commerce Platform:");
        System.out.println("→ Use BINARY SEARCH when products are sorted by ID.");
        System.out.println("→ Binary search checks only log2(n) elements.");
        System.out.println("→ For 1 million products: Linear=1,000,000 steps vs Binary=20 steps!");
        System.out.println("→ Use Linear Search only for small or unsorted datasets.");
    }
}
