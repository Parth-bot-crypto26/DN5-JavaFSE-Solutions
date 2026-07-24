package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.model.Department;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * doc3: "Create a static variable DEPARTMENT_LIST, this should be populated
 * from spring xml configuration."
 */
@Repository
public class DepartmentDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentDao.class);

    private final List<Department> departmentList;

    public DepartmentDao(@Qualifier("departmentList") List<Department> departmentList) {
        LOGGER.info("Start");
        this.departmentList = departmentList;
        LOGGER.info("End");
    }

    public List<Department> getAllDepartments() {
        return departmentList;
    }
}
