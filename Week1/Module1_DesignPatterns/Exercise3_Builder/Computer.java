// Computer.java - Builder Pattern
// Builder Pattern is used to construct complex objects step by step.
// It lets you create different configurations of an object.

public class Computer {

    // All attributes of Computer (some may be optional)
    private String CPU;       // Required
    private String RAM;       // Required
    private String storage;   // Required
    private String GPU;       // Optional
    private boolean bluetoothEnabled;  // Optional
    private boolean wifiEnabled;       // Optional

    // Private constructor - only Builder can call this
    private Computer(Builder builder) {
        this.CPU = builder.CPU;
        this.RAM = builder.RAM;
        this.storage = builder.storage;
        this.GPU = builder.GPU;
        this.bluetoothEnabled = builder.bluetoothEnabled;
        this.wifiEnabled = builder.wifiEnabled;
    }

    // Display computer specs
    public void displaySpecs() {
        System.out.println("=== Computer Specifications ===");
        System.out.println("CPU     : " + CPU);
        System.out.println("RAM     : " + RAM);
        System.out.println("Storage : " + storage);
        System.out.println("GPU     : " + (GPU != null ? GPU : "Integrated"));
        System.out.println("Bluetooth: " + (bluetoothEnabled ? "Yes" : "No"));
        System.out.println("WiFi    : " + (wifiEnabled ? "Yes" : "No"));
    }

    // ===== Static nested Builder class =====
    public static class Builder {
        // Required
        private String CPU;
        private String RAM;
        private String storage;
        // Optional
        private String GPU;
        private boolean bluetoothEnabled = false;
        private boolean wifiEnabled = false;

        // Constructor with required fields
        public Builder(String CPU, String RAM, String storage) {
            this.CPU = CPU;
            this.RAM = RAM;
            this.storage = storage;
        }

        // Each method sets an optional field and returns the Builder (for chaining)
        public Builder GPU(String GPU) {
            this.GPU = GPU;
            return this;
        }

        public Builder bluetooth(boolean enabled) {
            this.bluetoothEnabled = enabled;
            return this;
        }

        public Builder wifi(boolean enabled) {
            this.wifiEnabled = enabled;
            return this;
        }

        // Final method - builds and returns the Computer object
        public Computer build() {
            return new Computer(this);
        }
    }
}
