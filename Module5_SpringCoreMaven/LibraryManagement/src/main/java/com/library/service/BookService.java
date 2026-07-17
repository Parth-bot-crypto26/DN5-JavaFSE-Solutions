package com.library.service;

import com.library.repository.Book;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Exercise 1: Configuring a Basic Spring Application
 * Exercise 2: Implementing Dependency Injection (setter injection)
 * Exercise 5: Configuring the Spring IoC Container
 * Exercise 6: Configuring Beans with Annotations (@Service + @Autowired)
 * Exercise 7: Implementing Constructor and Setter Injection
 *
 * Deliberately exposes BOTH a constructor and a setter for BookRepository so
 * the same class can be wired either way depending on which
 * applicationContext-*.xml is loaded.
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    /** No-arg constructor — required for setter-injection style XML config. */
    public BookService() {
    }

    /** Constructor injection — used by Exercise 7's constructor-arg config. */
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /** Setter injection — used by Exercise 2/5's property config. */
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookRepository getBookRepository() {
        return bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }
}
