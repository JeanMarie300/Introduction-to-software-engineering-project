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
}
