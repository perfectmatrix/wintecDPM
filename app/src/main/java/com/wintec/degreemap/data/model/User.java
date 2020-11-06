package com.wintec.degreemap.data.model;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.wintec.degreemap.BR;

public class User extends BaseObservable {
    private String key;
    private String profileUrl;
    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String gender;
    private String pathway;

    // Required empty constructor
    public User() {
    }

    public User(String key, String profileUrl, String firstName, String lastName, String phone, String email, String address, String gender, String pathway) {
        this.key = key;
        this.profileUrl = profileUrl;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.gender = gender;
        this.pathway = pathway;
    }

    @Bindable
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        if (this.key == null || !this.key.equals(key)) {
            this.key = key;
            notifyPropertyChanged(BR.key);
        }
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    @Bindable
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Bindable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Bindable
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Bindable
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Bindable
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPathway() {
        return pathway;
    }

    public void setPathway(String pathway) {
        this.pathway = pathway;
    }
}
