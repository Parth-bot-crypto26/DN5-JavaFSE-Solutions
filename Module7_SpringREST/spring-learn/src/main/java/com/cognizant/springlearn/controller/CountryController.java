package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.CountryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * "REST - Country Web Service"                       -> getCountryIndia()  ⭐ MANDATORY (quick example)
 * "REST - Get all countries"                         -> getAllCountries()
 * "REST - Get country based on country code"          -> getCountry()      ⭐ MANDATORY
 * "REST - Get country exceptional scenario"           -> handled by CountryService + CountryNotFoundException
 */
@RestController
public class CountryController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

    @Autowired
    private CountryService countryService;

    public CountryController() {
        LOGGER.debug("Inside CountryController Constructor");
    }

    /** Sample Request: http://localhost:8083/country -> {"code":"IN","name":"India"} */
    @GetMapping("/country")
    public Country getCountryIndia() {
        LOGGER.info("Start");
        Country country = countryService.getCountryIndia();
        LOGGER.info("End");
        return country;
    }

    /** Sample Request: http://localhost:8083/countries -> array of all four countries */
    @GetMapping("/countries")
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.info("End");
        return countries;
    }

    /**
     * Sample Request: http://localhost:8083/countries/in  (case-insensitive)
     * Throws CountryNotFoundException (-> HTTP 404) if the code doesn't match anything.
     */
    @GetMapping("/countries/{code}")
    public Country getCountry(@PathVariable String code) {
        LOGGER.info("Start");
        Country country = countryService.getCountry(code);
        LOGGER.info("End");
        return country;
    }
}
