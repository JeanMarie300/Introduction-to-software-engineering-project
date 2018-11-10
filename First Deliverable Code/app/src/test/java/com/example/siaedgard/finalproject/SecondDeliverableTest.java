package com.example.siaedgard.finalproject;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 */

public class SecondDeliverableTest {

    @Test
    public void checkServiceName() {
        Services service = new Services("plumber", "10");
        assertEquals("Check the name of the service", "plumber", service.getName());
    }
    @Test
    public void checkFirstName(){
        User user = new User("Jake","Peralta","1988-05-21","K1C 1T1", "Home Owner", "jPera", "JPera1988" );
        assertEquals("Check the first name of the user", "Jake", user.getFirstName());
    }

    @Test
    public void checkLastName(){
        User user = new User("Jake","Peralta","1988-05-21","K1C 1T1", "Home Owner", "jPera", "JPera1988" );
        assertEquals("Check the last name of the user", "Peralta", user.getLastName());
    }

    @Test
    public void checkUserType(){
        User user = new User("Jake","Peralta","1988-05-21","K1C 1T1", "Home Owner", "jPera", "JPera1988" );
        assertEquals("Check the user type", "Home Owner", user.getUserType());
    }

    @Test
    public void checkActionBar(){
        User user = new User("Jake","Peralta","1988-05-21","K1C 1T1", "Home Owner", "jPera", "JPera1988" );

        if (user.getUserType().equals("Home Owner")){
            String [] expected = {"Search for service", "Book a service", "Rate a service"};
            String [] actual = WelcomePage.ActionBarForHomeOwner;
            assertEquals("Check bar action button [1]", expected[0], actual[0]);
            assertEquals("Check bar action button [2]", expected[0], actual[0]);
            assertEquals("Check bar action button [3]", expected[0], actual[0]);
        }
        else if (user.getUserType().equals("Admin")){
            String [] expected = {"Search for service", "Book a service", "Rate a service"};
            String [] actual = WelcomePage.ActionBarForAdmin;
            assertEquals("Check bar action button [1]", expected[0], actual[0]);
            assertEquals("Check bar action button [2]", expected[0], actual[0]);
            assertEquals("Check bar action button [3]", expected[0], actual[0]);
        }
        else{
            String [] expected = {"Search for service", "Book a service", "Rate a service"};
            String [] actual = WelcomePage.ActionBarForServiceProvider;
            assertEquals("Check bar action button [1]", expected[0], actual[0]);
            assertEquals("Check bar action button [2]", expected[0], actual[0]);
            assertEquals("Check bar action button [3]", expected[0], actual[0]);
        }

    }

}
