// InventoryTest.java

public class InventoryTest {
    public static void main(String[] args) {

        System.out.println("=== Inventory Management System ===\n");

        InventoryManager manager = new InventoryManager();

        // Add products
        manager.addProduct(new Product(101, "Laptop",       50,  75000.00));
        manager.addProduct(new Product(102, "Mouse",        200, 500.00));
        manager.addProduct(new Product(103, "Keyboard",     150, 1200.00));
        manager.addProduct(new Product(104, "Monitor",      30,  18000.00));

        // Display all
        manager.displayAll();

        // Update
        System.out.println("\n--- Updating Laptop quantity and price ---");
        manager.updateProduct(101, 45, 72000.00);

        // Delete
        System.out.println("\n--- Deleting Mouse ---");
        manager.deleteProduct(102);

        // Search
        System.out.println("\n--- Searching for Keyboard (ID 103) ---");
        Product found = manager.searchProduct(103);
        if (found != null) System.out.println("Found: " + found);

        manager.displayAll();

        // Time Complexity Analysis
        System.out.println("\n=== Time Complexity Analysis (HashMap) ===");
        System.out.println("Add Product    : O(1) average");
        System.out.println("Update Product : O(1) average");
        System.out.println("Delete Product : O(1) average");
        System.out.println("Search Product : O(1) average");
        System.out.println("Display All    : O(n)");
        System.out.println("\nHashMap is ideal because productId is unique and acts as a perfect key.");
    }
}
