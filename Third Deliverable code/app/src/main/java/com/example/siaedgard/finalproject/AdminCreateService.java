package com.example.siaedgard.finalproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class AdminCreateService extends AppCompatActivity {

    EditText ServiceName, ServiceHourRate;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admincreateservice);
        ServiceName = (EditText) findViewById(R.id.Name);
        ServiceHourRate = (EditText) findViewById(R.id.HourlyRate);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
    }

    public void OnFinish(View view) {
        Services services = new Services(ServiceName.getText().toString(), ServiceHourRate.getText().toString());
        MyDBHandler dbHandler = new MyDBHandler(this);
        String serviceNameField = ServiceName.getText().toString();
        String serviceRateField = ServiceHourRate.getText().toString();
        String  rateValue = "";
        int i = 0;

        while(i<serviceRateField.length() && serviceRateField.charAt(i) != '$') {
            rateValue=  rateValue + serviceRateField.charAt(i);
            i++;
        }
        int min =10;
        int max=1000;
        if( serviceNameField.isEmpty() || serviceRateField.isEmpty()) {
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("You need to fill up all the field");
            alert.setPositiveButton("OK",null);
            alert.show();
        }
        else if (Integer.parseInt(rateValue) < min  || Integer.parseInt(rateValue) > max ){
            AlertDialog.Builder  alert = new AlertDialog.Builder(this);
            alert.setTitle("Empty field alert");
            alert.setMessage("The hour rate is not between the acceptable boundaries");
            alert.setPositiveButton("OK",null);
            alert.show();
        }
        else {
            dbHandler.addServices(services);
            finish();
            startActivity(getIntent());
        }

    }

}
