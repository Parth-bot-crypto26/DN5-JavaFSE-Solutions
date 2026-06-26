// Observer Pattern - Stock Market
// Observer defines a one-to-many dependency:
// when one object (subject) changes, all its dependents (observers) are notified.

import java.util.ArrayList;
import java.util.List;

// ===== Observer Interface =====
interface Observer {
    void update(String stockName, double price);
}

// ===== Subject Interface =====
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

// ===== Concrete Subject =====
class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockName;
    private double price;

    public StockMarket(String stockName) {
        this.stockName = stockName;
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println("Observer registered for " + stockName);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer deregistered from " + stockName);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }

    // When price changes, notify everyone
    public void setPrice(double price) {
        System.out.println("\n" + stockName + " price changed to $" + price);
        this.price = price;
        notifyObservers();
    }
}

// ===== Concrete Observers =====
class MobileApp implements Observer {
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    @Override
    public void update(String stockName, double price) {
        System.out.println("[" + appName + " App] Alert: " + stockName + " is now $" + price);
    }
}

class EmailAlertService implements Observer {
    @Override
    public void update(String stockName, double price) {
        System.out.println("[Email Alert] " + stockName + " price update: $" + price);
    }
}

// ===== Test Class =====
public class ObserverTest {
    public static void main(String[] args) {

        System.out.println("=== Observer Pattern Demo ===\n");

        // Create the stock
        StockMarket appleStock = new StockMarket("AAPL");

        // Create observers
        Observer mobileUser1 = new MobileApp("StockTracker");
        Observer mobileUser2 = new MobileApp("WealthApp");
        Observer emailService = new EmailAlertService();

        // Register observers
        appleStock.registerObserver(mobileUser1);
        appleStock.registerObserver(mobileUser2);
        appleStock.registerObserver(emailService);

        // Price changes - all observers get notified
        appleStock.setPrice(175.50);
        appleStock.setPrice(180.25);

        // Deregister one observer
        System.out.println();
        appleStock.deregisterObserver(mobileUser2);
        appleStock.setPrice(165.00);  // Only 2 observers now

        System.out.println("\n✅ Observer Pattern working correctly!");
    }
}
