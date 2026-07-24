package com.cognizant.springlearn.model;

/**
 * doc3: "Reuse existing skills instead of creating new ones" - a plain bean
 * loaded straight from employee.xml, same as Country.
 */
public class Skill {

    private int id;
    private String name;

    public Skill() {
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
        return "Skill{id=" + id + ", name='" + name + "'}";
    }
}
