package com.cognizant.springlearn.model;

import java.util.ArrayList;
import java.util.List;

/**
 * doc3: "Create four more instances of Employee (use employee sample data
 * from angular)". Each employee references one Department bean and reuses
 * existing Skill beans, all wired together in employee.xml.
 */
public class Employee {

    private int id;
    private String name;
    private String designation;
    private Department department;
    private List<Skill> skills = new ArrayList<>();

    public Employee() {
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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', designation='" + designation
                + "', department=" + (department != null ? department.getName() : null) + "}";
    }
}
