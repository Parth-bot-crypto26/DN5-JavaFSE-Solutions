// BuilderTest.java

public class BuilderTest {
    public static void main(String[] args) {

        System.out.println("=== Builder Pattern Demo ===\n");

        // Gaming PC - with all optional parts
        Computer gamingPC = new Computer.Builder("Intel i9", "32GB", "2TB SSD")
                .GPU("NVIDIA RTX 4090")
                .bluetooth(true)
                .wifi(true)
                .build();
        System.out.println("Gaming PC:");
        gamingPC.displaySpecs();

        System.out.println();

        // Basic Office PC - minimal config
        Computer officePC = new Computer.Builder("Intel i5", "8GB", "512GB SSD")
                .wifi(true)
                .build();
        System.out.println("Office PC:");
        officePC.displaySpecs();

        System.out.println("\n✅ Builder Pattern working correctly!");
    }
}
