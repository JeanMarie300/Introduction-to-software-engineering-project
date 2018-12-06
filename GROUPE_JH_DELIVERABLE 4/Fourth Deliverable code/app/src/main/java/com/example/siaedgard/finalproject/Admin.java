package com.example.siaedgard.finalproject;

public class Admin extends User {

    public Admin(User user) {
        super(user.getId(),user.getFirstName(), user.getBirthday(), user.getPostalCode(), user.getUserType(),
                user.getUsername(),  user.getPassword(), user.getAddress());
    }
}
