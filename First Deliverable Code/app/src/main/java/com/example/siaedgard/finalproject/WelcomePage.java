package com.example.siaedgard.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class WelcomePage extends AppCompatActivity {


    private static final String[] ActionBarForAdmin = {"Create a service", "Modify a service", "Delete a service", "List of service" };
    private static final String[] ActionBarForHomeOwner = {"Search for service", "Book a service", "Rate a service"};
    private static final String[] ActionBarForServiceProvider = {"Modify profil", "Associate with service", "Enter Availabilities"};
    private String Name, lastName, userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);
        TextView FirsttxtView = findViewById(R.id.sessionText);
        TextView SecondtxtView = findViewById(R.id.welcomeText);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        if(bd != null)
        {
            String sessionType = bd.get("USER_TYPE") + " Session";
            if (bd.get("USER_TYPE").equals(MainActivity.paths[0])) {
                Button ButtonAction1 = findViewById(R.id.Action1);
                ButtonAction1.setText(ActionBarForAdmin[0]);
                Button ButtonAction2 = findViewById(R.id.Action2);
                ButtonAction2.setText(ActionBarForAdmin[1]);
                Button ButtonAction3 = findViewById(R.id.Action3);
                ButtonAction3.setText(ActionBarForAdmin[2]);
                Name = bd.get("FIRST_NAME").toString();
                lastName = bd.get("LAST_NAME").toString();
                userType = bd.get("USER_TYPE").toString();
                String getName = "Welcome "+(String) bd.get("FIRST_NAME")+ " " + bd.get("LAST_NAME");
                FirsttxtView.setText(sessionType);
                SecondtxtView.setText(getName);
            } else if (bd.get("USER_TYPE").equals(MainActivity.paths[1])) {
                Button ButtonAction1 = findViewById(R.id.Action1);
                ButtonAction1.setText(ActionBarForHomeOwner[0]);
                Button ButtonAction2 = findViewById(R.id.Action2);
                ButtonAction2.setText(ActionBarForHomeOwner[1]);
                Button ButtonAction3 = findViewById(R.id.Action3);
                ButtonAction3.setText(ActionBarForHomeOwner[2]);
                String getName = "Welcome "+(String) bd.get("FIRST_NAME")+ " " + bd.get("LAST_NAME");
                FirsttxtView.setText(sessionType);
                SecondtxtView.setText(getName);
            } else {
                Button ButtonAction1 = findViewById(R.id.Action1);
                ButtonAction1.setText(ActionBarForServiceProvider[0]);
                Button ButtonAction2 = findViewById(R.id.Action2);
                ButtonAction2.setText(ActionBarForServiceProvider[1]);
                Button ButtonAction3 = findViewById(R.id.Action3);
                ButtonAction3.setText(ActionBarForServiceProvider[2]);
                String getName = "Welcome "+(String) bd.get("FIRST_NAME")+ " " + bd.get("LAST_NAME");
                FirsttxtView.setText(sessionType);
                SecondtxtView.setText(getName);
            }
        }
    }

    public void OnFinish(View view) {
        Intent intent = new Intent(this, AdminCreateService.class);
        intent.putExtra("USER_TYPE",  userType);
        intent.putExtra("FIRST_NAME",  Name);
        intent.putExtra("LAST_NAME", lastName);
        startActivity(intent);
    }

    public void OnFinish2(View view) {
        Intent intent = new Intent(this, AdminUpdateService.class);
        startActivity(intent);
    }

}
