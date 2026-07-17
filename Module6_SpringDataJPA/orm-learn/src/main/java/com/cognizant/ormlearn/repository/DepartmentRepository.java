package com.cognizant.ormlearn.repository;

import com.cognizant.ormlearn.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Hands on 3: Create payroll tables and bean mapping
 */
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
