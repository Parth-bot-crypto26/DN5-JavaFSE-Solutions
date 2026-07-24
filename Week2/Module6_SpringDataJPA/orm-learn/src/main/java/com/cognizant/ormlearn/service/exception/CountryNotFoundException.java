package com.cognizant.ormlearn.service.exception;

/**
 * Hands on 6: Find a country based on country code
 * Thrown when a lookup by country code doesn't match any row.
 */
public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String countryCode) {
        super("Country not found for code: " + countryCode);
    }
}
