package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Hands on 1: Spring Data JPA - Quick Example (JpaRepository<Country, String>)
 *
 * doc2 Hands on 1: Write queries on country table using Query Methods
 * - search by containing text
 * - same search, sorted ascending
 * - search by starting letter
 */
@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    /** e.g. findByNameContaining("ou") -> every country whose name contains "ou" */
    List<Country> findByNameContaining(String text);

    /** Same search, but ordered by name ascending. */
    List<Country> findByNameContainingOrderByNameAsc(String text);

    /** e.g. findByNameStartingWith("Z") -> Zambia, Zimbabwe */
    List<Country> findByNameStartingWith(String letter);
}
