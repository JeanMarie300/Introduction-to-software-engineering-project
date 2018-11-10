package com.example.siaedgard.finalproject;

public class Services {
    private String Name;
    private String hourRate;

    public Services(String Name, String hourRate) {
        this.Name = Name;
        this.hourRate = hourRate;
    }

    public String getName() {
        return this.Name;
    }
    public String gethourRate() {
        return this.hourRate;
    }
}
