package org.example;

import jdk.jfr.Description;

public class Person {
    private String firstName;
    private String lastName;
    private String description;

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public void addDescription(String description) {
        this.description = description;
    }

}
