// InventoryManager.java
// Uses HashMap for O(1) average time complexity for add/update/delete/search
// HashMap stores key=productId, value=Product object

import java.util.HashMap;
import java.util.Map;

public class InventoryManager {

    // HashMap chosen because:
    // - Add:    O(1) average
    // - Update: O(1) average
    // - Delete: O(1) average
    // - Search: O(1) average
    private HashMap<Integer, Product> inventory = new HashMap<>();

    // ADD - O(1) average
    public void addProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            System.out.println("❌ Product ID " + product.getProductId() + " already exists!");
            return;
        }
        inventory.put(product.getProductId(), product);
        System.out.println("✅ Added: " + product);
    }

    // UPDATE - O(1) average
    public void updateProduct(int productId, int newQuantity, double newPrice) {
        Product product = inventory.get(productId);
        if (product == null) {
            System.out.println("❌ Product ID " + productId + " not found!");
            return;
        }
        product.setQuantity(newQuantity);
        product.setPrice(newPrice);
        System.out.println("✅ Updated: " + product);
    }

    // DELETE - O(1) average
    public void deleteProduct(int productId) {
        Product removed = inventory.remove(productId);
        if (removed == null) {
            System.out.println("❌ Product ID " + productId + " not found!");
        } else {
            System.out.println("✅ Deleted: " + removed.getProductName());
        }
    }

    // SEARCH - O(1) average
    public Product searchProduct(int productId) {
        return inventory.get(productId);
    }

    // DISPLAY ALL - O(n)
    public void displayAll() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }
        System.out.println("\n--- Current Inventory (" + inventory.size() + " items) ---");
        for (Map.Entry<Integer, Product> entry : inventory.entrySet()) {
            System.out.println(entry.getValue());
        }
    }
}
