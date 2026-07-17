package com.cognizant.ormlearn.service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Hands on 1: Spring Data JPA - Quick Example        -> getAllCountries()
 * Hands on 6: Find a country based on country code    -> findCountryByCode()
 * Hands on 7: Add a new country                       -> addCountry()
 * Hands on 8: Update a country based on code           -> updateCountry()
 * Hands on 9: Delete a country based on code           -> deleteCountry()
 * doc2 Hands on 1: Query Methods for country           -> searchByPartialName(), searchByPartialNameSorted(), findByStartingLetter()
 */
@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryRepository countryRepository;

    @Transactional
    public List<Country> getAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryRepository.findAll();
        LOGGER.debug("countries.size={}", countries.size());
        LOGGER.info("End");
        return countries;
    }

    @Transactional
    public Country findCountryByCode(String countryCode) {
        Optional<Country> result = countryRepository.findById(countryCode);
        if (!result.isPresent()) {
            throw new CountryNotFoundException(countryCode);
        }
        return result.get();
    }

    @Transactional
    public void addCountry(Country country) {
        countryRepository.save(country);
    }

    @Transactional
    public void updateCountry(String code, String name) {
        Country country = findCountryByCode(code);
        country.setName(name);
        countryRepository.save(country);
    }

    @Transactional
    public void deleteCountry(String code) {
        countryRepository.deleteById(code);
    }

    @Transactional
    public List<Country> searchByPartialName(String text) {
        return countryRepository.findByNameContaining(text);
    }

    @Transactional
    public List<Country> searchByPartialNameSorted(String text) {
        return countryRepository.findByNameContainingOrderByNameAsc(text);
    }

    @Transactional
    public List<Country> findByStartingLetter(String letter) {
        return countryRepository.findByNameStartingWith(letter);
    }
}
