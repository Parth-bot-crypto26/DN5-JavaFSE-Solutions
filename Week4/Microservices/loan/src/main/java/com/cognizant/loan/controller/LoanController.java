<<<<<<< HEAD
package com.cognizant.loan.controller;

import com.cognizant.loan.model.Loan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @GetMapping("/{number}")
    public Loan getLoan(@PathVariable String number) {
        return new Loan(18,800,"Education",number, (int) (Math.random() * 500000 + 100000));
    }

}
=======
package com.cognizant.loan.controller;

import com.cognizant.loan.model.Loan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @GetMapping("/{number}")
    public Loan getLoan(@PathVariable String number) {
        return new Loan(18,800,"Education",number, (int) (Math.random() * 500000 + 100000));
    }

}
>>>>>>> e48bf8f6c560e4f981911817f2e2c0597f5c981a
