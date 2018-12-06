package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class HomeOwnerSearchByService extends AppCompatActivity {

    EditText service;
    String userId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_owner_search_by_service);
        service = (EditText) findViewById(R.id.editText);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            userId = bd.get("USER_ID").toString();
        }
    }

    public void OnFinish(View view) {
        String inputService = service.getText().toString();
        MyDBHandler dbHandler = new MyDBHandler(this);
        Services serviceFound = dbHandler.findService( inputService);
        boolean invalidAuthentification = serviceFound == null;
        if (invalidAuthentification) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Service does not exist");
            alert.setMessage("This service is not offered yet");
            alert.setPositiveButton("OK",null);
            alert.show();
            }
        else {
            Intent intent = new Intent(this, ServiceProviderListByService.class);
            intent.putExtra("SERVICE_NAME",  inputService);
            intent.putExtra("USER_ID",  userId);
            startActivity(intent);
        }
    }
}