package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * doc3 Hands on 2: Get all permanent employees using HQL
 * doc3 Hands on 4: Get average salary using HQL
 * doc3 Hands on 5: Get all employees using Native Query
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * HQL (Hibernate Query Language) - note it addresses the Employee class
     * and its fields, not the "employee" table/columns. 'fetch' is required
     * on the joins so department and skillList are actually populated in one
     * query instead of triggering N+1 lazy-load queries afterward.
     */
    @Query(value = "SELECT e FROM Employee e "
            + "LEFT JOIN FETCH e.department d "
            + "LEFT JOIN FETCH e.skillList "
            + "WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    /** HQL aggregate function - average salary across every employee. */
    @Query(value = "SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    /** HQL aggregate function scoped to one department via a bind parameter. */
    @Query(value = "SELECT AVG(e.salary) FROM Employee e WHERE e.department.id = :id")
    double getAverageSalaryByDepartment(@Param("id") int departmentId);

    /** Native Query - plain SQL against the employee table, bypassing HQL entirely. */
    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
