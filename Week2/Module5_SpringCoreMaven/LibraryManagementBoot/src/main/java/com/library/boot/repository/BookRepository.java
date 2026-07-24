package com.library.boot.repository;

import com.library.boot.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Exercise 9: Creating a Spring Boot Application
 * "Define Entities and Repositories: ... and BookRepository interface."
 *
 * Extending JpaRepository gives us save/findAll/findById/delete/etc. for
 * free - no implementation needed.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByIsbn(String isbn);
}
