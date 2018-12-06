package com.example.siaedgard.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class HomeOwnerBookingList extends AppCompatActivity {
    TextView ServiceProviderName;
    ListView listViewProvider;
    List<Booking> bookings;
    String userId, userType, Name, providerId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_owner_booking_list);
        Intent intent = getIntent();
        listViewProvider = (ListView) findViewById(R.id.bookinglist);
        MyDBHandler dbHandler = new MyDBHandler(this);
        Bundle bd = intent.getExtras();
        if (bd != null) {
            userId = bd.get("USER_ID").toString();
            final List<Booking> bookings = dbHandler.findServiceProviderBooking(userId);
            BookingList productsAdapter = new BookingList(HomeOwnerBookingList.this, bookings);
            listViewProvider.setAdapter(productsAdapter);

            listViewProvider.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Booking booking = bookings.get(i);
                    showRatingForm (booking);
                    return true;
                }
            });
        }
    }

    private void showRatingForm ( final Booking booking){
        MyDBHandler dbHandler = new MyDBHandler(this);
        if (dbHandler.findFeedback(booking.getId()) == null) {
            Intent intent = new Intent(this, HomeOwnerRating.class);
            User userFound = dbHandler.findUserByName(booking.getService_provider_id());
            intent.putExtra("USER_ID", userId);
            intent.putExtra("PROVIDER_ID", userFound.getId());
            intent.putExtra("BOOKING_ID", booking.getId());
            startActivity(intent);
        } else {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Service already rated");
            alert.setMessage("The service is already rated, you cant rate it twice");
            alert.setPositiveButton("OK",null);
            alert.show();
        }

    }

}


