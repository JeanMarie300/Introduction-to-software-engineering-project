package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ServiceProviderAvailabilitiesList extends AppCompatActivity {
    TextView ServiceName;
    TextView ServiceHourRate;
    ListView listAvailabilities;
    int userId;
    String Name, lastName, userType;
    List<Availability> availabilities ;
    TextView idView;
    EditText serviceNameField;
    EditText servicePrice;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_provider_availabilities_list);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            userId =Integer.parseInt (bd.get("USER_ID").toString());
            Name = (String) bd.get("FIRST_NAME");
            lastName =(String) bd.get("LAST_NAME");
            userType = (String) bd.get("USER_TYPE");

        }
        availabilities = new ArrayList<>();
        ServiceName = (TextView) findViewById(R.id.textViewName);
        ServiceHourRate = (TextView) findViewById(R.id.HourlyRate);
        listAvailabilities = (ListView) findViewById(R.id.listViewServices);
        MyDBHandler dbHandler = new MyDBHandler(this);
        availabilities = dbHandler.findAvailabilities(userId);

        AvailabilityList productsAdapter = new AvailabilityList(ServiceProviderAvailabilitiesList.this, availabilities);
        listAvailabilities.setAdapter(productsAdapter);

        listAvailabilities.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Availability availability = availabilities.get(i);
                editAvailability( availability);
                return true;
            }
        });
    }

    private void editAvailability(final Availability availability) {
        Intent intent = new Intent(this, ServiceProviderEditAvailability.class);
        intent.putExtra("INITIAL_DATE",  availability.getInitialDate());
        intent.putExtra("INITIAL_TIME", availability.getInitialTime());
        intent.putExtra("FINAL_TIME", availability.getFinalTime());
        intent.putExtra("AVAILABILITY_ID", availability.getId());
        intent.putExtra("USER_ID", userId);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME", Name);
        intent.putExtra("LAST_NAME", lastName);
        startActivity(intent);
        finish();
    }

}

