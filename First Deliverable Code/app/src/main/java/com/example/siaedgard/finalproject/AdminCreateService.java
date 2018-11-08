package com.example.siaedgard.finalproject;

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

    }

    public void addNewService () {
        Services services = new Services(ServiceName.getText().toString(), ServiceHourRate.getText().toString());
        MyDBHandler dbHandler = new MyDBHandler(this);
        dbHandler.addServices(services);
    }

    public void OnFinish(View view) {
        addNewService();
        startActivity(getIntent());
    }

    }
