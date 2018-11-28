package com.example.siaedgard.finalproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HomeOwnerSearchByService extends AppCompatActivity {

    EditText service;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_owner_search_by_service);
        }

    public void OnFinish(View view) {

        String inputService = service.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        User serviceFound = dbHandler.findUser( inputService);
        boolean invalidAuthentification = serviceFound == null;

        if (invalidAuthentification) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Wrong information");
            alert.setMessage("The service doesn't exist");
            alert.setPositiveButton("OK",null);
            alert.show();
            }

        else {
            Intent intent = new Intent(this, ServiceProviderList.class);
            startActivity(intent);
            finish();

            }

            }
}
