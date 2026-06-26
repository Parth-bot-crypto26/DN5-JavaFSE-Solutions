// SearchAlgorithms.java
// LINEAR SEARCH: O(n) - checks every element one by one
// BINARY SEARCH: O(log n) - works only on SORTED arrays, splits in half each time

public class SearchAlgorithms {

    // ===== LINEAR SEARCH =====
    // Works on ANY array (sorted or unsorted)
    // Time Complexity: O(n) - worst case checks all n elements
    // Space Complexity: O(1)
    public static Product linearSearch(Product[] products, int targetId) {
        System.out.println("  [Linear Search] Scanning products...");
        for (int i = 0; i < products.length; i++) {
            System.out.println("  Checking index " + i + ": ID=" + products[i].getProductId());
            if (products[i].getProductId() == targetId) {
                System.out.println("  Found at index " + i + "!");
                return products[i];
            }
        }
        return null;  // Not found
    }

    // ===== BINARY SEARCH =====
    // REQUIRES array sorted by productId
    // Time Complexity: O(log n) - eliminates half the elements each step
    // Space Complexity: O(1)
    public static Product binarySearch(Product[] sortedProducts, int targetId) {
        int left = 0;
        int right = sortedProducts.length - 1;
        int step = 1;

        System.out.println("  [Binary Search] Searching in sorted array...");

        while (left <= right) {
            int mid = left + (right - left) / 2;  // Avoids integer overflow
            System.out.println("  Step " + step++ + ": left=" + left
                    + " mid=" + mid + " right=" + right
                    + " midId=" + sortedProducts[mid].getProductId());

            if (sortedProducts[mid].getProductId() == targetId) {
                System.out.println("  Found at index " + mid + "!");
                return sortedProducts[mid];
            } else if (sortedProducts[mid].getProductId() < targetId) {
                left = mid + 1;   // Target is in RIGHT half
            } else {
                right = mid - 1;  // Target is in LEFT half
            }
        }
        return null;  // Not found
    }
}
