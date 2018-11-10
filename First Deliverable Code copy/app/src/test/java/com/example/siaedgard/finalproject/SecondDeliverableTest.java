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


   /* @Test
    public void checkProductId() {
        Product aProduct = new Product("1", "DELL MONITOR", 180);
        assertEquals("Check the id of the product", "1", aProduct.getId());
    }

    @Test
    public void checkProductPrice() {
        // I am making this test to fail
        Product aProduct = new Product("1", "DELL MONITOR", 180);
        assertEquals(180.0, aProduct.getPrice(), 0.1);
    }*/

}