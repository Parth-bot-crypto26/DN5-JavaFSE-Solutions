package com.cognizant.ormlearn.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * Hands on 3: Create payroll tables and bean mapping
 * Hands on 4: Implement many-to-one relationship between Employee and Department
 * Hands on 6: Implement many-to-many relationship between Employee and Skill
 */
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "em_id")
    private int id;

    @Column(name = "em_name")
    private String name;

    @Column(name = "em_salary")
    private double salary;

    @Column(name = "em_permanent")
    private boolean permanent;

    @Column(name = "em_date_of_birth")
    private LocalDate dateOfBirth;

    /** Many employees belong to one department - default fetch for @ManyToOne is EAGER. */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "em_dp_id")
    private Department department;

    /** Owning side of the many-to-many relationship via the employee_skill join table. */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "employee_skill",
            joinColumns = @JoinColumn(name = "es_em_id"),
            inverseJoinColumns = @JoinColumn(name = "es_sk_id")
    )
    private Set<Skill> skillList = new HashSet<>();

    public Employee() {
    }

    public Employee(String name, double salary, boolean permanent, LocalDate dateOfBirth) {
        this.name = name;
        this.salary = salary;
        this.permanent = permanent;
        this.dateOfBirth = dateOfBirth;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public boolean isPermanent() {
        return permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Set<Skill> getSkillList() {
        return skillList;
    }

    public void setSkillList(Set<Skill> skillList) {
        this.skillList = skillList;
    }

    @Override
    public String toString() {
        return "Employee{id=" + id + ", name='" + name + "', salary=" + salary
                + ", permanent=" + permanent + ", dateOfBirth=" + dateOfBirth
                + ", departmentId=" + (department != null ? department.getId() : null) + "}";
    }
}
