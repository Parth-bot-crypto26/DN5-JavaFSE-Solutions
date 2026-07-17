package com.library;

import com.library.service.BookService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Exercise 7: Implementing Constructor and Setter Injection
 *
 * Loads applicationContext-constructor.xml and pulls out both
 * bookServiceConstructor and bookServiceSetter to prove each wiring style
 * independently produces a fully-usable bean.
 *
 * Run:  mvn compile exec:java -Dexec.mainClass=com.library.ConstructorSetterInjectionDemo
 */
public class ConstructorSetterInjectionDemo {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-constructor.xml");

        BookService constructorInjected = context.getBean("bookServiceConstructor", BookService.class);
        BookService setterInjected = context.getBean("bookServiceSetter", BookService.class);

        System.out.println("Constructor-injected bean repository: " + constructorInjected.getBookRepository());
        System.out.println("Setter-injected bean repository:      " + setterInjected.getBookRepository());

        System.out.println("Constructor-injected bean books: " + constructorInjected.getAllBooks());
        System.out.println("Setter-injected bean books:      " + setterInjected.getAllBooks());
    }
}
