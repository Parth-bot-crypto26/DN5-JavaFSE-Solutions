package com.cognizant.greet.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/** "Create a controller as shown below" -> returns "Hello World" */
@RestController
public class GreetController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetController.class);

    @GetMapping("/greet")
    public String greet() {
        LOGGER.info("Start");
        String result = "Hello World";
        LOGGER.info("End");
        return result;
    }
}
