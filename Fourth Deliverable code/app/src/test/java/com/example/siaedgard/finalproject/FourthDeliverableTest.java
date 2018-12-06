package com.example.siaedgard.finalproject;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FourthDeliverableTest {

    @Test
    public void checkIdOfBooking() {
        Booking booking  = new Booking("1", "4", "3", "2018/04/04");
        assertEquals("Check the Id of the booking", "1", booking.getId());
    }

    @Test
    public void checkHomeOwnerIdOfBooking() {
        Booking booking  = new Booking("1", "4", "3", "2018/04/04");
        assertEquals("Check the home owner id related to the booking", "4", booking.getHome_owner_id());
    }

    @Test
    public void checkServiceProviderIdForBooking() {
        Booking booking  = new Booking("1", "4", "3", "2018/04/04");
        assertEquals("Check the id of the service provider associated to the booking", "3", booking.getService_provider_id());
    }

    @Test
    public void checkDateForBooking() {
        Booking booking  = new Booking("1", "4", "3", "2018/04/04");
        assertEquals("Check the date of the booking", "2018/04/04", booking.getDateOfBooking());
    }

    @Test
    public void checkIdOfFeedback() {
        Feedback feedback  = new Feedback("1", "4", "3", "2018/04/04", "6", "7");
        assertEquals("Check the Id for the feedback", "1", feedback.getId());
    }

    @Test
    public void checkCommentOfFeedback() {
        //String id, String comments, String ratings, String home_owner, String service_provider, String bookingid
        Feedback feedback  = new Feedback("1", "Very good service", "3", "2018/04/04", "6", "7");
        assertEquals("Check the comment of the feedback", "Very good service", feedback.getComments());
    }

    @Test
    public void checkRatingFeedback() {
        //String id, String comments, String ratings, String home_owner, String service_provider, String bookingid
        Feedback feedback  = new Feedback("1", "Very good service", "3", "2018/04/04", "6", "7");
        assertEquals("Check the ratinf of the feedback", "3", feedback.getRatings());
    }

    @Test
    public void checkHomeOwnerIdForFeedback() {
        //String id, String comments, String ratings, String home_owner, String service_provider, String bookingid
        Feedback feedback  = new Feedback("1", "Very good service", "3", "4", "6", "7");
        assertEquals("Check the Id of the feedback", "4", feedback.getHome_owner_id());
    }


    @Test
    public void checkServiceProviderForFeedback() {
        //String id, String comments, String ratings, String home_owner, String service_provider, String bookingid
        Feedback feedback  = new Feedback("1", "Very good service", "3", "4", "6", "7");
        assertEquals("Check The service provider id for the feedback", "6", feedback.getService_provider_id());
    }

    @Test
    public void checkBookingIdForFeedback() {
        //String id, String comments, String ratings, String home_owner, String service_provider, String bookingid
        Feedback feedback  = new Feedback("1", "Very good service", "3", "4", "6", "7");
        assertEquals("Check the booking Id for the feedback", "7", feedback.getBookingId());
    }











}
