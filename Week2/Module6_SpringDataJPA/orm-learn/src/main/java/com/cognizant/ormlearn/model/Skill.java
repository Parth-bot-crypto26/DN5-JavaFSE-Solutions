package com.cognizant.ormlearn.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Hands on 3: Create payroll tables and bean mapping
 * Hands on 6: Implement many-to-many relationship between Employee and Skill
 */
@Entity
@Table(name = "skill")
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sk_id")
    private int id;

    @Column(name = "sk_name")
    private String name;

    /** Inverse side of the many-to-many; owning side lives on Employee.skillList */
    @ManyToMany(mappedBy = "skillList")
    private Set<Employee> employeeList = new HashSet<>();

    public Skill() {
    }

    public Skill(String name) {
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
        return "Skill{id=" + id + ", name='" + name + "'}";
    }
}
