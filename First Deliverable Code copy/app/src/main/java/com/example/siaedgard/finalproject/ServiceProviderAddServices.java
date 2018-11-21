package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ServiceProviderAddServices extends AppCompatActivity {
    TextView ServiceName;
    TextView ServiceHourRate;
    ListView listViewServices;
    String userId;
    List<Services> services;
    boolean added;
    TextView idView;
    EditText serviceNameField;
    EditText servicePrice;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminupdateservices);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if (bd != null) {
            userId = (String)bd.get("USER_ID");
        }
        services = new ArrayList<>();
        ServiceName = (TextView) findViewById(R.id.textViewName);
        ServiceHourRate = (TextView) findViewById(R.id.HourlyRate);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        MyDBHandler dbHandler = new MyDBHandler(this);
        Map<String, String> map = dbHandler.findServices();

        for (Map.Entry<String, String> entry : map.entrySet()) {
            Services service = new Services(entry.getKey(), entry.getValue());
            services.add(service);
        }
        //ServiceList productsAdapter = new ServiceList(AdminUpdateService.this, services);
        ServiceList productsAdapter = new ServiceList(ServiceProviderAddServices.this, services);
        listViewServices.setAdapter(productsAdapter);

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Services service = services.get(i);
                showUpdateDeleteDialog(service);
                return true;
            }
        });
    }

    private void showUpdateDeleteDialog(final Services services) {
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
        long userId = dbHandler.addServicesToUser(services,id);
        if (userId == -2) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Service already added");
            alert.setMessage("You have already added this service to your profile");
            alert.setPositiveButton("OK",null);
            alert.show();
        } else {
            finish();
            startActivity(getIntent());
        }
    }
}

