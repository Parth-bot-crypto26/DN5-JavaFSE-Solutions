package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercise 6: Configuring Beans with Annotations
 *
 * Loads applicationContext-annotations.xml, which relies on
 * <context:component-scan> to find @Service/@Repository classes instead of
 * declaring every <bean> by hand.
 *
 * Run:  mvn compile exec:java -Dexec.mainClass=com.library.AnnotationConfigDemo
 */
public class AnnotationConfigDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-annotations.xml");

        BookService bookService = context.getBean(BookService.class);

        System.out.println("Resolved BookService via component-scan: " + bookService);
        System.out.println("All books:");
        bookService.getAllBooks().forEach(System.out::println);
    }
}
