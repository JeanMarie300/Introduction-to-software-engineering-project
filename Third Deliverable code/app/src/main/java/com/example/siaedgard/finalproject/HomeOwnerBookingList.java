package com.example.siaedgard.finalproject;

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
                    return true;
                }
            });
        }
    }

    private void showRatingForm ( final User user){
        Intent intent = new Intent(this, ServiceProviderInformation.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("USER_ID", userId);
        intent.putExtra("PROVIDER_ID", user.getId());
        startActivity(intent);
    }

}


