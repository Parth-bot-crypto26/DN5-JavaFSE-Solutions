package com.cognizant.springlearn;

import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Hands on 1: Create a Spring Web Project using Maven  ⭐ MANDATORY
 *
 * country.xml and employee.xml are imported straight into this Boot
 * application's own ApplicationContext via @ImportResource, so the REST
 * controllers can simply @Autowired the beans they need. date-format.xml
 * is loaded the way the original hands-on describes it - as a
 * standalone ClassPathXmlApplicationContext inside displayDate() - since
 * that bean isn't used anywhere else in the app.
 */
@SpringBootApplication
@ImportResource({"classpath:country.xml", "classpath:employee.xml"})
public class SpringLearnApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

    private final ApplicationContext context;

    public SpringLearnApplication(ApplicationContext context) {
        this.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringLearnApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        displayDate();
        displayCountry();
        displayCountries();
    }

    /** Hands on 2: Spring Core - Load SimpleDateFormat from Spring Configuration XML */
    public void displayDate() throws Exception {
        LOGGER.info("Start");
        ApplicationContext dateContext = new ClassPathXmlApplicationContext("date-format.xml");
        SimpleDateFormat format = dateContext.getBean("dateFormat", SimpleDateFormat.class);
        Date date = format.parse("31/12/2018");
        LOGGER.debug("{}", date);
        LOGGER.info("End");
    }

    /**
     * Hands on 4: Spring Core - Load Country from Spring Configuration XML  ⭐ MANDATORY
     * Hands on 5: Demonstration of Singleton Scope and Prototype Scope
     */
    public void displayCountry() {
        LOGGER.info("Start");

        // Hands on 4
        Country country = context.getBean("country", Country.class);
        LOGGER.debug("Country : {}", country.toString());

        // Hands on 5 - Singleton scope: same instance both times, only ONE
        // "Inside Country Constructor" log line was produced for this bean.
        Country anotherCountry = context.getBean("country", Country.class);
        LOGGER.debug("Singleton scope -> same instance? {}", country == anotherCountry);

        // Hands on 5 - Prototype scope: a fresh instance (and constructor
        // call) every single time getBean() is invoked.
        Country prototype1 = context.getBean("countryPrototype", Country.class);
        Country prototype2 = context.getBean("countryPrototype", Country.class);
        LOGGER.debug("Prototype scope -> same instance? {}", prototype1 == prototype2);

        LOGGER.info("End");
    }

    /** Hands on 6: Spring Core - Load list of countries from Spring Configuration XML */
    @SuppressWarnings("unchecked")
    public void displayCountries() {
        LOGGER.info("Start");
        List<Country> countryList = (List<Country>) context.getBean("countryList", List.class);
        countryList.forEach(c -> LOGGER.debug("{}", c));
        LOGGER.info("End");
    }
}
