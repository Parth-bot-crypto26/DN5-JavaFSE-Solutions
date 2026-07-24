package com.cognizant.springlearn.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * "REST - Get country exceptional scenario"
 *
 * @ResponseStatus turns any uncaught instance of this exception into an
 * HTTP 404 response with the given reason, without the controller needing
 * a try/catch or @ExceptionHandler.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Country not found")
public class CountryNotFoundException extends RuntimeException {

    public CountryNotFoundException(String code) {
        super("Country not found for code: " + code);
    }
}
