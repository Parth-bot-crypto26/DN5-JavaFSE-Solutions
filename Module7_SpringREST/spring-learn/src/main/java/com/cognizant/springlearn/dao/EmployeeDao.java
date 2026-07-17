package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * doc3: "In EmployeeDao, incorporate the following: create static variable
 * EMPLOYEE_LIST of type ArrayList<Employee>, include constructor that reads
 * employee list from xml config, create method getAllEmployees()."
 *
 * "employeeList" is the bean built up in employee.xml (imported into this
 * Spring Boot app via @ImportResource on SpringLearnApplication) - the
 * constructor below is that same "reads from xml config" step, just done
 * through DI instead of a manual ClassPathXmlApplicationContext lookup.
 */
@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private final List<Employee> employeeList;

    public EmployeeDao(@Qualifier("employeeList") List<Employee> employeeList) {
        LOGGER.info("Start");
        this.employeeList = employeeList;
        LOGGER.info("End");
    }

    public List<Employee> getAllEmployees() {
        return employeeList;
    }
}
