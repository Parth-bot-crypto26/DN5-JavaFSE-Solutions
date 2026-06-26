// Product.java - Data model for inventory items

public class Product {
    private int productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(int productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters
    public int getProductId()       { return productId; }
    public String getProductName()  { return productName; }
    public int getQuantity()        { return quantity; }
    public double getPrice()        { return price; }

    // Setters
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public void setPrice(double price)    { this.price = price; }

    @Override
    public String toString() {
        return String.format("Product[ID=%d, Name=%-20s Qty=%d, Price=$%.2f]",
                productId, productName, quantity, price);
    }
}
