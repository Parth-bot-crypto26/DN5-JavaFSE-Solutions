package com.cognizant.springlearn.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hands on 4: Spring Core - Load Country from Spring Configuration XML  ⭐ MANDATORY
 *
 * Instance variables, empty constructor, and every getter/setter log a debug
 * message so Hands on 5 (Singleton vs Prototype scope) can be observed just
 * by watching the console - the constructor log line tells you exactly how
 * many instances Spring actually created.
 */
public class Country {

    private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

    private String code;
    private String name;

    public Country() {
        LOGGER.debug("Inside Country Constructor");
    }

    public String getCode() {
        LOGGER.debug("getCode() called");
        return code;
    }

    public void setCode(String code) {
        LOGGER.debug("setCode({})", code);
        this.code = code;
    }

    public String getName() {
        LOGGER.debug("getName() called");
        return name;
    }

    public void setName(String name) {
        LOGGER.debug("setName({})", name);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Country{code='" + code + "', name='" + name + "'}";
    }
}
