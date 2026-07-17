package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercise 1: Configuring a Basic Spring Application
 * Exercise 2: Implementing Dependency Injection
 * Exercise 5: Configuring the Spring IoC Container
 *
 * Loads applicationContext.xml, lets the IoC container build and wire the
 * beans, then exercises the result.
 *
 * Run:  mvn compile exec:java -Dexec.mainClass=com.library.LibraryManagementApplication
 */
public class LibraryManagementApplication {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        BookService bookService = context.getBean("bookService", BookService.class);

        System.out.println("bookService.getBookRepository() = " + bookService.getBookRepository());
        System.out.println("All books:");
        bookService.getAllBooks().forEach(System.out::println);

        System.out.println("Lookup by ISBN 978-0134685991: " + bookService.getBookByIsbn("978-0134685991"));
    }
}
