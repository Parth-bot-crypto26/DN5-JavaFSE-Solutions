// LibrarySearchTest.java - Linear and Binary search on Books

// ===== Book Model =====
class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId()   { return bookId; }
    public String getTitle() { return title; }

    @Override
    public String toString() {
        return String.format("Book[ID=%d, Title=%-30s Author=%s]", bookId, title, author);
    }
}

// ===== Library Search =====
class LibrarySearch {

    // Linear Search by title - O(n)
    public static Book linearSearchByTitle(Book[] books, String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) return book;
        }
        return null;
    }

    // Binary Search by bookId - O(log n) - array must be sorted by bookId
    public static Book binarySearchById(Book[] sortedBooks, int targetId) {
        int left = 0, right = sortedBooks.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (sortedBooks[mid].getBookId() == targetId) return sortedBooks[mid];
            else if (sortedBooks[mid].getBookId() < targetId) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }
}

// ===== Test Class =====
public class LibrarySearchTest {
    public static void main(String[] args) {

        System.out.println("=== Library Management System ===\n");

        Book[] books = {
            new Book(3, "Clean Code",           "Robert C. Martin"),
            new Book(1, "The Pragmatic Programmer", "David Thomas"),
            new Book(5, "Design Patterns",      "Gang of Four"),
            new Book(2, "Effective Java",       "Joshua Bloch"),
            new Book(4, "Refactoring",          "Martin Fowler"),
        };

        // Sorted version for binary search
        Book[] sortedBooks = {
            new Book(1, "The Pragmatic Programmer", "David Thomas"),
            new Book(2, "Effective Java",       "Joshua Bloch"),
            new Book(3, "Clean Code",           "Robert C. Martin"),
            new Book(4, "Refactoring",          "Martin Fowler"),
            new Book(5, "Design Patterns",      "Gang of Four"),
        };

        // Linear search by title
        System.out.println("--- Linear Search by title: 'Clean Code' ---");
        Book found1 = LibrarySearch.linearSearchByTitle(books, "Clean Code");
        System.out.println(found1 != null ? "Found: " + found1 : "Not found");

        // Binary search by ID
        System.out.println("\n--- Binary Search by ID: 4 ---");
        Book found2 = LibrarySearch.binarySearchById(sortedBooks, 4);
        System.out.println(found2 != null ? "Found: " + found2 : "Not found");

        System.out.println("\n=== When to use which? ===");
        System.out.println("Linear Search → Small datasets, unsorted data, search by any field");
        System.out.println("Binary Search → Large datasets, sorted data, search by indexed field");
    }
}
