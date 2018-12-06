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

public class ServiceProviderDeleteServices extends AppCompatActivity {
    TextView ServiceName;
    TextView ServiceHourRate;
    ListView listViewServices;
    List<Services> services;
    int userId;
    TextView idView;
    EditText serviceNameField;
    EditText servicePrice;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminupdateservices);
        Intent intent = getIntent();
        services = new ArrayList<>();
        ServiceName = (TextView) findViewById(R.id.textViewName);
        ServiceHourRate = (TextView) findViewById(R.id.HourlyRate);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        MyDBHandler dbHandler = new MyDBHandler(this);
        Bundle bd = intent.getExtras();
        if (bd != null) {
            userId = Integer.parseInt((String)bd.get("USER_ID"));
        }
        Map<String, String> map = dbHandler.findServiceProviderServices(userId);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            Services service = new Services(entry.getKey(), entry.getValue());
            services.add(service);
        }
        ServiceList productsAdapter = new ServiceList(ServiceProviderDeleteServices.this, services);
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
        final TextView dialogText = (TextView) dialogView.findViewById(R.id.editTextServiceName);
        dialogText.setText("Are you sure you want to delete this service from your profile ?");
        dialogBuilder.setTitle(services.getName());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeService(view,services.getName());
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

    public void removeService(View view, final String serviceName) {
        final MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.deleteServiceProviderServices(serviceName);
        finish();
        startActivity(getIntent());
    }

}

