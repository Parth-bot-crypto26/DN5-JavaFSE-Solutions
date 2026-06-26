// MVC Pattern - Student Records
// Model-View-Controller separates data (Model), display (View), and logic (Controller).

// ===== MODEL - holds data =====
class Student {
    private String name;
    private String id;
    private String grade;

    public Student(String name, String id, String grade) {
        this.name = name;
        this.id = id;
        this.grade = grade;
    }

    // Getters
    public String getName()  { return name; }
    public String getId()    { return id; }
    public String getGrade() { return grade; }

    // Setters
    public void setName(String name)   { this.name = name; }
    public void setId(String id)       { this.id = id; }
    public void setGrade(String grade) { this.grade = grade; }
}

// ===== VIEW - displays data =====
class StudentView {
    public void displayStudentDetails(String name, String id, String grade) {
        System.out.println("=== Student Details ===");
        System.out.println("Name  : " + name);
        System.out.println("ID    : " + id);
        System.out.println("Grade : " + grade);
    }
}

// ===== CONTROLLER - manages logic between Model and View =====
class StudentController {
    private Student model;
    private StudentView view;

    public StudentController(Student model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    // Update model
    public void setStudentName(String name)   { model.setName(name); }
    public void setStudentGrade(String grade) { model.setGrade(grade); }

    // Get from model
    public String getStudentName()  { return model.getName(); }
    public String getStudentGrade() { return model.getGrade(); }

    // Tell view to display
    public void updateView() {
        view.displayStudentDetails(model.getName(), model.getId(), model.getGrade());
    }
}

// ===== Test Class =====
public class MVCTest {
    public static void main(String[] args) {

        System.out.println("=== MVC Pattern Demo ===\n");

        // Create model (data)
        Student student = new Student("Alice Johnson", "STU001", "A");

        // Create view (display)
        StudentView view = new StudentView();

        // Create controller (connects model and view)
        StudentController controller = new StudentController(student, view);

        // Display initial data
        System.out.println("Initial data:");
        controller.updateView();

        System.out.println();

        // Update data through controller
        controller.setStudentName("Alice Williams");
        controller.setStudentGrade("A+");

        System.out.println("After update:");
        controller.updateView();

        System.out.println("\n✅ MVC Pattern working correctly!");
    }
}
