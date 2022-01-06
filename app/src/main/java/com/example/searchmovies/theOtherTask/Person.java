package com.example.searchmovies.theOtherTask;

public class Person {

    private String firstName;
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String fullName) {
        int idx = fullName.lastIndexOf(' ');
        if (idx == -1)
            throw new IllegalArgumentException("Only a single name: " + fullName);
        firstName = fullName.substring(0, idx);
        lastName = fullName.substring(idx + 1);
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}
