package com.example.siaedgard.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AdminCreateService extends AppCompatActivity {

    EditText ServiceName, ServiceHourRate;
    private String Name, lastName, userType;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admincreateservice);
        ServiceName = (EditText) findViewById(R.id.Name);
        ServiceHourRate = (EditText) findViewById(R.id.HourlyRate);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            Name = bd.get("FIRST_NAME").toString();
            lastName = bd.get("LAST_NAME").toString();
            userType = bd.get("USER_TYPE").toString();
        }
    }



    public void OnFinish(View view) {
        Services services = new Services(ServiceName.getText().toString(), ServiceHourRate.getText().toString());
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addServices(services);
        String serviceNameField = ServiceName.getText().toString();
        String serviceRateField = ServiceHourRate.getText().toString();
        int min =10;
        int max=1000;
        if( serviceNameField.isEmpty() || serviceRateField.isEmpty()) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        }
        else if (Integer.parseInt(serviceRateField) < min  || Integer.parseInt(serviceRateField) > max ){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("The price is lower or higher");
            alert.setPositiveButton("OK",null);
            alert.show();

        }

        else {
            Intent intent = new Intent(this, WelcomePage.class);
            intent.putExtra("USER_TYPE",  userType);
            intent.putExtra("FIRST_NAME",  Name);
            intent.putExtra("LAST_NAME", lastName);
            startActivity(intent);
        }

    }

    }
