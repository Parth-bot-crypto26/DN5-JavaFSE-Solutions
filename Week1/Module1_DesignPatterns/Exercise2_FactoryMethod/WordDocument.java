// WordDocument.java
public class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Word Document (.docx)");
    }

    @Override
    public void close() {
        System.out.println("Closing Word Document");
    }

    @Override
    public String getType() {
        return "Word Document";
    }
}
