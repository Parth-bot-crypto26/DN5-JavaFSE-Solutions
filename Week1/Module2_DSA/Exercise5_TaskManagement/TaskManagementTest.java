// TaskManagementTest.java - Singly Linked List implementation

// ===== Task Node =====
class Task {
    int taskId;
    String taskName;
    String status;
    Task next;   // Pointer to next node - this is what makes it a linked list!

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    @Override
    public String toString() {
        return String.format("Task[ID=%d, Name=%-20s Status=%s]", taskId, taskName, status);
    }
}

// ===== Singly Linked List for Tasks =====
class TaskLinkedList {
    private Task head;  // Points to first task
    private int size = 0;

    // ADD at end - O(n) to traverse to end
    public void addTask(Task task) {
        if (head == null) {
            head = task;
        } else {
            Task current = head;
            while (current.next != null) current = current.next;
            current.next = task;
        }
        size++;
        System.out.println("✅ Added: " + task);
    }

    // SEARCH - O(n)
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) return current;
            current = current.next;
        }
        return null;
    }

    // TRAVERSE - O(n)
    public void traverseAll() {
        System.out.println("\n--- All Tasks (" + size + ") ---");
        Task current = head;
        while (current != null) {
            System.out.println("  " + current);
            current = current.next;
        }
    }

    // DELETE - O(n)
    public void deleteTask(int taskId) {
        if (head == null) { System.out.println("List is empty!"); return; }

        if (head.taskId == taskId) {
            System.out.println("✅ Deleted: " + head);
            head = head.next;
            size--;
            return;
        }

        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                System.out.println("✅ Deleted: " + current.next);
                current.next = current.next.next;  // Skip the deleted node
                size--;
                return;
            }
            current = current.next;
        }
        System.out.println("❌ Task ID " + taskId + " not found.");
    }
}

// ===== Test Class =====
public class TaskManagementTest {
    public static void main(String[] args) {

        System.out.println("=== Task Management System (Linked List) ===\n");

        TaskLinkedList taskList = new TaskLinkedList();

        taskList.addTask(new Task(1, "Design Database",   "In Progress"));
        taskList.addTask(new Task(2, "Build REST API",    "Pending"));
        taskList.addTask(new Task(3, "Write Unit Tests",  "Pending"));
        taskList.addTask(new Task(4, "Deploy to Server",  "Not Started"));

        taskList.traverseAll();

        System.out.println("\n--- Search Task ID 3 ---");
        Task found = taskList.searchTask(3);
        System.out.println(found != null ? "Found: " + found : "Not found");

        System.out.println("\n--- Delete Task ID 2 ---");
        taskList.deleteTask(2);
        taskList.traverseAll();

        System.out.println("\n=== Linked List vs Array ===");
        System.out.println("Linked List advantages:");
        System.out.println("→ Dynamic size — grows and shrinks as needed");
        System.out.println("→ Delete: O(1) once node is found (no shifting needed)");
        System.out.println("→ Insert at head: O(1)");
        System.out.println("Array advantages:");
        System.out.println("→ Random access: O(1) by index");
        System.out.println("→ Better cache performance (contiguous memory)");
    }
}
