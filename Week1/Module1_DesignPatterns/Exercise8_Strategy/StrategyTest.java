// Strategy Pattern - Payment System
// Strategy defines a family of algorithms, encapsulates each one,
// and makes them interchangeable at runtime.

// ===== Strategy Interface =====
interface PaymentStrategy {
    void pay(double amount);
}

// ===== Concrete Strategies =====
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card ending in "
                + cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " via PayPal account: " + email);
    }
}

// ===== Context Class =====
class PaymentContext {
    private PaymentStrategy strategy;

    // Strategy can be set or changed at runtime
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void executePayment(double amount) {
        if (strategy == null) {
            System.out.println("No payment strategy selected!");
            return;
        }
        strategy.pay(amount);
    }
}

// ===== Test Class =====
public class StrategyTest {
    public static void main(String[] args) {

        System.out.println("=== Strategy Pattern Demo ===\n");

        PaymentContext context = new PaymentContext();

        // Pay with Credit Card
        context.setPaymentStrategy(new CreditCardPayment("1234567890123456"));
        context.executePayment(99.99);

        // Switch to PayPal at runtime
        context.setPaymentStrategy(new PayPalPayment("user@example.com"));
        context.executePayment(49.50);

        System.out.println("\n✅ Strategy Pattern working correctly!");
    }
}
