package com.library.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Exercise 1: Configuring a Basic Spring Application
 * Exercise 6: Configuring Beans with Annotations (@Repository)
 *
 * An in-memory "repository" standing in for a real database, so the whole
 * module can be run without any external infrastructure.
 */
@Repository
public class BookRepository {

    private final Map<String, Book> books = new HashMap<>();

    public BookRepository() {
        // seed data so demos have something to show
        save(new Book("978-0134685991", "Effective Java", "Joshua Bloch"));
        save(new Book("978-0596007126", "Head First Design Patterns", "Freeman & Robson"));
    }

    public void save(Book book) {
        books.put(book.getIsbn(), book);
    }

    public Book findByIsbn(String isbn) {
        return books.get(isbn);
    }

    public List<Book> findAll() {
        return new ArrayList<>(books.values());
    }
}
