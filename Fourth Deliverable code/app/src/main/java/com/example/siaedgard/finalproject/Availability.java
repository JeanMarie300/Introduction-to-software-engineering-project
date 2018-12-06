package com.example.siaedgard.finalproject;

public class Availability {

   private  String initialDate, initialTime, finalTime, id;

    public Availability(String id, String initialDate, String initialTime, String finalTime) {
        this.initialDate = initialDate;
        this.initialTime = initialTime;
        this.finalTime = finalTime;
        this.id = id;
    }


    public String getInitialDate() {
        return initialDate;
    }

    public void setInitialDate(String initialDate) {
        this.initialDate = initialDate;
    }

    public String getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(String initialTime) {
        this.initialTime = initialTime;
    }

    public String getFinalTime() {
        return finalTime;
    }

    public void setFinalTime(String finalTime) {
        this.finalTime = finalTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
