package com.example.siaedgard.finalproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
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

public class ServiceProviderListByService extends AppCompatActivity {
    TextView ServiceProviderName;
    ListView listViewProvider;
    List<ServiceProvider> providers;
    TextView idView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.provider_list_by_service);
        Intent intent = getIntent();
        providers = new ArrayList<>();
        ServiceProviderName = (TextView) findViewById(R.id.textViewName);
        listViewProvider = (ListView) findViewById(R.id.listViewProviders);
        MyDBHandler dbHandler = new MyDBHandler(this);
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
        String serviceName = bd.get("SERVICE_NAME").toString();
        List<User> serviceProviderList = dbHandler.findServiceProviderByService(serviceName);

        ServiceList productsAdapter = new ServiceList(ServiceProviderListByService.this, services);
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
        final View dialogView = inflater.inflate(R.layout.update_service_dialog, null);
        dialogBuilder.setView(dialogView);
        final android.app.AlertDialog.Builder  alert = new android.app.AlertDialog.Builder(this);
        serviceNameField = (EditText) dialogView.findViewById(R.id.editTextServiceName);
        serviceNameField.setText(services.getName());
        servicePrice = (EditText) dialogView.findViewById(R.id.editTextServicePrice);
        servicePrice.setText(services.gethourRate());
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateService);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteService);

        dialogBuilder.setTitle(services.getName());
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = serviceNameField.getText().toString().trim();
                String rate = servicePrice.getText().toString().trim();
                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(rate)) {
                    int i =0;
                    String  rateValue = "";
                    while(i<rate.length() && rate.charAt(i) != '$') {
                        rateValue=  rateValue + rate.charAt(i);
                        i++;
                    }
                    int min =10;
                    int max=1000;
                    if (Integer.parseInt(rateValue) < min  || Integer.parseInt(rateValue) > max ) {
                        alert.setTitle("Hour rate unnaceptable");
                        alert.setMessage("The hour rate is not between the acceptable boundaries");
                        alert.setPositiveButton("OK",null);
                        alert.show();
                    } else {
                        OnUpdateService(view,services,name, rate);
                        b.dismiss();
                    }
                }
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeProduct(view,services.getName());
                b.dismiss();
            }
        });
    }

    public void OnUpdateService(View view,Services services, String name, String rate) {
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.updateServices(services.getName(), name, rate);
        finish();
        startActivity(getIntent());
    }

    public void removeProduct(View view, final String serviceName) {

        final MyDBHandler dbHandler = new MyDBHandler(this);
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        dbHandler.deleteServices(serviceName);
                        finish();
                        startActivity(getIntent());
                        break;

                    case DialogInterface.BUTTON_NEGATIVE://No button clicked
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure ? Once deleted you wont be able to modify or see the  service").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
    }
}

