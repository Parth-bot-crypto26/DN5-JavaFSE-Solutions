// DocumentFactory.java - Abstract Factory class
// Defines the factory method that subclasses must implement

public abstract class DocumentFactory {

    // THE factory method - subclasses decide WHICH document to create
    public abstract Document createDocument();

    // Common method all factories can use
    public void openDocument() {
        Document doc = createDocument();
        System.out.println("Factory created: " + doc.getType());
        doc.open();
    }
}

// ---- Concrete Factories (each creates one type of document) ----

class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}
