package com.wintec.degreemap.data.model;

public class User {
    private String key;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String gender;
    private String pathway;

    public User(String key, String firstName, String lastName, String phone, String email, String gender, String pathway) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.pathway = pathway;
    }

    public String getKey() {
        return key;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getPathway() {
        return pathway;
    }
}
