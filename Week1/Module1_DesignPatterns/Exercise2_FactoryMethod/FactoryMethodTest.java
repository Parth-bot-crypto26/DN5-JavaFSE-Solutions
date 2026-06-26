// FactoryMethodTest.java - Test the Factory Method Pattern

public class FactoryMethodTest {

    public static void main(String[] args) {

        System.out.println("=== Factory Method Pattern Demo ===\n");

        // Create Word Document using its factory
        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open();
        wordDoc.close();

        System.out.println();

        // Create PDF Document using its factory
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open();
        pdfDoc.close();

        System.out.println();

        // Create Excel Document using its factory
        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open();
        excelDoc.close();

        System.out.println();

        // Using the factory's openDocument() helper method
        System.out.println("--- Using openDocument() helper ---");
        wordFactory.openDocument();

        System.out.println("\n✅ Factory Method Pattern working correctly!");
    }
}
