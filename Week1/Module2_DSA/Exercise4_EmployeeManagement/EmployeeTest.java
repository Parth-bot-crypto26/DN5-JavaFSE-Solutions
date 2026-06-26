// Employee.java + EmployeeManager.java + EmployeeTest.java

// ===== Employee Model =====
class Employee {
    private int employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(int employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public int getEmployeeId() { return employeeId; }

    @Override
    public String toString() {
        return String.format("Employee[ID=%d, Name=%-15s Position=%-15s Salary=$%.2f]",
                employeeId, name, position, salary);
    }
}

// ===== Employee Manager using Array =====
class EmployeeManager {
    private Employee[] employees;
    private int size = 0;
    private int capacity;

    public EmployeeManager(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
    }

    // ADD - O(1) if space available
    public void addEmployee(Employee emp) {
        if (size >= capacity) {
            System.out.println("❌ Array full! Cannot add more employees.");
            return;
        }
        employees[size++] = emp;
        System.out.println("✅ Added: " + emp);
    }

    // SEARCH - O(n) linear search through array
    public Employee searchEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) return employees[i];
        }
        return null;
    }

    // TRAVERSE - O(n)
    public void traverseAll() {
        System.out.println("\n--- All Employees (" + size + ") ---");
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    // DELETE - O(n) — must shift elements after deletion
    public void deleteEmployee(int id) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId() == id) {
                String name = employees[i].toString();
                // Shift all elements left
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                System.out.println("✅ Deleted employee ID " + id);
                return;
            }
        }
        System.out.println("❌ Employee ID " + id + " not found.");
    }
}

// ===== Test Class =====
public class EmployeeTest {
    public static void main(String[] args) {

        System.out.println("=== Employee Management System (Array) ===\n");

        EmployeeManager manager = new EmployeeManager(10);

        manager.addEmployee(new Employee(1, "Alice",   "Developer",  85000));
        manager.addEmployee(new Employee(2, "Bob",     "Designer",   72000));
        manager.addEmployee(new Employee(3, "Charlie", "Manager",    110000));
        manager.addEmployee(new Employee(4, "Diana",   "QA Engineer",68000));

        manager.traverseAll();

        System.out.println("\n--- Search for ID 3 ---");
        Employee found = manager.searchEmployee(3);
        System.out.println(found != null ? "Found: " + found : "Not found");

        System.out.println("\n--- Delete Employee ID 2 ---");
        manager.deleteEmployee(2);
        manager.traverseAll();

        System.out.println("\n=== Time Complexity (Array) ===");
        System.out.println("Add      : O(1)  — insert at end");
        System.out.println("Search   : O(n)  — must scan all elements");
        System.out.println("Traverse : O(n)  — visit every element");
        System.out.println("Delete   : O(n)  — find + shift elements left");
        System.out.println("\nArray Limitation: Fixed size. Use ArrayList for dynamic resizing.");
    }
}
