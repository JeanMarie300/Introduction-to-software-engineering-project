package com.example.siaedgard.finalproject;

public class Booking {

    private  String  id,home_owner_id, service_provider_id, dateOfBooking, rating;

    public Booking(String id, String home_owner_id, String service_provider_id, String dateOfBooking) {
        this.id = id;
        this.home_owner_id = home_owner_id;
        this.service_provider_id = service_provider_id;
        this.dateOfBooking = dateOfBooking;
        this.rating = "-1";


    }

    public String getDateOfBooking() {
        return this.dateOfBooking;
    }

    public void setDateOfBooking(String comments) {
        this.dateOfBooking = comments;
    }

    public String getHome_owner_id() {
        return this.home_owner_id;
    }

    public void setHome_owner_id(String home_owner) {
        this.home_owner_id = home_owner;
    }

    public String getService_provider_id() {
        return this.service_provider_id;
    }

    public void setService_provider_id(String service_provider_id) {
        this.service_provider_id = service_provider_id;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRate(String rate) {
        this.rating = rate;
    }

    public String getRating() {
        return rating;
    }
}
