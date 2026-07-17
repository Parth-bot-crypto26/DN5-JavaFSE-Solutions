package com.cognizant.account.controller;

import com.cognizant.account.model.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * "Account Microservice"  ⭐ MANDATORY (Creating Microservices for account and loan)
 *
 * Method: GET
 * Endpoint: /accounts/{number}
 * Sample Response (dummy, no backend): { "number": "00987987973432", "type": "savings", "balance": 234343 }
 */
@RestController
public class AccountController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

    @GetMapping("/accounts/{number}")
    public Account getAccount(@PathVariable String number) {
        LOGGER.info("Start");
        LOGGER.debug("number={}", number);
        Account account = new Account(number, "savings", 234343);
        LOGGER.info("End");
        return account;
    }
}
