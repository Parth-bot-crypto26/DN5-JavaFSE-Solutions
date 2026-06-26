// Proxy Pattern - Image Viewer with Lazy Loading
// Proxy controls access to an object and can add caching/lazy loading.

// ===== Subject Interface =====
interface Image {
    void display();
}

// ===== Real Subject (expensive to create) =====
class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromServer();  // Expensive operation!
    }

    private void loadFromServer() {
        System.out.println("Loading image from remote server: " + filename);
    }

    @Override
    public void display() {
        System.out.println("Displaying: " + filename);
    }
}

// ===== Proxy Class (lazy loading + caching) =====
class ProxyImage implements Image {
    private String filename;
    private RealImage realImage;  // Starts as null - not loaded yet

    public ProxyImage(String filename) {
        this.filename = filename;
        // Note: We do NOT load here - that's the point of lazy loading!
    }

    @Override
    public void display() {
        // Only load when actually needed (lazy initialization)
        if (realImage == null) {
            realImage = new RealImage(filename);  // Load only once
        } else {
            System.out.println("(Using cached image - no server call needed)");
        }
        realImage.display();
    }
}

// ===== Test Class =====
public class ProxyTest {
    public static void main(String[] args) {

        System.out.println("=== Proxy Pattern Demo ===\n");

        // Create proxy - image NOT loaded yet
        Image image = new ProxyImage("vacation_photo.jpg");
        System.out.println("ProxyImage created. Image not loaded yet.");

        System.out.println();

        // First display - triggers loading
        System.out.println("First display call:");
        image.display();

        System.out.println();

        // Second display - uses cache, NO server call
        System.out.println("Second display call:");
        image.display();

        System.out.println("\n✅ Proxy Pattern working correctly!");
    }
}
