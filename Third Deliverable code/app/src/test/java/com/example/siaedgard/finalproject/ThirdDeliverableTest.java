package com.example.siaedgard.finalproject;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 */

public class ThirdDeliverableTest {


    @Test
    public void checkServiceProviderExpertise() {
        User user = new User("1","Jean-Marie","N'dah", "1998/02/02","K3E4R5", "Service provider", "JeanMarie", "Password", "3344 Bank Street");
        ServiceProvider serviceProvider = new ServiceProvider(user,"1234567990", "IBM", "yes", 4, "plumber");
        assertEquals("Check the service provider expertise", "plumber", serviceProvider.getExpertise());
    }

    @Test
    public void checkServiceProviderCompany() {
        User user = new User("1","Jean-Marie","N'dah", "1998/02/02","K3E4R5", "Service provider", "JeanMarie", "Password", "3344 Bank Street");
        ServiceProvider serviceProvider = new ServiceProvider(user,"1234567990", "IBM", "yes", 4, "plumber");
        assertEquals("Check the service provider company", "IBM", serviceProvider.getCompanyName());
    }



}
