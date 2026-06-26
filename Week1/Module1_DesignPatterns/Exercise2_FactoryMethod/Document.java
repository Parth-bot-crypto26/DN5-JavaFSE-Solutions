// Document.java - The common interface for all document types
// Every document type (Word, PDF, Excel) will implement this

public interface Document {
    void open();
    void close();
    String getType();
}
