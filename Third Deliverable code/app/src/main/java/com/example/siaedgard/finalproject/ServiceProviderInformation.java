package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class ServiceProviderInformation extends AppCompatActivity {
    TextView company_name_value, licensedvalue, expertisevalue, experienceYearsValue, PhoneNumberValue, RatingValue;
    String service_provider_id, home_owner_id;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_row_form);
        company_name_value = findViewById(R.id.company_name_value);
        licensedvalue = findViewById(R.id.licensedvalue);
        expertisevalue = findViewById(R.id.expertisevalue);
        experienceYearsValue = findViewById(R.id.experienceYearsValue);
        PhoneNumberValue = findViewById(R.id.PhoneNumberValue);
        RatingValue = findViewById(R.id.RatingValue);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            service_provider_id = bd.get("PROVIDER_ID").toString();
            home_owner_id = bd.get("USER_ID").toString();
            MyDBHandler dbHandler = new MyDBHandler(this);
            HashMap serviceProviderInfo = dbHandler.findServiceProviderInfo(bd.get("PROVIDER_ID").toString());
            company_name_value.setText(serviceProviderInfo.get("CompanyName").toString());
            licensedvalue.setText(serviceProviderInfo.get("Licensed").toString());
            expertisevalue.setText(serviceProviderInfo.get("Expertise").toString());
            if (serviceProviderInfo.get("ExperienceYears").toString().equals("0")) {
                experienceYearsValue.setText("Less than a year");
            } else {
                experienceYearsValue.setText(serviceProviderInfo.get("ExperienceYears").toString());
            }
            PhoneNumberValue.setText(serviceProviderInfo.get("PhoneNumber").toString());
            if (serviceProviderInfo.get("Rating").toString().equals("0.0")) {
                RatingValue.setText("N/A");
            } else {
                RatingValue.setText(serviceProviderInfo.get("Rating").toString());
            }
        }

    }

    public void OnGoback(View view) {
        Intent intent = new Intent(this, ServiceProviderListByService.class);
        startActivity(intent);
        finish();
    }

    public void OnBook(View view) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date date = new Date();
        String dateTime = dateFormat.format(date);
        Booking booking = new Booking ("1",  home_owner_id, service_provider_id, dateTime);
        long id = dbHandler.addBooking(booking);
        booking.setId(String.valueOf(id));
        Intent intent = new Intent(this, ConfirmBooking.class);
        intent.putExtra("USER_ID", home_owner_id);
        startActivity(intent);
    }



}


