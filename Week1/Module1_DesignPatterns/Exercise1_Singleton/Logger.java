// Logger.java - Singleton Pattern
// The Singleton Pattern ensures that a class has only ONE instance
// and provides a global point of access to it.

public class Logger {

    // Step 1: Private static instance of itself
    // 'volatile' ensures thread safety in multi-threaded environments
    private static volatile Logger instance;

    // Step 2: Private constructor - no one outside can call new Logger()
    private Logger() {
        System.out.println("Logger instance created.");
    }

    // Step 3: Public static method to get the single instance
    // This is called "Double-Checked Locking" - thread-safe and efficient
    public static Logger getInstance() {
        if (instance == null) {                    // First check (no locking)
            synchronized (Logger.class) {          // Lock for thread safety
                if (instance == null) {            // Second check (with locking)
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    // Actual logging method
    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
