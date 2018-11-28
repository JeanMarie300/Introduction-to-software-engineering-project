package com.example.siaedgard.finalproject;

public class Feedback {

    private  String Comments, id, Ratings,  home_owner_id, service_provider_id;

    public Feedback(String id, String comments, String ratings, String home_owner, String service_provider) {
        this.id = id;
        this.Comments = comments;
        this.Ratings = ratings;
        this.home_owner_id = home_owner;
        this.service_provider_id = service_provider;
    }

    public String getComments() {
        return this.Comments;
    }

    public void setComments(String comments) {
        this.Comments = comments;
    }

    public String getRatings() {
        return this.Ratings;
    }

    public void setRatings(String ratings) {
        this.Ratings = ratings;
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
}
