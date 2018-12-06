package com.example.siaedgard.finalproject;

public class HomeOwner extends User{

    public HomeOwner(User user) {
        super(user.getId(),user.getFirstName(), user.getBirthday(), user.getPostalCode(), user.getUserType(),
                user.getUsername(),  user.getPassword(), user.getAddress());
    }
}
