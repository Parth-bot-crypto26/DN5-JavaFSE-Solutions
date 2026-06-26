// Dependency Injection - Customer Management
// DI means: instead of a class creating its own dependencies,
// they are INJECTED from outside. This makes code flexible and testable.

// ===== Repository Interface =====
interface CustomerRepository {
    String findCustomerById(int id);
}

// ===== Concrete Repository =====
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // In a real app, this would query a database
        if (id == 1) return "Alice Johnson";
        if (id == 2) return "Bob Smith";
        return "Customer not found";
    }
}

// ===== Service Class (depends on Repository) =====
class CustomerService {
    private CustomerRepository repository;

    // Constructor Injection - dependency is passed in constructor
    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public void displayCustomer(int id) {
        String customerName = repository.findCustomerById(id);
        System.out.println("Customer #" + id + ": " + customerName);
    }
}

// ===== Test Class =====
public class DependencyInjectionTest {
    public static void main(String[] args) {

        System.out.println("=== Dependency Injection Demo ===\n");

        // Create the dependency
        CustomerRepository repository = new CustomerRepositoryImpl();

        // INJECT it into the service (Constructor Injection)
        CustomerService service = new CustomerService(repository);

        // Use the service
        service.displayCustomer(1);
        service.displayCustomer(2);
        service.displayCustomer(99);

        System.out.println("\n✅ Dependency Injection working correctly!");
        System.out.println("Note: CustomerService doesn't create its own repository.");
        System.out.println("It receives it from outside - that's Dependency Injection!");
    }
}
