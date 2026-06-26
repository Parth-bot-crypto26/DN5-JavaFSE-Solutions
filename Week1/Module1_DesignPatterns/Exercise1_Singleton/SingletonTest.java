// SingletonTest.java - Test class for Singleton Pattern

public class SingletonTest {

    public static void main(String[] args) {

        // Get two references to Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Use them
        logger1.log("Application started.");
        logger2.log("User logged in.");

        // Verify both references point to the SAME instance
        if (logger1 == logger2) {
            System.out.println("✅ SUCCESS: Both logger1 and logger2 are the SAME instance!");
            System.out.println("   logger1 hashCode: " + logger1.hashCode());
            System.out.println("   logger2 hashCode: " + logger2.hashCode());
        } else {
            System.out.println("❌ FAILED: Different instances created!");
        }
    }
}
