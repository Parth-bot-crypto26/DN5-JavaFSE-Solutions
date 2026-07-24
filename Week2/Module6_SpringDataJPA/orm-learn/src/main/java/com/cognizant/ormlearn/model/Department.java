package com.cognizant.ormlearn.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Hands on 3: Create payroll tables and bean mapping
 * Hands on 5: Implement one-to-many relationship between Employee and Department
 */
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dp_id")
    private int id;

    @Column(name = "dp_name")
    private String name;

    /**
     * The "many" side (Employee) owns the relationship via em_dp_id.
     * fetch = EAGER here only so the demo can log the list without an open
     * Hibernate session; see Hands on 5 for the LazyInitializationException
     * this defaults to (and why) if left on FetchType.LAZY.
     */
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER)
    private Set<Employee> employeeList = new HashSet<>();

    public Department() {
    }

    public Department(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Set<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}
