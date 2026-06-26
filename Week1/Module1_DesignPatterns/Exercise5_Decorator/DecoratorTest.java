// Decorator Pattern - Notification System
// Decorator adds new behaviors to objects without modifying their class.

// ===== Component Interface =====
interface Notifier {
    void send(String message);
}

// ===== Concrete Component =====
class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

// ===== Abstract Decorator =====
abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier notifier) {
        this.wrappedNotifier = notifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);  // Delegate to wrapped notifier
    }
}

// ===== Concrete Decorators =====
class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);            // Send via base notifier
        System.out.println("Sending SMS: " + message);  // ALSO send via SMS
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier notifier) {
        super(notifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("Sending SLACK message: " + message);
    }
}

// ===== Test Class =====
public class DecoratorTest {
    public static void main(String[] args) {

        System.out.println("=== Decorator Pattern Demo ===\n");

        // Just email
        System.out.println("-- Email only --");
        Notifier emailOnly = new EmailNotifier();
        emailOnly.send("Server is down!");

        System.out.println();

        // Email + SMS
        System.out.println("-- Email + SMS --");
        Notifier emailAndSMS = new SMSNotifierDecorator(new EmailNotifier());
        emailAndSMS.send("Server is down!");

        System.out.println();

        // Email + SMS + Slack (stacking decorators)
        System.out.println("-- Email + SMS + Slack --");
        Notifier allChannels = new SlackNotifierDecorator(
                                new SMSNotifierDecorator(
                                 new EmailNotifier()));
        allChannels.send("Server is down!");

        System.out.println("\n✅ Decorator Pattern working correctly!");
    }
}
