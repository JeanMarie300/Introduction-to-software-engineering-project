package com.example.siaedgard.finalproject;

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

public class AdminUpdateService extends AppCompatActivity {
    TextView ServiceName;
    TextView ServiceHourRate;
    ListView listViewServices;
    List<Services> services;
    TextView idView;
    EditText serviceNameField;
    EditText servicePrice;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adminupdateservices);
        services = new ArrayList<>();
        ServiceName = (TextView) findViewById(R.id.textViewName);
        ServiceHourRate = (TextView) findViewById(R.id.HourlyRate);
        listViewServices = (ListView) findViewById(R.id.listViewServices);
        MyDBHandler dbHandler = new MyDBHandler(this);
        Map<String, String> map = dbHandler.findServices();;
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            Services service =new Services(entry.getKey(), entry.getValue());
            services.add(service);
        }
        ServiceList productsAdapter = new ServiceList(AdminUpdateService.this, services);
        listViewServices.setAdapter(productsAdapter);

        listViewServices.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Services service = services.get(i);
                showUpdateDeleteDialog(service.getName());
                return true;
            }
        });
    }

    private void showUpdateDeleteDialog(String serviceName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_service_dialog, null);
        dialogBuilder.setView(dialogView);
        serviceNameField = (EditText) dialogView.findViewById(R.id.editTextServiceName);
        this.servicePrice  = (EditText) dialogView.findViewById(R.id.editTextServicePrice);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);
        final String name = serviceName;

        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

      /*  buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextServiceName.getText().toString().trim();
                double price = Double.parseDouble(String.valueOf(editTextPrice.getText().toString()));
                if (!TextUtils.isEmpty(name)) {
                    updateProduct(name, price);
                    b.dismiss();
                }
            }
        });*/

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeProduct(view, name);
                b.dismiss();
                startActivity(getIntent());
            }
        });
    }

    public void removeProduct (View view, String serviceName) {

        MyDBHandler dbHandler = new MyDBHandler(this);


        boolean result = dbHandler.deleteUsers(serviceName);

        if (result) {
            idView.setText("Record Deleted");
            this.serviceNameField.setText("");
            servicePrice.setText("");
        }
        else
            idView.setText("No Match Found");
    }
}

