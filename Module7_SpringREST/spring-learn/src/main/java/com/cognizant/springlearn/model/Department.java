package com.cognizant.springlearn.model;

/**
 * doc3: "Create one or two more departments" - loaded from employee.xml,
 * returned as-is by DepartmentController / DepartmentService / DepartmentDao.
 */
public class Department {

    private int id;
    private String name;

    public Department() {
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

    @Override
    public String toString() {
        return "Department{id=" + id + ", name='" + name + "'}";
    }
}
