package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * doc3: "In EmployeeService, incorporate the following: change the
 * annotation for this class from @Component to @Service. Create method
 * getAllEmployees() ... Define @Transactional annotation for this method."
 */
@Service
public class EmployeeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeService.class);

    @Autowired
    private EmployeeDao employeeDao;

    @Transactional
    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        List<Employee> employees = employeeDao.getAllEmployees();
        LOGGER.info("End");
        return employees;
    }
}
