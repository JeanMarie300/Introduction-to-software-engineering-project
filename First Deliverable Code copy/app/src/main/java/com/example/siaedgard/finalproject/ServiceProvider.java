package com.example.siaedgard.finalproject;

public class ServiceProvider extends User {
    private String phoneNumber, companyName, licensed, expertise;
    private int experienceYears;
    // Create Add the informations related to a service provider in the database
    public ServiceProvider(User user, String phoneNumber, String companyName, String license, int experienceYears, String expertise) {
        super(user.getFirstName(), user.getLastName(), user.getBirthday(), user.getPostalCode(), user.getUserType(),
                user.getUsername(),  user.getPassword());
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.licensed = license;
        this.experienceYears = experienceYears;
        this.expertise = expertise;
    }
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getCompanyName() {
        return this.companyName;
    }

    public String getLicenseName() {
        return this.licensed;
    }

    public int getexperienceYears() {
        return this.experienceYears;
    }

    public String getExpertise() {
        return this.expertise;
    }
    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

}
