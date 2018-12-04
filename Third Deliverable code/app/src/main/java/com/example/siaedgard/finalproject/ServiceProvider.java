package com.example.siaedgard.finalproject;

public class ServiceProvider extends User {
    private String phoneNumber, companyName, licensed, expertise;
    private double rating;
    private int experienceYears;
    public ServiceProvider(User user, String phoneNumber, String companyName, String license, int experienceYears, String expertise) {
        super(user.getId(),user.getFirstName(), user.getBirthday(), user.getPostalCode(), user.getUserType(),
                user.getUsername(),  user.getPassword(), user.getAddress());
        this.phoneNumber = phoneNumber;
        this.companyName = companyName;
        this.licensed = license;
        this.experienceYears = experienceYears;
        this.expertise = expertise;
        this.rating = 0;
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

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getRating() {
        return rating;
    }
}
