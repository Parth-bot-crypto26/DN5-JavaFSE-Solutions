// Adapter Pattern - Payment Processing System
// Adapter lets incompatible interfaces work together.

// ===== Target Interface (what our app expects) =====
interface PaymentProcessor {
    void processPayment(double amount);
}

// ===== Adaptee Classes (third-party payment gateways with THEIR own methods) =====

class PayPalGateway {
    // PayPal uses its own method name
    public void makePayment(double amount) {
        System.out.println("Processing $" + amount + " via PayPal.");
    }
}

class StripeGateway {
    // Stripe uses a different method name
    public void charge(double amount) {
        System.out.println("Charging $" + amount + " via Stripe.");
    }
}

// ===== Adapter Classes (bridge between our interface and their interface) =====

class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPal;

    public PayPalAdapter(PayPalGateway payPal) {
        this.payPal = payPal;
    }

    @Override
    public void processPayment(double amount) {
        // Translate our method call to PayPal's method
        payPal.makePayment(amount);
    }
}

class StripeAdapter implements PaymentProcessor {
    private StripeGateway stripe;

    public StripeAdapter(StripeGateway stripe) {
        this.stripe = stripe;
    }

    @Override
    public void processPayment(double amount) {
        // Translate our method call to Stripe's method
        stripe.charge(amount);
    }
}

// ===== Test Class =====
public class AdapterTest {
    public static void main(String[] args) {

        System.out.println("=== Adapter Pattern Demo ===\n");

        // Use PayPal through the adapter
        PaymentProcessor paypal = new PayPalAdapter(new PayPalGateway());
        paypal.processPayment(150.00);

        // Use Stripe through the adapter
        PaymentProcessor stripe = new StripeAdapter(new StripeGateway());
        stripe.processPayment(75.50);

        System.out.println("\n✅ Adapter Pattern working correctly!");
    }
}
