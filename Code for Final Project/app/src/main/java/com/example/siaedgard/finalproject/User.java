package com.example.siaedgard.finalproject;

public class User {
     private String id,firstName, lastName, birthday, postalCode, userType, username, password;

     public User(String id, String firstName, String lastName, String birthday, String postalCode, String userType, String username, String password) {
         this.id = id;
         this.firstName = firstName;
         this.lastName = lastName;
         this.birthday = birthday;
         this.postalCode = postalCode;
         this.userType = userType;
         this.username = username;
         this.password = password;
     }

    public String getFirstName() {
        return this.firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    public String getBirthday() {
        return this.birthday;
    }
    public String getPostalCode() { return this.postalCode;}
    public String getUserType() { return this.userType;}
    public String getUsername() { return this.username;}
    public void setUsername(String username) {  this.username = username;}
    public String getPassword() {  return this.password;}
    public void SetPassword(String password) {this.password = password;}

}
