package com.cognizant.springlearn.service;

import com.cognizant.springlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * "REST - Get country based on country code"  ⭐ MANDATORY
 * "REST - Get country exceptional scenario"
 *
 * getCountry(code): case-insensitive match against the countryList bean
 * (built in country.xml), using a lambda instead of a manual loop, as the
 * doc calls out as an alternative approach.
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    private final Country india;
    private final List<Country> countryList;

    public CountryService(@Qualifier("country") Country india,
                           @Qualifier("countryList") List<Country> countryList) {
        this.india = india;
        this.countryList = countryList;
    }

    /** "REST - Country Web Service": getCountryIndia() */
    public Country getCountryIndia() {
        return india;
    }

    /** "REST - Get all countries" */
    public List<Country> getAllCountries() {
        return countryList;
    }

    /** "REST - Get country based on country code": case-insensitive match via lambda/stream. */
    public Country getCountry(String code) {
        LOGGER.info("Start");
        Country match = countryList.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException(code));
        LOGGER.debug("Matched: {}", match);
        LOGGER.info("End");
        return match;
    }
}
