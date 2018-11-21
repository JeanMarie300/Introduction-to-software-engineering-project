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
                return true;
            }
        });
    }

 /*   private void showUpdateDeleteDialog(final Services services) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.service_added_dialog, null);
        dialogBuilder.setView(dialogView);
        final android.app.AlertDialog.Builder  alert = new android.app.AlertDialog.Builder(this);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.yes);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.no);
        dialogBuilder.setTitle(services.getName());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnConfirm(view,services);
                b.dismiss();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b.dismiss();
            }
        });
    }

    public void OnConfirm(View view,Services services) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        int id = Integer.parseInt(userId);
        dbHandler.addServicesToUser(services,id);
        finish();
        startActivity(getIntent());
    }*/
}

