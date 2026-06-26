// ExcelDocument.java
public class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening Excel Document (.xlsx)");
    }

    @Override
    public void close() {
        System.out.println("Closing Excel Document");
    }

    @Override
    public String getType() {
        return "Excel Document";
    }
}
