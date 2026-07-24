package com.library;

import com.library.repository.Book;
import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercise 3: Implementing Logging with Spring AOP
 * Exercise 8: Implementing Basic AOP with Spring
 *
 * Loads applicationContext-aop.xml, which enables AspectJ auto-proxying and
 * registers LoggingAspect. Every BookService call below is wrapped by the
 * aspect's before/after/around advice - watch the console for [AOP] lines.
 *
 * Run:  mvn compile exec:java -Dexec.mainClass=com.library.AopLoggingDemo
 */
public class AopLoggingDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-aop.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        bookService.addBook(new Book("978-0135166307", "Clean Code", "Robert C. Martin"));
        bookService.getAllBooks();
        bookService.getBookByIsbn("978-0135166307");
    }
}
