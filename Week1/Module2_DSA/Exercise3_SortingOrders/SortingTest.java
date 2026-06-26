// Order.java + SortingAlgorithms.java + SortingTest.java

// ===== Order Model =====
class Order {
    private int orderId;
    private String customerName;
    private double totalPrice;

    public Order(int orderId, String customerName, double totalPrice) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() { return totalPrice; }

    @Override
    public String toString() {
        return String.format("Order[ID=%d, Customer=%-15s Price=$%.2f]",
                orderId, customerName, totalPrice);
    }
}

// ===== Bubble Sort =====
// Time: O(n²) — very slow for large data
// Repeatedly swaps adjacent elements if they're in wrong order
class BubbleSort {
    public static void sort(Order[] orders) {
        int n = orders.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (orders[j].getTotalPrice() > orders[j + 1].getTotalPrice()) {
                    // Swap
                    Order temp = orders[j];
                    orders[j] = orders[j + 1];
                    orders[j + 1] = temp;
                }
            }
        }
    }
}

// ===== Quick Sort =====
// Time: O(n log n) average — much faster!
// Picks a pivot, puts smaller elements left, larger right
class QuickSort {
    public static void sort(Order[] orders, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(orders, low, high);
            sort(orders, low, pivotIndex - 1);   // Sort left part
            sort(orders, pivotIndex + 1, high);  // Sort right part
        }
    }

    private static int partition(Order[] orders, int low, int high) {
        double pivot = orders[high].getTotalPrice();
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (orders[j].getTotalPrice() <= pivot) {
                i++;
                Order temp = orders[i];
                orders[i] = orders[j];
                orders[j] = temp;
            }
        }
        Order temp = orders[i + 1];
        orders[i + 1] = orders[high];
        orders[high] = temp;
        return i + 1;
    }
}

// ===== Test Class =====
public class SortingTest {

    static void printOrders(Order[] orders) {
        for (Order o : orders) System.out.println("  " + o);
    }

    static Order[] getSampleOrders() {
        return new Order[]{
            new Order(1, "Alice",   15000.00),
            new Order(2, "Bob",     3500.50),
            new Order(3, "Charlie", 87000.00),
            new Order(4, "Diana",   1200.00),
            new Order(5, "Eve",     45000.75),
        };
    }

    public static void main(String[] args) {

        System.out.println("=== Sorting Customer Orders by Total Price ===\n");

        // --- Bubble Sort ---
        System.out.println("--- BUBBLE SORT (ascending by price) ---");
        Order[] orders1 = getSampleOrders();
        System.out.println("Before:");
        printOrders(orders1);
        BubbleSort.sort(orders1);
        System.out.println("After:");
        printOrders(orders1);

        System.out.println();

        // --- Quick Sort ---
        System.out.println("--- QUICK SORT (ascending by price) ---");
        Order[] orders2 = getSampleOrders();
        System.out.println("Before:");
        printOrders(orders2);
        QuickSort.sort(orders2, 0, orders2.length - 1);
        System.out.println("After:");
        printOrders(orders2);

        System.out.println("\n=== Performance Comparison ===");
        System.out.println("+---------------+-------------+-------------+-----------+");
        System.out.println("| Algorithm     | Best Case   | Average     | Worst     |");
        System.out.println("+---------------+-------------+-------------+-----------+");
        System.out.println("| Bubble Sort   | O(n)        | O(n²)       | O(n²)     |");
        System.out.println("| Quick Sort    | O(n log n)  | O(n log n)  | O(n²)     |");
        System.out.println("+---------------+-------------+-------------+-----------+");
        System.out.println("\nQuick Sort is preferred because:");
        System.out.println("→ Average O(n log n) vs Bubble Sort's O(n²)");
        System.out.println("→ For 1000 orders: QuickSort ~10,000 ops vs BubbleSort ~1,000,000 ops!");
    }
}
