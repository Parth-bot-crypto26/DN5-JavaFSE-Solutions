package com.library.boot;

import com.library.boot.entity.Book;
import com.library.boot.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * Exercise 9: Creating a Spring Boot Application
 *
 * Run:  mvn spring-boot:run
 * Then try:
 *   GET    http://localhost:8080/api/books
 *   GET    http://localhost:8080/api/books/1
 *   POST   http://localhost:8080/api/books   (JSON body: {"isbn":"...","title":"...","author":"..."})
 *   PUT    http://localhost:8080/api/books/1
 *   DELETE http://localhost:8080/api/books/1
 *   H2 console: http://localhost:8080/h2-console  (JDBC URL: jdbc:h2:mem:librarydb)
 */
@SpringBootApplication
public class LibraryManagementBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementBootApplication.class, args);
    }

    /** Seed a couple of books on startup so the GET endpoints return something immediately. */
    @Bean
    CommandLineRunner seedData(BookRepository bookRepository) {
        return args -> {
            bookRepository.save(new Book("978-0134685991", "Effective Java", "Joshua Bloch"));
            bookRepository.save(new Book("978-0596007126", "Head First Design Patterns", "Freeman & Robson"));
        };
    }
}
