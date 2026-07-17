package com.cognizant.ormlearn;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Hands on 1: Spring Data JPA - Quick Example
 *
 * Every test*() method below corresponds to one hands-on exercise from the
 * three reference docs. They run in order via CommandLineRunner so the whole
 * module can be exercised with a single `mvn spring-boot:run`.
 */
@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    @Autowired
    private CountryService countryService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
        LOGGER.info("Inside main");
    }

    @Override
    public void run(String... args) {
        testGetAllCountries();          // Hands on 1
        testFindCountryByCode();        // Hands on 6
        testAddCountry();               // Hands on 7
        testUpdateCountry();            // Hands on 8
        testDeleteCountry();            // Hands on 9
        testQueryMethods();             // doc2 Hands on 1
        testGetEmployeeWithDepartment(); // doc2 Hands on 4 (O/R Mapping: @ManyToOne)
        testGetDepartmentWithEmployees(); // doc2 Hands on 5 (O/R Mapping: @OneToMany)
        testGetEmployeeWithSkills();     // doc2 Hands on 6 (O/R Mapping: @ManyToMany)
        testGetAllPermanentEmployeesHQL(); // doc3 Hands on 2
        testGetAverageSalaryHQL();       // doc3 Hands on 4
        testGetAllEmployeesNativeQuery(); // doc3 Hands on 5
    }

    /** Hands on 1: Spring Data JPA - Quick Example */
    private void testGetAllCountries() {
        LOGGER.info("Start");
        List<Country> countries = countryService.getAllCountries();
        LOGGER.debug("countries.size={}", countries.size());
        LOGGER.info("End");
    }

    /** Hands on 6: Find a country based on country code */
    private void testFindCountryByCode() {
        LOGGER.info("Start");
        Country country = countryService.findCountryByCode("IN");
        LOGGER.debug("Country:{}", country);

        try {
            countryService.findCountryByCode("ZZ");
        } catch (CountryNotFoundException ex) {
            LOGGER.debug("Expected exception for unknown code: {}", ex.getMessage());
        }
        LOGGER.info("End");
    }

    /** Hands on 7: Add a new country */
    private void testAddCountry() {
        LOGGER.info("Start");
        Country newCountry = new Country("XX", "Testlandia");
        countryService.addCountry(newCountry);
        Country fetched = countryService.findCountryByCode("XX");
        LOGGER.debug("Added and fetched:{}", fetched);
        LOGGER.info("End");
    }

    /** Hands on 8: Update a country based on code */
    private void testUpdateCountry() {
        LOGGER.info("Start");
        countryService.updateCountry("XX", "Testlandia (Updated)");
        Country updated = countryService.findCountryByCode("XX");
        LOGGER.debug("After update:{}", updated);
        LOGGER.info("End");
    }

    /** Hands on 9: Delete a country based on code */
    private void testDeleteCountry() {
        LOGGER.info("Start");
        countryService.deleteCountry("XX");
        try {
            countryService.findCountryByCode("XX");
        } catch (CountryNotFoundException ex) {
            LOGGER.debug("Confirmed deleted: {}", ex.getMessage());
        }
        LOGGER.info("End");
    }

    /** doc2 Hands on 1: Query Methods on the country table */
    private void testQueryMethods() {
        LOGGER.info("Start");
        LOGGER.debug("Contains 'ou': {}", countryService.searchByPartialName("ou"));
        LOGGER.debug("Contains 'ou' sorted: {}", countryService.searchByPartialNameSorted("ou"));
        LOGGER.debug("Starting with 'Z': {}", countryService.findByStartingLetter("Z"));
        LOGGER.info("End");
    }

    /** doc2 Hands on 4: @ManyToOne mapping - get an employee along with department */
    private void testGetEmployeeWithDepartment() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.info("End");
    }

    /** doc2 Hands on 5: @OneToMany mapping - get a department along with its employees */
    private void testGetDepartmentWithEmployees() {
        LOGGER.info("Start");
        LOGGER.debug("Department 1 employees:{}", departmentService.get(1).getEmployeeList());
        LOGGER.info("End");
    }

    /** doc2 Hands on 6: @ManyToMany mapping - get an employee along with skills */
    private void testGetEmployeeWithSkills() {
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    /** doc3 Hands on 2: Get all permanent employees using HQL (with fetch joins) */
    private void testGetAllPermanentEmployeesHQL() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        employees.forEach(e -> LOGGER.debug("Permanent employee:{} skills:{}", e, e.getSkillList()));
        LOGGER.info("End");
    }

    /** doc3 Hands on 4: Get average salary using HQL aggregate function */
    private void testGetAverageSalaryHQL() {
        LOGGER.info("Start");
        LOGGER.debug("Average salary (all): {}", employeeService.getAverageSalary());
        LOGGER.debug("Average salary (dept 1): {}", employeeService.getAverageSalaryByDepartment(1));
        LOGGER.info("End");
    }

    /** doc3 Hands on 5: Get all employees using Native Query */
    private void testGetAllEmployeesNativeQuery() {
        LOGGER.info("Start");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("Native query employee count: {}", employees.size());
        LOGGER.info("End");
    }
}
